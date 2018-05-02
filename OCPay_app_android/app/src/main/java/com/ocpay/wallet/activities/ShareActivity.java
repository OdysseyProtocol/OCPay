package com.ocpay.wallet.activities;

import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.ocpay.wallet.R;
import com.ocpay.wallet.databinding.ActivityShareBinding;
import com.ocpay.wallet.utils.qr.Contents;
import com.ocpay.wallet.utils.qr.QREncoder;
import com.snow.commonlibrary.log.MyLog;

/**
 * Created by y on 2018/4/20.
 */

public class ShareActivity extends BaseActivity {

    private ActivityShareBinding shareBinding;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        shareBinding = DataBindingUtil.setContentView(this, R.layout.activity_share);

        float scale = getResources().getDisplayMetrics().density;
        int qrCodeDimention = (int) (310 * scale + 0.5f);

        QREncoder qrCodeEncoder = new QREncoder("发数据库", null,
                Contents.Type.TEXT, BarcodeFormat.QR_CODE.toString(), qrCodeDimention);

        MyLog.i("--"+qrCodeDimention);
        Bitmap bitmap = null;
        try {
            bitmap = qrCodeEncoder.encodeAsBitmap();
        } catch (WriterException e) {
            e.printStackTrace();
        }
        shareBinding.qrCode.setImageBitmap(bitmap);
    }


}
