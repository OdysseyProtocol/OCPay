package com.ocpay.wallet.fragment;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ocpay.wallet.R;
import com.snow.commonlibrary.log.MyLog;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

public abstract class BaseFragment<VD extends ViewDataBinding> extends Fragment {
    protected VD bindingView;
    public boolean mIsVisible;


    public CompositeSubscription mSubscription;
    public CompositeDisposable mDisposable;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_base, null);
        bindingView = DataBindingUtil.inflate(getActivity().getLayoutInflater(), setContentView(), null, false);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        bindingView.getRoot().setLayoutParams(params);

        view.findViewById(R.id.refresh).setEnabled(false);
        RelativeLayout rlContainer = view.findViewById(R.id.container);
        rlContainer.addView(bindingView.getRoot());
        MyLog.i("onCreateView");
        return view;
    }

    public abstract int setContentView();


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        MyLog.i("setUserVisibleHint");

        if (getUserVisibleHint()) {
            mIsVisible = true;
            onVisible();
        } else {
            mIsVisible = false;
            onInvisible();
        }
    }

    private void onInvisible() {


    }

    private void onVisible() {
        loadData();

    }

    public abstract void loadData();


    public void addSubscription(Subscription s) {
        if (this.mSubscription == null) {
            this.mSubscription = new CompositeSubscription();
        }
        this.mSubscription.add(s);
    }


    public void addDisposable(Disposable disposable) {
        if (this.mDisposable == null) {

            this.mDisposable = new CompositeDisposable();
        }
        this.mDisposable.add(disposable);
    }


    @Override
    public void onDestroy() {
        if (this.mSubscription != null && mSubscription.hasSubscriptions()) {
            this.mSubscription.unsubscribe();
        }


        if (this.mDisposable != null && mDisposable.isDisposed()) {
            this.mDisposable.clear();
            this.mDisposable = null;
        }
        super.onDestroy();
    }


    public   void setCheckBoxStatus(Context mContext, boolean isChecked, TextView textView){
        textView.setClickable(isChecked);
        int resBgImport = isChecked ? R.drawable.shape_corner_btn_main_r6 : R.drawable.shape_btn_grave;
        int resTxColor = isChecked ? mContext.getResources().getColor(R.color.white) : getResources().getColor(R.color.color_btn_text_un_click);
        textView.setTextColor(resTxColor);
        textView.setBackgroundResource(resBgImport);
    }
}
