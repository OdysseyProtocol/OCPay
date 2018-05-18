package com.ocpay.wallet.fragment.mainhome;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

import com.ocpay.wallet.Constans;
import com.ocpay.wallet.MyApp;
import com.ocpay.wallet.OCPWallet;
import com.ocpay.wallet.R;
import com.ocpay.wallet.activities.GatheringActivity;
import com.ocpay.wallet.activities.QRReaderActivity;
import com.ocpay.wallet.activities.SendActivity;
import com.ocpay.wallet.activities.TokenTransactionsActivity;
import com.ocpay.wallet.adapter.HomePageAdapter;
import com.ocpay.wallet.adapter.TokenBalanceAdapter;
import com.ocpay.wallet.bean.home.Banner;
import com.ocpay.wallet.bean.home.BannerBean;
import com.ocpay.wallet.bean.home.Generalize;
import com.ocpay.wallet.bean.home.GeneralizeBean;
import com.ocpay.wallet.bean.home.Goods;
import com.ocpay.wallet.bean.home.GoodsBean;
import com.ocpay.wallet.bean.home.HomeBean;
import com.ocpay.wallet.bean.home.TokenBalanceBean;
import com.ocpay.wallet.databinding.FragmentHomeBinding;
import com.ocpay.wallet.fragment.BaseFragment;
import com.ocpay.wallet.greendao.WalletInfo;
import com.ocpay.wallet.http.client.EthScanHttpClientIml;
import com.ocpay.wallet.http.rx.RxBus;
import com.ocpay.wallet.utils.web3j.response.TokenBalanceResponse;
import com.snow.commonlibrary.utils.PrefUtils;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

import static com.ocpay.wallet.Constans.CONFIG.HIDE_ASSET;
import static com.ocpay.wallet.Constans.HOME.GENERALIZE;
import static com.ocpay.wallet.Constans.HOME.MERCHANT;
import static com.ocpay.wallet.Constans.HOME.WHEEL_AD;
import static com.ocpay.wallet.activities.QRReaderActivity.QR_CODE_MODE_PARSE;
import static com.ocpay.wallet.utils.TokenUtils.ETH;
import static com.ocpay.wallet.utils.TokenUtils.TOKEN_OCN;
import static com.ocpay.wallet.utils.eth.OCPWalletUtils.foldWalletAddress;

public class HomeFragment extends BaseFragment<FragmentHomeBinding> implements View.OnClickListener {


    private WalletInfo walletInfo;
    private HomePageAdapter mainAdapter;
    private TokenBalanceAdapter tokenBalanceAdapter;
    private boolean isShowToken;

    @Override
    public int setContentView() {
        return R.layout.fragment_home;
    }

    @Override
    public void loadData() {
        walletInfo = OCPWallet.getCurrentWallet();
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        initRxBus();
        updateInfo(walletInfo);
        initListener();
        initToken();
        getTokenBalance();

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
        Disposable tokenUpdate = RxBus.getInstance()
                .toObservable(Constans.RXBUS.ACTION_TOKEN_BALANCE_UPDATE, TokenBalanceResponse.class)
                .subscribe(new Consumer<TokenBalanceResponse>() {
                    @Override
                    public void accept(TokenBalanceResponse tokenBalanceResponse) throws Exception {
                        updateTokenAdapter(tokenBalanceResponse);

                    }
                });
        addDisposable(tokenUpdate);


    }

    private void updateTokenAdapter(TokenBalanceResponse tokenBalanceResponse) {
        if (tokenBalanceAdapter.getmData() == null || tokenBalanceAdapter.getmData().size() <= 0)
            return;
        for (int i = 0; i < tokenBalanceAdapter.getmData().size(); i++) {
            if (tokenBalanceResponse.getTokenName().equals(tokenBalanceAdapter.getmData().get(i).getTokenName())) {
                tokenBalanceAdapter.getmData().get(i).setTokenBalance(tokenBalanceResponse.getTokenBalance().toString());
                tokenBalanceAdapter.notifyDataSetChanged();
            }
        }

    }


    public void updateInfo(WalletInfo walletInfo) {
        if (walletInfo == null) return;
        OCPWallet.setCurrentWallet(walletInfo);
        bindingView.includeHead.tvWalletName.setText(walletInfo.getWalletName());
        bindingView.include.tvWalletAddress.setText(foldWalletAddress(walletInfo.getWalletAddress()));
    }

    private void initView() {

        mainAdapter = new HomePageAdapter(getContext());
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
                isShowToken = !isShowToken;
                int tbVisible = isShowToken ? View.GONE : View.VISIBLE;
                int tokenBoardVisible = isShowToken ? View.VISIBLE : View.GONE;
                bindingView.rlToken.setVisibility(tokenBoardVisible);
                bindingView.rlView.setVisibility(tbVisible);
                break;
            case R.id.ll_scan:
                QRReaderActivity.startQRReaderActivity(getActivity(), -1, QR_CODE_MODE_PARSE);
                break;
            case R.id.ll_send:
                SendActivity.startSendActivity(getActivity(), walletInfo.getWalletAddress(), TOKEN_OCN);
                break;
            case R.id.ll_record:
                TokenTransactionsActivity.startTokenTransactionActivity(getActivity(), TOKEN_OCN);
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


    public void initToken() {
        tokenBalanceAdapter = new TokenBalanceAdapter(getContext());
        bindingView.rlToken.setLayoutManager(new LinearLayoutManager(getContext()));
        bindingView.rlToken.setAdapter(tokenBalanceAdapter);
        List<TokenBalanceBean> list = new ArrayList<>();
        list.add(new TokenBalanceBean("https://resource.jinse.com/phenix/img/coin/ETH.png", ETH, 0 + ""));
        list.add(new TokenBalanceBean("https://resource.jinse.com/phenix/img/coin/EOS.png", TOKEN_OCN, 0 + ""));
        tokenBalanceAdapter.setData(list);
    }


    public void getTokenBalance() {
        for (TokenBalanceBean tb : tokenBalanceAdapter.getmData()) {
            EthScanHttpClientIml.getTokenBalanceOf(OCPWallet.getCurrentWallet().getWalletAddress(), tb.getTokenName());
//            DataBlockClientIml.getTokenPrice(Constans.RXBUS.ACTION_TOKEN_PRICE_UPDATE, tb.getTokenName());
        }
    }

}
