package com.ocpay.wallet.widget;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;

/**
 * Created by y on 2017/12/5.
 */

public class CustomDefaultSliderView extends BaseSliderView {
    private ImageLoadListener mLoadListener;


    public CustomDefaultSliderView(Context context) {
        super(context);
    }

    @Override
    public View getView() {
        View v = LayoutInflater.from(getContext()).inflate(com.daimajia.slider.library.R.layout.render_type_default, null);
        ImageView target = (ImageView) v.findViewById(com.daimajia.slider.library.R.id.daimajia_slider_image);
        bindEventAndShow(v, target);
        return v;
    }


    /**
     * When you want to implement your own slider view, please call this method in the end in `getView()` method
     *
     * @param v               the whole view
     * @param targetImageView where to place image
     */
    protected void bindEventAndShow(final View v, ImageView targetImageView) {
        final CustomDefaultSliderView me = this;

        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnSliderClickListener != null) {
                    mOnSliderClickListener.onSliderClick(me);
                }
            }
        });

        if (targetImageView == null)
            return;

        if (mLoadListener != null) {
            mLoadListener.onStart(me);
        }

        Glide.with(getContext())
                .load(getUrl())
                .centerCrop()
                .listener(new RequestListener<String, GlideDrawable>() {
                    @Override
                    public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                        if (v.findViewById(com.daimajia.slider.library.R.id.loading_bar) != null) {
                            v.findViewById(com.daimajia.slider.library.R.id.loading_bar).setVisibility(View.INVISIBLE);
                        }
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                        if (v.findViewById(com.daimajia.slider.library.R.id.loading_bar) != null) {
                            v.findViewById(com.daimajia.slider.library.R.id.loading_bar).setVisibility(View.INVISIBLE);
                        }
                        return false;
                    }
                })
                .into(targetImageView);



//        Picasso p = (mPicasso != null) ? mPicasso : Picasso.with(mContext);
//        RequestCreator rq = null;
//        if (mUrl != null) {
//            rq = p.load(mUrl);
//        } else if (mFile != null) {
//            rq = p.load(mFile);
//        } else if (mRes != 0) {
//            rq = p.load(mRes);
//        } else {
//            return;
//        }
//
//        if (rq == null) {
//            return;
//        }
//
//        if (getEmpty() != 0) {
//            rq.placeholder(getEmpty());
//        }
//
//        if (getError() != 0) {
//            rq.error(getError());
//        }
//
//        switch (mScaleType) {
//            case Fit:
//                rq.fit();
//                break;
//            case CenterCrop:
//                rq.fit().centerCrop();
//                break;
//            case CenterInside:
//                rq.fit().centerInside();
//                break;
//        }
//
//        rq.into(targetImageView, new Callback() {
//            @Override
//            public void onSuccess() {
//                if (v.findViewById(com.daimajia.slider.library.R.id.loading_bar) != null) {
//                    v.findViewById(com.daimajia.slider.library.R.id.loading_bar).setVisibility(View.INVISIBLE);
//                }
//            }
//
//            @Override
//            public void onError() {
//                if (mLoadListener != null) {
//                    mLoadListener.onEnd(false, me);
//                }
//                if (v.findViewById(com.daimajia.slider.library.R.id.loading_bar) != null) {
//                    v.findViewById(com.daimajia.slider.library.R.id.loading_bar).setVisibility(View.INVISIBLE);
//                }
//            }
//        });
    }


    public void setOnImageLoadListener(ImageLoadListener l) {
        mLoadListener = l;
    }
}
