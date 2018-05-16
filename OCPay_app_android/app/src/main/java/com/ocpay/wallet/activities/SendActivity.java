package com.ocpay.wallet.activities;

import android.app.Activity;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.SeekBar;
import android.widget.Toast;

import com.ocpay.wallet.Constans;
import com.ocpay.wallet.R;
import com.ocpay.wallet.bean.ZipSignBean;
import com.ocpay.wallet.databinding.ActivitySendBinding;
import com.ocpay.wallet.http.client.EthScanHttpClientIml;
import com.ocpay.wallet.http.rx.RxBus;
import com.ocpay.wallet.utils.eth.OCPWalletUtils;
import com.ocpay.wallet.utils.eth.bean.OCPWalletFile;
import com.ocpay.wallet.utils.wallet.WalletStorage;
import com.ocpay.wallet.utils.web3j.response.EtherScanJsonrpcResponse;
import com.ocpay.wallet.widget.dialog.PasswordConfirmDialog;
import com.ocpay.wallet.widget.dialog.TxDetailDialog;
import com.ocpay.wallet.widget.dialog.WarmDialog;
import com.snow.commonlibrary.utils.RegularExpressionUtils;

import org.web3j.crypto.CipherException;
import org.web3j.crypto.ECKeyPair;
import org.web3j.crypto.Wallet;

import java.math.BigDecimal;
import java.math.BigInteger;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

import static com.ocpay.wallet.Constans.ERROR.WALLET_INVALID_PASSWORD;
import static com.ocpay.wallet.Constans.ERROR.WALLET_NO_KEYSTORE;
import static com.ocpay.wallet.Constans.RXBUS.ACTION_SEND_ERROR;
import static com.ocpay.wallet.Constans.RXBUS.ACTION_SEND_TRANSFER_ADDRESS;
import static com.ocpay.wallet.Constans.RXBUS.ACTION_TRANSACTION_SEND_TX;
import static com.ocpay.wallet.Constans.TEST.OCN_TOKEN_ADDRESS;
import static com.ocpay.wallet.Constans.TRANSFER.DEFAULT_GAS_PRICE;
import static com.ocpay.wallet.Constans.WALLET.ADDRESS_FROM;
import static com.ocpay.wallet.Constans.WALLET.TOKEN_NAME;
import static com.ocpay.wallet.activities.QRReaderActivity.QR_CODE_MODE_READER;
import static com.ocpay.wallet.utils.eth.OCPWalletUtils.getTransactionFee;

public class SendActivity extends BaseActivity implements View.OnClickListener {

    private boolean isSimpleMode;

    private ActivitySendBinding binding;

    private String tokenName;

    private BigInteger gasUnit = new BigInteger(16800 + "");

    private BigInteger gasLimit = new BigInteger(gasUnit.toString());

    private BigInteger gasPrice = new BigInteger(DEFAULT_GAS_PRICE);
    private BigInteger customGas;
    private BigInteger customGasPrice;
    private BigInteger customRealGasPrice;
    private BigInteger customGasLimit;
    private String walletAddress;


    public static void startSendActivity(Activity activity, String fromAddress, String tokenName) {

        Intent intent = new Intent(activity, SendActivity.class);
        intent.putExtra(TOKEN_NAME, tokenName);
        intent.putExtra(ADDRESS_FROM, fromAddress);
        activity.startActivity(intent);

    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(SendActivity.this, R.layout.activity_send);

        tokenName = getIntent().getStringExtra(TOKEN_NAME);
        walletAddress = getIntent().getStringExtra(ADDRESS_FROM);

        isSimpleMode = true;

        initActionBar();

        initListener();

        initRxBus();

        initTestData();


    }

    private void initTestData() {

        //0x570bdbCb9d434c51e6F1CC9c796E9c4F3F0C09da
        binding.etSendWalletAddress.setText("0x570bdbCb9d434c51e6F1CC9c796E9c4F3F0C09da");
        binding.tvTransferAmount.setText("1");

    }

    private void initRxBus() {
        Observable<String> pwdObservable = RxBus.getInstance().toObservable(Constans.RXBUS.ACTION_TRANSACTION_CONFIRM_KEYSTORE, String.class);
        Observable<EtherScanJsonrpcResponse> nonceObservable = RxBus.getInstance()
                .toObservable(Constans.RXBUS.ACTION_TRANSACTION_GET_NONCE, EtherScanJsonrpcResponse.class);
        Disposable disposableSign = Observable
                .zip(pwdObservable, nonceObservable, new BiFunction<String, EtherScanJsonrpcResponse, ZipSignBean>() {
                    @Override
                    public ZipSignBean apply(String s, EtherScanJsonrpcResponse etherScanJsonrpcResponse) throws Exception {
                        /**  get nonce and pwd **/
                        return new ZipSignBean(s, etherScanJsonrpcResponse);
                    }
                })
                .observeOn(Schedulers.newThread())
                .subscribeOn(Schedulers.newThread())
                .subscribe(new Consumer<ZipSignBean>() {
                    @Override
                    public void accept(ZipSignBean zipSignBean) throws Exception {
                        /**   keystore is exits **/
                        int status = 0;
                        OCPWalletFile ocpWallet = null;
                        ECKeyPair ecKeyPair = null;
                        try {
                            ocpWallet = WalletStorage.getInstance().getOCPWallet(walletAddress);
                        } catch (Exception e) {
                            e.printStackTrace();
                            status = WALLET_NO_KEYSTORE;
                        }
                        if (ocpWallet == null) {
                            status = WALLET_NO_KEYSTORE;
                        }
                        /**    get keypair **/
                        try {
                            ecKeyPair = Wallet.decrypt(zipSignBean.getPassword(), ocpWallet.getWalletFile());
                        } catch (CipherException e) {
                            e.printStackTrace();
                            status = WALLET_INVALID_PASSWORD;
                        }
                        if (ecKeyPair == null) status = WALLET_INVALID_PASSWORD;

                        if (status != 0) {
                            RxBus.getInstance().post(ACTION_SEND_ERROR, Integer.class);
                            return;
                        }

                        /**    sign    **/
                        String signHex = OCPWalletUtils.signTransaction(ecKeyPair, binding.tvTransferAmount.getText().toString().trim(),
                                Constans.currentWallet.getWalletAddress(),
                                gasPrice.toString(),
                                gasLimit.toString(),
                                "",
                                getErc20Address(),
                                zipSignBean.getEtherScanJsonrpcResponse().getDicemalFromDex()
                        );
                        /**  send transaction **/
                        if (!signHex.startsWith("0x")) return;
                        EthScanHttpClientIml.sendTransaction(ACTION_TRANSACTION_SEND_TX, signHex);
                    }
                });
        addDisposable(disposableSign);


        Disposable txSendTx = RxBus.getInstance()
                .toObservable(ACTION_TRANSACTION_SEND_TX, Object.class)
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(Object s) throws Exception {

                    }
                });
        addDisposable(txSendTx);

        Disposable statusResponse = RxBus.getInstance()
                .toObservable(ACTION_SEND_ERROR, Integer.class)
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer s) throws Exception {
                        if (s == null) return;
                        switch (s) {
                            case WALLET_INVALID_PASSWORD:
                                dismissLoading();
                                WarmDialog.getInstance(SendActivity.this).setTip("Wrong Password");
                                break;
                            case WALLET_NO_KEYSTORE:
                                dismissLoading();
                                WarmDialog.getInstance(SendActivity.this).setTip("Can't find  keystore");
                                break;
                        }
                    }
                });
        addDisposable(statusResponse);


    }

    private void initActionBar() {
        String title = tokenName + " " + getResources().getString(R.string.activity_transfer);
        binding.includeActionBar.actionBarTitle.setText(title);
        binding.includeActionBar.toolbarMenuIcon.setImageResource(R.mipmap.ic_bar_sacn);
        binding.includeActionBar.toolbarMenuIcon.setOnClickListener(this);
        binding.includeActionBar.ivBack.setImageResource(R.mipmap.ic_close_black);
        binding.includeActionBar.ivBack.setOnClickListener(this);
        binding.includeActionBar.actionBarTitle.setTextColor(getResources().getColor(R.color.color_text_main));

    }

    private void initListener() {
        binding.tvNextStep.setOnClickListener(this);

        binding.btnMode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isSimpleMode = !isSimpleMode;
                int simpleVisible = isSimpleMode ? View.VISIBLE : View.INVISIBLE;
                int customVisible = isSimpleMode ? View.INVISIBLE : View.VISIBLE;
                binding.llSimpleInput.setVisibility(simpleVisible);
                binding.llCustomInput.setVisibility(customVisible);
            }
        });
        Disposable disposable = RxBus.getInstance()
                .toObservable(ACTION_SEND_TRANSFER_ADDRESS, String.class)
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        if (!s.startsWith("0x")) {
                            Toast.makeText(SendActivity.this, "Invalid Data", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        binding.etSendWalletAddress.setText(s);
                    }
                });
        addDisposable(disposable);


        binding.seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                progressChanged(i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        binding.seekBar.setProgress(10);
        progressChanged(10);
    }

    private void progressChanged(int i) {
        if (i == 0) i = 1;
        BigDecimal gasPriceD = new BigDecimal(gasPrice);
        gasLimit = gasUnit.multiply(new BigInteger(i + ""));
        BigDecimal transactionFee = getTransactionFee(gasPriceD, new BigDecimal(gasLimit));
        binding.tvSeekBarGasFee.setText(transactionFee.toString() + " ETH");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.toolbar_menu_icon:
                QRReaderActivity.startQRReaderActivity(SendActivity.this, ACTION_SEND_TRANSFER_ADDRESS, QR_CODE_MODE_READER);
                break;
            case R.id.tv_next_step:
                boolean isInvalidData = checkData();
                if (isInvalidData) showTxDetailDialog();
                break;
        }
    }

    private void showTxDetailDialog() {

        TxDetailDialog txDetailDialog = TxDetailDialog.getInstance(SendActivity.this);
        txDetailDialog.setListener(new TxDetailDialog.OnNextListener() {
            @Override
            public void onNext() {
                showPwdDialog();
            }
        });
        BigInteger sGasPrice = isSimpleMode ? gasPrice : customGasPrice;
        BigInteger sGasLimit = isSimpleMode ? gasLimit : customGasLimit;

        Window win = txDetailDialog.getWindow();
        win.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.BOTTOM);
        txDetailDialog.show();
        txDetailDialog.setData(getString(R.string.dialog_tx_detail_transfer),
                Constans.currentWallet.getWalletAddress(),
                binding.etSendWalletAddress.getText().toString().trim(),
                sGasPrice,
                sGasLimit,
                binding.tvTransferAmount.getText().toString().trim()
        );


    }


    private void showPwdDialog() {
        PasswordConfirmDialog dialog = PasswordConfirmDialog.getInstance(SendActivity.this);
        dialog.setListener(new PasswordConfirmDialog.ConfirmListener() {
            @Override
            public void onConfirm() {
                showLoading(false);
                setTip(getString(R.string.loading_tip_trading));
            }
        });

        dialog.show();
    }

    private boolean checkData() {
        /** valid address **/
        boolean addressValid = RegularExpressionUtils.valid(binding.etSendWalletAddress.getText().toString().trim(), Constans.REGULAR.REGULAR_ETH_ADDRESS);
        if (!addressValid) {
            Toast.makeText(SendActivity.this, "Invalid Address", Toast.LENGTH_LONG).show();
            return false;
        }
        /** send same address **/
        if (Constans.currentWallet.getWalletAddress().equals(binding.etSendWalletAddress.getText().toString().trim())) {
            Toast.makeText(SendActivity.this, "Invalid Address", Toast.LENGTH_LONG).show();
            return false;
        }
        /** compare balance **/


        BigInteger bigInteger = new BigInteger(binding.tvTransferAmount.getText().toString());
        if (bigInteger.compareTo(new BigInteger(0 + "")) < 0) {
            Toast.makeText(SendActivity.this, "Not Enough Amount", Toast.LENGTH_LONG).show();
            return false;
        }

        /** check arg **/


        if (!isSimpleMode) {

            customGasPrice = new BigInteger(binding.etCustomGasPrice.getText().toString());
            customGas = new BigInteger(binding.etCustomGas.toString());
            BigInteger cutomHex = new BigInteger(binding.etCustomHex.toString());

            if (isPositive(customGasPrice) && isPositive(customGas)) {
                Toast.makeText(SendActivity.this, "Not Enough Amount", Toast.LENGTH_LONG).show();
                return false;
            }
            customRealGasPrice = customGasPrice.multiply(new BigInteger("100000000"));
        }
        return true;
    }


    public boolean isPositive(BigInteger value) {
        return value.compareTo(new BigInteger("0")) > 0;

    }


    public String getErc20Address() {
        if ("ETH".equals(tokenName)) {
            return "";
        }
        return OCN_TOKEN_ADDRESS;
    }

    @Override
    protected void onDestroy() {
        PasswordConfirmDialog.getInstance(SendActivity.this).destroy();
        WarmDialog.getInstance(SendActivity.this).destroy();
        TxDetailDialog.getInstance(SendActivity.this).destroy();
        super.onDestroy();
    }
}
