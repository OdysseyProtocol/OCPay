package com.ocpay.wallet.activities;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.databinding.DataBindingUtil;
import android.graphics.PointF;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.dlazaro66.qrcodereaderview.QRCodeReaderView;
import com.dlazaro66.qrcodereaderview.QRCodeReaderView.OnQRCodeReadListener;
import com.ocpay.wallet.R;
import com.ocpay.wallet.databinding.ActivityDecoderBinding;
import com.ocpay.wallet.databinding.ContentDecoderBinding;
import com.ocpay.wallet.http.rx.RxBus;

public class QRReaderActivity extends BaseActivity
        implements ActivityCompat.OnRequestPermissionsResultCallback, OnQRCodeReadListener {

    private static final int MY_PERMISSION_REQUEST_CAMERA = 0;
    public static final int QR_CODE_MODE_PARSE = 1;
    public static final int QR_CODE_MODE_READER = 2;
    public static final String QR_REQEUST_CODE = "QR_REQEUST_CODE";
    public static final String QR_REQEUST_MODE = "QR_REQEUST_MODE";

    private ViewGroup mainLayout;

    private QRCodeReaderView qrCodeReaderView;
    private CheckBox flashlightCheckBox;
    private ActivityDecoderBinding dataBinding;
    private ContentDecoderBinding contentDecoderBinding;


    public static void startQRReaderActivity(Activity context, int requestCode, int requestMode) {
        Intent intent = new Intent(context, QRReaderActivity.class);
        intent.putExtra(QR_REQEUST_CODE, requestCode);
        intent.putExtra(QR_REQEUST_MODE, requestMode);
        context.startActivity(intent);

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_decoder);

        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_decoder);

        mainLayout = dataBinding.mainLayout;

        init();

    }

    private void init() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                == PackageManager.PERMISSION_GRANTED) {
            initQRCodeReaderView();
        } else {
            requestCameraPermission();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (qrCodeReaderView != null) {
            qrCodeReaderView.startCamera();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (qrCodeReaderView != null) {
            qrCodeReaderView.stopCamera();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        if (requestCode != MY_PERMISSION_REQUEST_CAMERA) {
            return;
        }

        if (grantResults.length == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            Snackbar.make(mainLayout, "Camera permission was granted.", Snackbar.LENGTH_SHORT).show();
            initQRCodeReaderView();
        } else {
            Snackbar.make(mainLayout, "Camera permission request was denied.", Snackbar.LENGTH_SHORT)
                    .show();
        }
    }

    @Override
    public void onQRCodeRead(String text, PointF[] points) {
        int requestCode = getIntent().getIntExtra(QR_REQEUST_CODE, -1);
        int requestMode = getIntent().getIntExtra(QR_REQEUST_MODE, -1);
        if (requestMode == QR_CODE_MODE_READER) {
            RxBus.getInstance().post(requestCode, text);
        }
        finish();
    }

    private void requestCameraPermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CAMERA)) {
            Snackbar.make(mainLayout, "Camera access is required to display the camera preview.",
                    Snackbar.LENGTH_INDEFINITE).setAction("OK", new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ActivityCompat.requestPermissions(QRReaderActivity.this, new String[]{
                            Manifest.permission.CAMERA
                    }, MY_PERMISSION_REQUEST_CAMERA);
                }
            }).show();
        } else {
            Snackbar.make(mainLayout, "Permission is not available. Requesting camera permission.",
                    Snackbar.LENGTH_SHORT).show();
            ActivityCompat.requestPermissions(this, new String[]{
                    Manifest.permission.CAMERA
            }, MY_PERMISSION_REQUEST_CAMERA);
        }
    }

    private void initQRCodeReaderView() {
        contentDecoderBinding = DataBindingUtil.inflate(getLayoutInflater(), R.layout.content_decoder, mainLayout, true);
        qrCodeReaderView = contentDecoderBinding.qrdecoderview;
        flashlightCheckBox = contentDecoderBinding.flashlightCheckbox;
        qrCodeReaderView.setAutofocusInterval(2000L);
        qrCodeReaderView.setOnQRCodeReadListener(this);
        qrCodeReaderView.setBackCamera();
        flashlightCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                qrCodeReaderView.setTorchEnabled(isChecked);
            }
        });

        qrCodeReaderView.startCamera();
    }
}