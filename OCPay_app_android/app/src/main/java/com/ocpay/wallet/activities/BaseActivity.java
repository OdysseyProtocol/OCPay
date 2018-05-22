package com.ocpay.wallet.activities;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;

import com.ocpay.wallet.widget.dialog.LoadingDialog;

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

    public void getPermission() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) return;
        //当前系统大于等于6.0
        if (!(ContextCompat.checkSelfPermission(BaseActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED)) {
            ActivityCompat.requestPermissions(BaseActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.INTERNET}, 10086);
        }
    }
}
