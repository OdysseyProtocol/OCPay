package com.ocpay.wallet.utils.qr;

import android.graphics.Bitmap;
import android.widget.ImageView;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.ocpay.wallet.MyApp;
import com.snow.commonlibrary.utils.ViewUtils;

/**
 * Created by y on 2018/5/4.
 */

public class QRCodeUtils {


    /**
     * @param imageView widget to fill with qr code
     * @param dimension qr code dimension
     * @param code      qr code
     */
    public static void updateQRCode(ImageView imageView, int dimension, String code) {
        if (dimension <= 0) {
            dimension = 100;
        }
        int qrCodeDimension = ViewUtils.dp2px(MyApp.getContext(), dimension);
        QREncoder qrCodeEncoder = new QREncoder(code, null,
                Contents.Type.TEXT, BarcodeFormat.QR_CODE.toString(), qrCodeDimension);
        try {
            Bitmap bitmap = qrCodeEncoder.encodeAsBitmap();
            imageView.setImageBitmap(bitmap);
        } catch (WriterException e) {
            e.printStackTrace();
        }
    }
}
