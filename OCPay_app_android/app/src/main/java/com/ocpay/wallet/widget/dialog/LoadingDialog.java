package com.ocpay.wallet.widget.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;

import com.ocpay.wallet.R;


/**
 * Created by Avazu Holding on 2017/12/11.
 */

public class LoadingDialog extends Dialog {

//    private ImageView progressImg;
    private TextView tvTxt;
    //帧动画
//    private AnimationDrawable animation;

    public LoadingDialog(Context context) {
        super(context, R.style.CustomDialog);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loading_layout);

        //点击imageview外侧区域，动画不会消失
        setCanceledOnTouchOutside(false);

//        progressImg = (ImageView) findViewById(R.id.imgLoading);
        tvTxt=(TextView)findViewById(R.id.tvTxt);
//        //加载动画资源
//        animation = (AnimationDrawable) progressImg.getDrawable();
    }

    public void setCance(boolean isCance){
        setCancelable(isCance);
    }

    public void setText(String txt){
        tvTxt.setText(txt);
    }


    /**
     * 在AlertDialog的 onStart() 生命周期里面执行开始动画
     */
    @Override
    protected void onStart() {
        super.onStart();
//        animation.start();
    }

    /**
     * 在AlertDialog的onStop()生命周期里面执行停止动画
     */
    @Override
    protected void onStop() {
        super.onStop();
//        animation.stop();
    }

}
