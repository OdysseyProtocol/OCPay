package com.ocpay.wallet.adapter.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.daimajia.slider.library.Indicators.PagerIndicator;
import com.daimajia.slider.library.SliderLayout;
import com.ocpay.wallet.R;


/**
 * Created by y on 2017/11/17.
 */

public class WheelAdHolder extends RecyclerView.ViewHolder {

    public SliderLayout sliderLayout;
    public PagerIndicator indicator;

    public WheelAdHolder(final View itemView) {
        super(itemView);
        sliderLayout = itemView.findViewById(R.id.slider);
        indicator = itemView.findViewById(R.id.indicator);
        //进行搜索页跳转
//        itemView.findViewById(R.id.iv_btn_search).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (Test.testUploadPic){
//                    itemView.getContext().startActivity(new Intent(itemView.getContext(), TestUploadPicActivity.class));
//                }else {
////                    itemView.getContext().startActivity(new Intent(itemView.getContext(),RequestLoanTabActivity.class));
//
//                    startSearchActivity(itemView.getContext());
//                }
//
//            }
//        });
//        itemView.findViewById(R.id.iv_btn_user).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
////                itemView.getContext().
//                if(SPUtils.getAccessToken(itemView.getContext()).isEmpty()){
////                    LoginActivity log = new LoginActivity();
////                    log.startLoginActivity(itemView.getContext(), new LoginCallBack() {
////                        @Override
////                        public void loginSuccess() {
//////                            itemView.getContext().startActivity(new Intent(itemView.getContext(),UserSettingActivity.class));
////                        }
////                    });
//                    itemView.getContext().startActivity(new Intent(itemView.getContext(),LoginActivity.class));
//
//
//
//                }else{
//                    itemView.getContext().startActivity(new Intent(itemView.getContext(),PersonalCenterActivity.class));
//
//                }
//            }
//        });
    }


}
