package com.ocpay.wallet.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ocpay.wallet.R;
import com.ocpay.wallet.adapter.viewholder.GeneralizeHolder;
import com.ocpay.wallet.bean.home.Generalize;
import com.snow.commonlibrary.recycleview.BaseAdapter;


/**
 * Created by y on 2017/11/17.
 */

public class GeneralizeAdapter extends BaseAdapter<Generalize, RecyclerView.ViewHolder> {


    public GeneralizeAdapter(Context ctx) {
        super(ctx);
    }

    @Override
    protected void bindViewHolderData(RecyclerView.ViewHolder viewHolder, final Generalize data, final int position) {

        ((GeneralizeHolder) viewHolder).bindData(data, position);

    }


    @Override
    public int getItemViewType(int position) {
        return (getmData() == null) ? 0 : getmData().get(position).type;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(mCtx).inflate(R.layout.item_generalize, null, false);
        GeneralizeHolder Comm = new GeneralizeHolder(itemView);
        return Comm;
    }
}
