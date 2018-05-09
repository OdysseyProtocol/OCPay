package com.ocpay.wallet.activities;

import android.app.Activity;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.SeekBar;
import android.widget.Toast;

import com.ocpay.wallet.Constans;
import com.ocpay.wallet.MyApp;
import com.ocpay.wallet.R;
import com.ocpay.wallet.databinding.ActivitySendBinding;
import com.ocpay.wallet.http.rx.RxBus;
import com.ocpay.wallet.view.PasswordConfirmDialog;
import com.snow.commonlibrary.utils.RegularExpressionUtils;

import java.math.BigDecimal;
import java.math.BigInteger;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

import static com.ocpay.wallet.Constans.RXBUS.ACTION_SEND_TRANSFER_ADDRESS;
import static com.ocpay.wallet.Constans.TRANSFER.DEFAULT_GAS_PRICE;
import static com.ocpay.wallet.Constans.WALLET.ADDRESS_FROM;
import static com.ocpay.wallet.Constans.WALLET.TOKEN_NAME;
import static com.ocpay.wallet.activities.QRReaderActivity.QR_CODE_MODE_READER;
import static com.ocpay.wallet.utils.eth.OCPWalletUtils.getTransactionFee;
import static com.snow.commonlibrary.utils.ViewUtils.dp2px;

public class SendActivity extends BaseActivity implements View.OnClickListener {

    private boolean isSimpleMode;

    private ActivitySendBinding binding;

    private String tokenName;

    private BigInteger gasUnit = new BigInteger(16800 + "");

    private BigInteger gasLimit = new BigInteger(gasUnit.toString());

    private BigInteger gasPrice = new BigInteger(DEFAULT_GAS_PRICE);


    private BigInteger cGasLimit = null;

    private BigInteger cGasPrice = null;
    private BigInteger customGas;
    private BigInteger customGasPrice;
    private BigInteger customRealGasPrice;


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

        isSimpleMode = true;

        initActionBar();

        initListener();

        initRxBus();


    }

    private void initRxBus() {

        Disposable txConfirm = RxBus.getInstance()
                .toObservable(Constans.RXBUS.ACTION_TRANSACTION_CONFIRM_KEYSTORE, String.class)
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        showLoading(false);

                        new Thread(new Runnable() {
                            @Override
                            public void run() {



                            }
                        }).start();


                    }
                });
        addDisposable(txConfirm);
        Disposable txGetNonce = RxBus.getInstance()
                .toObservable(Constans.RXBUS.ACTION_TRANSACTION_GET_NONCE, String.class)
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {


//                        EthScanHttpClientIml.getn();

                    }
                });
        addDisposable(txGetNonce);

        Disposable txSendTx = RxBus.getInstance()
                .toObservable(Constans.RXBUS.ACTION_TRANSACTION_SEND_TX, String.class)
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {

                    }
                });

        addDisposable(txSendTx);

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

        binding.tvNextStep.setOnClickListener(this);

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
//        MyLog.i(i + "");
//        MyLog.i("gasPriceD" + gasPriceD.toString());
//        MyLog.i(i + "gasLimitD" + gasLimitD);

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
                if (isInvalidData) nextStep();

                break;
        }
    }

    private void nextStep() {
        PasswordConfirmDialog dialog = PasswordConfirmDialog.getInstance(SendActivity.this, R.style.PasswordConfirmDialog, SendActivity.this);
        Window win = dialog.getWindow();
        win.setGravity(Gravity.CENTER);
        WindowManager.LayoutParams lp = win.getAttributes();
        lp.width = MyApp.getContext().getResources().getDisplayMetrics().widthPixels - dp2px(MyApp.getContext(), 80);
        win.setAttributes(lp);
        dialog.show();


    }

    private boolean checkData() {
        boolean addressValid = RegularExpressionUtils.valid(binding.etSendWalletAddress.getText().toString().trim(), Constans.REGULAR.REGULAR_ETH_ADDRESS);
        if (!addressValid) {
            Toast.makeText(SendActivity.this, "Invalid Address", Toast.LENGTH_LONG).show();
            return false;
        }

        if (isSimpleMode) {
            BigInteger bigInteger = new BigInteger(binding.tvTransferAmount.getText().toString());
            if (bigInteger.compareTo(new BigInteger(0 + "")) < 0) {
                Toast.makeText(SendActivity.this, "Not Enough Amount", Toast.LENGTH_LONG).show();
                return false;
            }


        }

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


}