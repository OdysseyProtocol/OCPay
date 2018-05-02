package com.ocpay.wallet.fragment.fragmentadapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;




public class MyFragmentAdapter extends FragmentPagerAdapter {


    private List<Fragment> fragments;
    private List<String> mTitleList;

    public MyFragmentAdapter(FragmentManager fm, List<Fragment> fragments, List<String> mTitleList) {
        super(fm);
        this.fragments = fragments;
        this.mTitleList = mTitleList;

    }
    public MyFragmentAdapter(FragmentManager fm, List<Fragment> fragments) {
        super(fm);
        this.fragments = fragments;
    }

    public MyFragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return fragments == null ? null : fragments.get(position);
    }


    @Override
    public CharSequence getPageTitle(int position) {
        if (mTitleList != null) {
            return mTitleList.get(position);
        } else {
            return "";
        }
    }

    @Override
    public int getCount() {
        return fragments == null ? 0 : fragments.size();
    }
}
