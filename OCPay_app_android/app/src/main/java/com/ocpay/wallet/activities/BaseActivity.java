package com.ocpay.wallet.activities;

import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;

import com.ocpay.wallet.widget.LoadingDialog;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

public class BaseActivity extends AppCompatActivity {
    private CompositeSubscription mSubscription;
    private CompositeDisposable mDisposable;
    private LoadingDialog loadingDialog;


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
    protected void onDestroy() {
        if (mSubscription != null && mSubscription.hasSubscriptions()) {
            mSubscription.unsubscribe();
        }
        if (mDisposable != null) mDisposable.clear();

        if (loadingDialog != null && loadingDialog.isShowing()) {
            loadingDialog.dismiss();
        }
        super.onDestroy();

    }


    public void showLoading(boolean hasBackground) {
        if (loadingDialog != null && loadingDialog.isShowing()) {
            loadingDialog.dismiss();
            loadingDialog = null;
        }
        loadingDialog = new LoadingDialog(this);
        loadingDialog.show();
    }

    public void setCancel(boolean isCancel) {
        if (loadingDialog != null && loadingDialog.isShowing()) {
            loadingDialog.setCance(isCancel);
        }
    }

    public void setTip(String txt) {
        if (loadingDialog != null && !TextUtils.isEmpty(txt)) {
            loadingDialog.setText(txt);
        }
    }

    public void dismissLoading() {
        if (loadingDialog != null) {
            loadingDialog.dismiss();
        }
    }
}
