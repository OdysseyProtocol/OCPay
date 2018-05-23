package com.ocpay.wallet.activities;

import android.app.Activity;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.ocpay.wallet.Constans;
import com.ocpay.wallet.MyApp;
import com.ocpay.wallet.R;
import com.ocpay.wallet.adapter.WalletsAdapter;
import com.ocpay.wallet.databinding.ActivityMainBinding;
import com.ocpay.wallet.databinding.NavHeaderMainBinding;
import com.ocpay.wallet.fragment.fragmentadapter.MyFragmentAdapter;
import com.ocpay.wallet.fragment.mainhome.AirdropFragment;
import com.ocpay.wallet.fragment.mainhome.HomeFragment;
import com.ocpay.wallet.fragment.mainhome.MarketsFragment;
import com.ocpay.wallet.fragment.mainhome.MeFragment;
import com.ocpay.wallet.fragment.mainhome.NearbyFragment;
import com.ocpay.wallet.greendao.WalletInfo;
import com.ocpay.wallet.greendao.manager.WalletInfoDaoUtils;
import com.ocpay.wallet.http.client.DataBlockClientIml;
import com.ocpay.wallet.http.client.EthScanHttpClientIml;
import com.ocpay.wallet.http.rx.RxBus;
import com.ocpay.wallet.utils.OCPPrefUtils;
import com.ocpay.wallet.widget.ScrollViewPager;
import com.ocpay.wallet.widget.customview.BottomActionBar;
import com.snow.commonlibrary.recycleview.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

import static com.ocpay.wallet.http.client.EthScanHttpClientIml.getGasPrice;
import static com.ocpay.wallet.utils.CurrencyUtils.CNY;
import static com.ocpay.wallet.utils.CurrencyUtils.USD;
import static com.ocpay.wallet.utils.TokenUtils.ETH;

public class MainActivity extends BaseActivity implements ViewPager.OnPageChangeListener, View.OnClickListener {

    private ActivityMainBinding mBinding;
    private ScrollViewPager vpContent;
    private WalletsAdapter walletsAdapter;

    public static void startMainActivity(Activity activity) {
        activity.startActivity(new Intent(activity, MainActivity.class));
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        getPermission();

        initStatusView();

        findView();

        initNav();

        initRxBus();

        initListener();

        initContentFragment();

        getGasPrice();

        initEthInfo();
    }


    private void initNav() {
        mBinding.navView.inflateHeaderView(R.layout.nav_header_main);
        View headerView = mBinding.navView.getHeaderView(0);
        NavHeaderMainBinding bind = DataBindingUtil.bind(headerView);
        walletsAdapter = new WalletsAdapter(MainActivity.this);
        List<WalletInfo> walletInfos = WalletInfoDaoUtils.sqlAll(MainActivity.this);
        walletsAdapter.setData(walletInfos);

        bind.rlWalletList.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        bind.rlWalletList.setAdapter(walletsAdapter);
        bind.llCreateWallet.setOnClickListener(this);
        bind.llImportWallet.setOnClickListener(this);
        walletsAdapter.notifyDataSetChanged();
        walletsAdapter.setOnItemClickListener(new BaseAdapter.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView.Adapter adapter, Object data, int position) {
                if (data instanceof WalletInfo) {
                    mBinding.drawerLayout.closeDrawers();
                    if (data == walletsAdapter.getWalletInfo()) return;
                    OCPPrefUtils.setCurrentWallet((WalletInfo) data);
                    walletsAdapter.setWalletInfo((WalletInfo) data);
                    walletsAdapter.notifyDataSetChanged();
                    RxBus.getInstance().post(Constans.RXBUS.ACTION_SELECT_WALLET, data);
                }
            }
        });
    }


    private void initListener() {
        mBinding.include.actionBar.setOnBottomActionBarItemClickListener(new BottomActionBar.OnBottomActionBarItemClickListener() {
            @Override
            public void selected(int type) {
                switch (type) {
                    case BottomActionBar.TYPE_BOTTOM_ACTION_MARKETS:
                        vpContent.setCurrentItem(1);
                        break;
                    case BottomActionBar.TYPE_BOTTOM_ACTION_HOME:
                        vpContent.setCurrentItem(0);
                        break;
                    case BottomActionBar.TYPE_BOTTOM_ACTION_NEARBY:
                        vpContent.setCurrentItem(2);
                        break;
                    case BottomActionBar.TYPE_BOTTOM_ACTION_AIRDROP:
                        vpContent.setCurrentItem(3);
                        break;
                    case BottomActionBar.TYPE_BOTTOM_ACTION_ME:
                        vpContent.setCurrentItem(4);
                        break;
                }
            }
        });


    }

    private void initRxBus() {

        Disposable disposable = RxBus.getInstance()
                .toObservable(Constans.RXBUS.ACTION_OPEN_DRAWER, Integer.class)
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        mBinding.drawerLayout.openDrawer(GravityCompat.END);
                    }
                });
        addDisposable(disposable);
    }

    private void initStatusView() {

    }


    private void findView() {
        vpContent = mBinding.include.vpContent;

    }


    private void initContentFragment() {
        ArrayList<Fragment> mFragmentList = new ArrayList<>();
        mFragmentList.add(new HomeFragment());
        mFragmentList.add(new MarketsFragment());
        mFragmentList.add(new NearbyFragment());
        mFragmentList.add(new AirdropFragment());
        mFragmentList.add(new MeFragment());

        // 注意使用的是：getSupportFragmentManager
        MyFragmentAdapter adapter = new MyFragmentAdapter(getSupportFragmentManager(), mFragmentList);
        vpContent.setAdapter(adapter);
        ViewGroup.LayoutParams layoutParams = vpContent.getLayoutParams();
        layoutParams.height = ViewPager.LayoutParams.MATCH_PARENT;
        layoutParams.width = ViewPager.LayoutParams.MATCH_PARENT;
        vpContent.setLayoutParams(layoutParams);
        vpContent.setOffscreenPageLimit(4);
        vpContent.addOnPageChangeListener(this);
        vpContent.setCurrentItem(0);
        vpContent.setCanScroll(false);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.ll_create_wallet:
                WalletCreateActivity.startActivity(this);
                closeDrawer();
                break;
            case R.id.ll_import_wallet:
                WalletImportActivity.startActivity(this);
                closeDrawer();
                break;
        }
    }

    private void closeDrawer() {
        mBinding.drawerLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                mBinding.drawerLayout.closeDrawers();
            }
        }, 1000);
    }


    private void initEthInfo() {
        //update rate
        DataBlockClientIml.getPairOCN_ETH(-1);
        //update ocn_eth
        DataBlockClientIml.getRate(USD, CNY);
        //update eth
        List<WalletInfo> walletInfos = WalletInfoDaoUtils.sqlAll(MyApp.getContext());
        for (WalletInfo info : walletInfos) {
            EthScanHttpClientIml.getEthBalanceOf(info.getWalletAddress(), ETH, false);
        }
        //record first block high
        if (OCPPrefUtils.isRecordFirstBlock()) {
            EthScanHttpClientIml.getBlockNumber(Constans.RXBUS.ACTION_RECORD_BLOCK_NO);
        }

    }

}
