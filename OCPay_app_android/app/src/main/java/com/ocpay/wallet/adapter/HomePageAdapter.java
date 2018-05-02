package com.ocpay.wallet.adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.ocpay.wallet.R;
import com.ocpay.wallet.adapter.viewholder.RecycleViewHolder;
import com.ocpay.wallet.adapter.viewholder.WheelAdHolder;
import com.ocpay.wallet.bean.home.Banner;
import com.ocpay.wallet.bean.home.HomeBean;
import com.ocpay.wallet.view.CustomDefaultSliderView;
import com.snow.commonlibrary.recycleview.BaseAdapter;

import static com.ocpay.wallet.Constans.HOME.GENERALIZE;
import static com.ocpay.wallet.Constans.HOME.MERCHANT;
import static com.ocpay.wallet.Constans.HOME.WHEEL_AD;


/**
 * Created by y on 2017/11/17.
 */

public class HomePageAdapter extends BaseAdapter<HomeBean, RecyclerView.ViewHolder> {


    public HomePageAdapter(Context ctx) {
        super(ctx);
    }

    @Override
    protected void bindViewHolderData(RecyclerView.ViewHolder viewHolder, final HomeBean data, int position) {
        if (viewHolder instanceof WheelAdHolder && getItemViewType(position) == WHEEL_AD) {
            WheelAdHolder mWheelAdHolder = (WheelAdHolder) viewHolder;
            mWheelAdHolder.sliderLayout.removeAllSliders();
            if (data.getBannerBean().getBannerList().size() == 0) return;

            for (Banner banner : data.getBannerBean().getBannerList()) {
                CustomDefaultSliderView defaultSliderView = new CustomDefaultSliderView(mCtx);
                defaultSliderView.setOnSliderClickListener(new BaseSliderView.OnSliderClickListener() {
                    @Override
                    public void onSliderClick(BaseSliderView slider) {
                        //todo jump or do something
//                        SimpleWebViewActivity.startWebViewActivity(slider.getBundle().getString("url"), mCtx);
                    }
                });
                Bundle bundle = new Bundle();
                bundle.putString("url", banner.getLink());
                defaultSliderView
                        .bundle(bundle)
                        .setScaleType(BaseSliderView.ScaleType.CenterCrop)
                        .image(banner.getMainImage());
                mWheelAdHolder.sliderLayout.addSlider(defaultSliderView);
            }
            mWheelAdHolder.sliderLayout.setCustomIndicator(mWheelAdHolder.indicator);
            mWheelAdHolder.setIsRecyclable(false);
        } else
//
            if (viewHolder instanceof RecycleViewHolder && getItemViewType(position) == MERCHANT) {
                MerchantAdapter itemAdapter = new MerchantAdapter(mCtx);
                if (data.getGoodsBean().getPageItems() == null || data.getGoodsBean().getPageItems().size() == 0) {
                    return;
                }
                itemAdapter.setData(data.getGoodsBean().getPageItems());
                ((RecycleViewHolder) viewHolder).recyclerView.setAdapter(itemAdapter);
            } else if (viewHolder instanceof RecycleViewHolder && getItemViewType(position) == GENERALIZE) {
                GeneralizeAdapter itemAdapter = new GeneralizeAdapter(mCtx);
                itemAdapter.setData(data.getGeneralizeBean().getPageItems());
                ((RecycleViewHolder) viewHolder).recyclerView.setAdapter(itemAdapter);
                ((RecycleViewHolder) viewHolder).llHomeItem.setBackgroundColor(mCtx.getResources().getColor(R.color.color_concrete_background));


            }
//        else if (viewHolder instanceof BannerHolder && getItemViewType(position) == BANNER.ordinal()) {
//            Log.i("snow_hehe", "what fuck" + data.getBannerBean().getBannerList().size());
//            if (data.getBannerBean().getBannerList().size() == 0) return;
////            Picasso.with(mCtx)
////                    .load(data.getBannerBean().getBannerList().get(0).getMainImage())
////                    .fit()
////                    .centerCrop()
////                    .into((((BannerHolder) viewHolder).roundedImageView));
//            Glide.with(mCtx)
//                    .load(data.getBannerBean().getBannerList().get(0).getMainImage())
//                    .centerCrop()
//                    .into((((BannerHolder) viewHolder).roundedImageView));
//            final String url = data.getBannerBean().getBannerList().get(0).getLink();
//            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    // SimpleWebViewActivity.startWebViewActivity(url, mCtx);
//                    Intent intent = new Intent(mCtx, StartActivity.class);
//                    intent.putExtra("flag", 2);
//                    mCtx.startActivity(intent);
//                }
//            });
//    }

    }


    @Override
    public int getItemViewType(int position) {
        return getmData().get(position).getType();
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == MERCHANT) {
            View view = LayoutInflater.from(mCtx).inflate(R.layout.fragment_home_item_recycleview, null, false);
            RecycleViewHolder mainItemHolder = new RecycleViewHolder(view);
            mainItemHolder.recyclerView.setLayoutManager(new GridLayoutManager(mCtx, 3, FullyGridLayoutManager.VERTICAL, false));
            return mainItemHolder;
        } else if (viewType == WHEEL_AD) {
            View view = LayoutInflater.from(mCtx).inflate(R.layout.item_wheel_ad, null, false);
            WheelAdHolder wheelAdHolder = new WheelAdHolder(view);
            return wheelAdHolder;
        } else if (viewType == GENERALIZE) {
            View view = LayoutInflater.from(mCtx).inflate(R.layout.fragment_home_item_recycleview, null, false);
            RecycleViewHolder mainItemHolder = new RecycleViewHolder(view);
            view.findViewById(R.id.view_top_space).setVisibility(View.VISIBLE);
            mainItemHolder.recyclerView.setLayoutManager(new GridLayoutManager(mCtx, 2, FullyGridLayoutManager.VERTICAL, false));
            mainItemHolder.recyclerView.addItemDecoration(new SpaceItemDecoration(mCtx, 0));
            return mainItemHolder;
        }
        return null;
    }
}
