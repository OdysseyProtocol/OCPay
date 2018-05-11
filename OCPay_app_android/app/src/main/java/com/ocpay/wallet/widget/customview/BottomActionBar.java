package com.ocpay.wallet.widget.customview;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;

import com.ocpay.wallet.R;
import com.ocpay.wallet.databinding.LayoutBottomActionbarBinding;


public class BottomActionBar extends RelativeLayout {

    public static final int TYPE_BOTTOM_ACTION_HOME = 0;
    public static final int TYPE_BOTTOM_ACTION_MARKETS = 1;
    public static final int TYPE_BOTTOM_ACTION_NEARBY = 2;
    public static final int TYPE_BOTTOM_ACTION_AIRDROP = 3;
    public static final int TYPE_BOTTOM_ACTION_ME = 4;


    private OnBottomActionBarItemClickListener onBottomActionBarItemClickListener;
    private LayoutBottomActionbarBinding binding;

    public interface OnBottomActionBarItemClickListener {
        void selected(int type);
    }

    public BottomActionBar(Context context) {
        this(context, null);
    }

    public BottomActionBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public void setOnBottomActionBarItemClickListener(OnBottomActionBarItemClickListener onBottomActionBarItemClickListener) {
        this.onBottomActionBarItemClickListener = onBottomActionBarItemClickListener;
    }

    private void init(Context context) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);


        binding = DataBindingUtil.inflate(inflater, R.layout.layout_bottom_actionbar, this, true);

        setListeners();

        resetSelectedState();

        selected(TYPE_BOTTOM_ACTION_HOME);
    }


    public void selected(int selected) {
        resetSelectedState();
        int color = getContext().getResources().getColor(R.color.color_bar_bottom_text_press);
        switch (selected) {
            case TYPE_BOTTOM_ACTION_HOME:
                binding.ivHome.setImageResource(R.mipmap.ic_home_press);
                binding.tvHome.setTextColor(color);
                break;
            case TYPE_BOTTOM_ACTION_MARKETS:
                binding.ivMarket.setImageResource(R.mipmap.ic_markets_press);
                binding.tvMarket.setTextColor(color);
                break;
            case TYPE_BOTTOM_ACTION_NEARBY:
                binding.ivNearby.setImageResource(R.mipmap.ic_nearby_press);
                binding.tvNearby.setTextColor(color);
                break;
            case TYPE_BOTTOM_ACTION_AIRDROP:
                binding.ivAirdrop.setImageResource(R.mipmap.ic_airdrop_press);
                binding.tvAirdrop.setTextColor(color);
                break;
            case TYPE_BOTTOM_ACTION_ME:
                binding.ivMe.setImageResource(R.mipmap.ic_me_press);
                binding.tvMe.setTextColor(color);
                break;
        }
    }

    private void resetSelectedState() {
        int color = getContext().getResources().getColor(R.color.color_bar_bottom_text);
        binding.ivHome.setImageResource(R.mipmap.ic_home);
        binding.ivAirdrop.setImageResource(R.mipmap.ic_airdrop);
        binding.ivMe.setImageResource(R.mipmap.ic_me);
        binding.ivNearby.setImageResource(R.mipmap.ic_nearby);
        binding.ivMarket.setImageResource(R.mipmap.ic_markets);
        binding.tvHome.setTextColor(color);
        binding.tvMe.setTextColor(color);
        binding.tvAirdrop.setTextColor(color);
        binding.tvNearby.setTextColor(color);
        binding.tvMarket.setTextColor(color);
    }

    private void setListeners() {

        binding.layoutBtnHome.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onBottomActionBarItemClickListener != null) {
                    onBottomActionBarItemClickListener.selected(TYPE_BOTTOM_ACTION_HOME);
                }
                selected(TYPE_BOTTOM_ACTION_HOME);
            }
        });
        binding.layoutBtnMarket.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onBottomActionBarItemClickListener != null) {
                    onBottomActionBarItemClickListener.selected(TYPE_BOTTOM_ACTION_MARKETS);
                }
                selected(TYPE_BOTTOM_ACTION_MARKETS);
            }
        });
        binding.layoutBtnAirdrop.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onBottomActionBarItemClickListener != null) {
                    onBottomActionBarItemClickListener.selected(TYPE_BOTTOM_ACTION_AIRDROP);
                }
                selected(TYPE_BOTTOM_ACTION_AIRDROP);
            }
        });
        binding.layoutBtnMe.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onBottomActionBarItemClickListener != null) {
                    onBottomActionBarItemClickListener.selected(TYPE_BOTTOM_ACTION_ME);
                }
                selected(TYPE_BOTTOM_ACTION_ME);
            }
        });
        binding.layoutBtnNearby.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onBottomActionBarItemClickListener != null) {
                    onBottomActionBarItemClickListener.selected(TYPE_BOTTOM_ACTION_NEARBY);
                }
                selected(TYPE_BOTTOM_ACTION_NEARBY);
            }
        });

    }


}

