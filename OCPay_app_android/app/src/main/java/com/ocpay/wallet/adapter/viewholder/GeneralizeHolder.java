package com.ocpay.wallet.adapter.viewholder;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.ocpay.wallet.R;
import com.ocpay.wallet.bean.home.Generalize;


/**
 * Created by y on 2017/11/21.
 */

public class GeneralizeHolder extends RecyclerView.ViewHolder {


    private final CardView cardView;
    private final ImageView ivGeneralize;

    public GeneralizeHolder(View itemView) {
        super(itemView);
        cardView = itemView.findViewById(R.id.layout_card);
        ivGeneralize = itemView.findViewById(R.id.iv_generalize);
    }

    public void bindData(Generalize data, int position) {
        int imgSource = position % 2 != 0 ? R.mipmap.card_carnival : R.mipmap.card_join_us;
        ivGeneralize.setImageResource(imgSource);
    }
}
