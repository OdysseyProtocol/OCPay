package com.ocpay.wallet.adapter;

import android.content.Context;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import static com.snow.commonlibrary.utils.ViewUtils.dp2px;


/**
 * Created by y on 2017/6/26.
 */

public class SpaceItemDecoration extends RecyclerView.ItemDecoration {
    private int space;
    private final Context mContext;

    public SpaceItemDecoration(Context context, int space) {
        mContext = context;
        this.space = dp2px(context, space);

    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        // Add top margin only for the first item to avoid double space between items
        if (parent.getChildLayoutPosition(view) == 0) {
            outRect.left = dp2px(mContext, 10);
            outRect.right = dp2px(mContext, 5);
            return;
        }

        if (parent.getChildLayoutPosition(view) == (parent.getChildCount() - 1)) {
            outRect.left = dp2px(mContext, 5);
            outRect.right = dp2px(mContext, 10);

        }

    }


}