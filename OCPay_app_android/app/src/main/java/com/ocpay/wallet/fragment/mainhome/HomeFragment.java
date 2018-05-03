package com.ocpay.wallet.fragment.mainhome;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

import com.ocpay.wallet.Constans;
import com.ocpay.wallet.MyApp;
import com.ocpay.wallet.R;
import com.ocpay.wallet.activities.GatheringActivity;
import com.ocpay.wallet.activities.QRReaderActivity;
import com.ocpay.wallet.activities.SendActivity;
import com.ocpay.wallet.adapter.HomePageAdapter;
import com.ocpay.wallet.bean.home.Banner;
import com.ocpay.wallet.bean.home.BannerBean;
import com.ocpay.wallet.bean.home.Generalize;
import com.ocpay.wallet.bean.home.GeneralizeBean;
import com.ocpay.wallet.bean.home.Goods;
import com.ocpay.wallet.bean.home.GoodsBean;
import com.ocpay.wallet.bean.home.HomeBean;
import com.ocpay.wallet.databinding.FragmentHomeBinding;
import com.ocpay.wallet.fragment.BaseFragment;
import com.ocpay.wallet.greendao.WalletInfo;
import com.ocpay.wallet.http.client.HttpClient;
import com.ocpay.wallet.http.rx.RxBus;
import com.ocpay.wallet.utils.eth.OCPWalletUtils;
import com.ocpay.wallet.utils.web3j.response.EthBalanceResponse;
import com.ocpay.wallet.utils.web3j.transaction.OWalletTransaction;
import com.snow.commonlibrary.log.MyLog;
import com.snow.commonlibrary.utils.PrefUtils;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

import static com.ocpay.wallet.Constans.CONFIG.HIDE_ASSET;
import static com.ocpay.wallet.Constans.HOME.GENERALIZE;
import static com.ocpay.wallet.Constans.HOME.MERCHANT;
import static com.ocpay.wallet.Constans.HOME.WHEEL_AD;
import static com.ocpay.wallet.Constans.TEST.OCN_TOKEN_ADDRESS;
import static com.ocpay.wallet.Constans.TEST.WALLET_ADDRESS;
import static com.ocpay.wallet.activities.QRReaderActivity.QR_CODE_MODE_PARSE;
import static com.ocpay.wallet.utils.eth.OCPWalletUtils.foldWalletAddress;
import static com.ocpay.wallet.utils.eth.OCPWalletUtils.getCurrentWallet;

public class HomeFragment extends BaseFragment<FragmentHomeBinding> implements View.OnClickListener {


    private WalletInfo walletInfo;

    @Override
    public int setContentView() {
        return R.layout.fragment_home;
    }

    @Override
    public void loadData() {
        walletInfo = getCurrentWallet();
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        initRxBus();
        updateInfo(walletInfo);
        initListener();
        getTransactionList();
        getTokenTrList();
    }

    private void initListener() {
        bindingView.includeHead.ivSlideMenu.setOnClickListener(this);
        bindingView.include.ivAddressQr.setOnClickListener(this);
        bindingView.include.llSend.setOnClickListener(this);
        bindingView.include.llScan.setOnClickListener(this);
        bindingView.include.llRecord.setOnClickListener(this);
        bindingView.include.ivReChange.setOnClickListener(this);
    }

    private void initRxBus() {
        Disposable disposable = RxBus.getInstance()
                .toObservable(Constans.RXBUS.ACTION_SELECT_WALLET, WalletInfo.class)
                .subscribe(new Consumer<WalletInfo>() {
                    @Override
                    public void accept(WalletInfo walletInfo) throws Exception {
                        if (walletInfo != null) updateInfo(walletInfo);
                    }
                });


        addDisposable(disposable);
    }


    public void updateInfo(WalletInfo walletInfo) {
        if (walletInfo == null) return;
        bindingView.includeHead.tvWalletName.setText(walletInfo.getWalletName());
        bindingView.include.tvWalletAddress.setText(foldWalletAddress(walletInfo.getWalletAddress()));
    }

    private void initView() {

        HomePageAdapter mainAdapter = new HomePageAdapter(getContext());
        RecyclerView.LayoutManager manager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);

        mainAdapter.setData(getHomeBeans());
        bindingView.rlView.setLayoutManager(manager);
        bindingView.rlView.setAdapter(mainAdapter);


    }


    public List<HomeBean> getHomeBeans() {
        List<HomeBean> list = new ArrayList<>();

        HomeBean homeBean1 = new HomeBean();
        HomeBean homeBean2 = new HomeBean();

        //merchant
        GoodsBean goodsBean = new GoodsBean();
        List<Goods> goodsList = new ArrayList<>();
        Goods goods = null;
        for (int i = 0; i < 6; i++) {
            goods = new Goods();
            goodsList.add(goods);
        }
        goodsBean.setPageItems(goodsList);
        homeBean2.setGoodsBean(goodsBean);
        homeBean2.setType(MERCHANT);
        list.add(homeBean2);


        //WHEEL_AD
        BannerBean bannerBean = new BannerBean();
        List<Banner> banners = new ArrayList<>();
        Banner banner = null;
        banner = new Banner();
        banner.setMainImage("https://b-ssl.duitang.com/uploads/item/201708/27/20170827120919_CjJaN.jpeg");
        banners.add(banner);
        banner = new Banner();
        banner.setMainImage("https://b-ssl.duitang.com/uploads/item/201708/27/20170827121116_m8Tfj.thumb.700_0.jpeg");
        banners.add(banner);
        banner = new Banner();
        banner.setMainImage("https://b-ssl.duitang.com/uploads/item/201708/27/20170827121335_emLQd.thumb.700_0.jpeg");
        banners.add(banner);
        bannerBean.setBannerList(banners);
        homeBean1.setBannerBean(bannerBean);
        homeBean1.setType(WHEEL_AD);

        list.add(homeBean1);

        //demo generalize
        HomeBean homeBean = new HomeBean();

        GeneralizeBean generalizeBean = new GeneralizeBean();
        List<Generalize> generalizes = new ArrayList<>();
        generalizes.add(new Generalize(GENERALIZE, ""));
        generalizes.add(new Generalize(GENERALIZE, ""));
        generalizeBean.setPageItems(generalizes);
        homeBean.setGeneralizeBean(generalizeBean);
        homeBean.setType(GENERALIZE);

        list.add(homeBean);


        return list;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.iv_address_qr:
                GatheringActivity.startGatheringActivity(getActivity(), "OCN", walletInfo.getWalletAddress());

                break;

            case R.id.iv_hide_asset:
                hideAsset();
                break;
            case R.id.iv_re_change:

                break;
            case R.id.ll_scan:
                QRReaderActivity.startQRReaderActvity(getActivity(), -1, QR_CODE_MODE_PARSE);
                break;
            case R.id.ll_send:
                SendActivity.startSendActivity(getActivity(), "OCN", walletInfo.getWalletAddress());
                break;
            case R.id.ll_record:

                break;
            case R.id.iv_slide_menu:
                RxBus.getInstance().post(Constans.RXBUS.ACTION_OPEN_DRAWER, 1);
                break;

        }


    }

    private void hideAsset() {
        boolean isHide = PrefUtils.getBoolean(MyApp.getContext(), HIDE_ASSET, false);
        int iconRes = isHide ? R.mipmap.icon_show_asset : R.mipmap.icon_show_asset;
        bindingView.include.ivHideAsset.setImageResource(iconRes);
        PrefUtils.putBoolean(MyApp.getContext(), HIDE_ASSET, !isHide);
    }


    public void showBalance() {
        HttpClient.Builder
                .getEthScanServer()
                .getEthBalance(WALLET_ADDRESS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        new Observer<EthBalanceResponse>() {
                            @Override
                            public void onSubscribe(Disposable d) {
                                addDisposable(d);
                                MyLog.i("onSubscribe");
                            }

                            @Override
                            public void onNext(EthBalanceResponse o) {
                                MyLog.i("onNext" + o.toString());

                            }

                            @Override
                            public void onError(Throwable e) {
                                MyLog.i("onError" + e.getMessage());

                            }

                            @Override
                            public void onComplete() {
                                MyLog.i("onComplete");

                            }
                        }
                );
    }

    public void getTokenBalanceOf() {

        String data = OWalletTransaction.getBalanceOfTokenData(WALLET_ADDRESS);
        HttpClient.Builder
                .getEthScanServer()
                .getTokenBalance(OCN_TOKEN_ADDRESS, data)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        new Observer<Object>() {
                            @Override
                            public void onSubscribe(Disposable d) {
                                addDisposable(d);
                                MyLog.i("onSubscribe");
                            }

                            @Override
                            public void onNext(Object o) {
                                MyLog.i("onNext" + o.toString());

                            }

                            @Override
                            public void onError(Throwable e) {
                                MyLog.i("onError" + e.getMessage());

                            }

                            @Override
                            public void onComplete() {
                                MyLog.i("onComplete");

                            }
                        }
                );
    }

    public void getTransactionList() {

        HttpClient.Builder
                .getEthScanServer()
                .getEthTransactionList(WALLET_ADDRESS, "5000", "9999999")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        new Observer<Object>() {
                            @Override
                            public void onSubscribe(Disposable d) {
                                addDisposable(d);
                                MyLog.i("onSubscribe");
                            }

                            @Override
                            public void onNext(Object o) {
                                MyLog.i("onNext" + o.toString());

                            }

                            @Override
                            public void onError(Throwable e) {
                                MyLog.i("onError" + e.getMessage());

                            }

                            @Override
                            public void onComplete() {
                                MyLog.i("onComplete");

                            }
                        }
                );
    }


    public void getBlockNumber() {
        HttpClient.Builder
                .getEthScanServer()
                .getEthBlockNumber()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        new Observer<Object>() {
                            @Override
                            public void onSubscribe(Disposable d) {
                                addDisposable(d);
                                MyLog.i("onSubscribe");
                            }

                            @Override
                            public void onNext(Object o) {
                                MyLog.i("onNext" + o.toString());

                            }

                            @Override
                            public void onError(Throwable e) {
                                MyLog.i("onError" + e.getMessage());

                            }

                            @Override
                            public void onComplete() {
                                MyLog.i("onComplete");

                            }
                        }
                );
    }


    public void getTokenTrList() {
        String walletAddress = OCPWalletUtils.getWalletAddress32b(WALLET_ADDRESS);
        HttpClient.Builder
                .getEthScanServer()
                .getTokenTransactionList(OCN_TOKEN_ADDRESS, walletAddress, walletAddress, "1000", "last")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        new Observer<Object>() {
                            @Override
                            public void onSubscribe(Disposable d) {
                                MyLog.i("onSubscribe");
                            }

                            @Override
                            public void onNext(Object o) {
                                MyLog.i("onNext" + o.toString());

                            }

                            @Override
                            public void onError(Throwable e) {
                                MyLog.i("onError" + e.getMessage());

                            }

                            @Override
                            public void onComplete() {
                                MyLog.i("onComplete");

                            }
                        }
                );
    }
}
