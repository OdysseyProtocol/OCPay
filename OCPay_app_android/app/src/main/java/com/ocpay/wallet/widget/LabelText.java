package com.ocpay.wallet.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.MotionEvent;
import android.widget.TextView;

import com.ocpay.wallet.R;

import static com.snow.commonlibrary.utils.ViewUtils.dp2px;

/**
 * Created by y on 2017/11/22.
 */

@SuppressLint("AppCompatCustomView")
public class LabelText extends TextView {
    private static final int PADDING_WIDTH_DP = 11;
    private static final int PADDING_HEIGHT_DP = 6;
    private boolean hasExpend;
    private boolean selected;


    public static final int MODE_SELECTED = 1;
    public static final int MODE_UN_SELECT = 2;
    public static final int MODE_ADDED = 3;
    private int currentMode;


    public LabelText(Context context) {
        this(context, null, -1);
    }

    public LabelText(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, -1);

    }


    public LabelText(Context context, String world, int mode, boolean selected) {

        this(context, null, -1);
        setTag(world);

        setText(world);

        initView(mode);

    }

    private void initView(int mode) {
        currentMode = mode;
        int bgDrawable = mode == MODE_SELECTED ? R.drawable.shape_btn_selected :
                mode == MODE_UN_SELECT ? R.drawable.shape_base_line : R.drawable.shape_corner_white;
        int textColor = mode == MODE_SELECTED ? R.color.white : R.color.color_text_main;
        setBackgroundResource(bgDrawable);
        setTextColor(getResources().getColor(textColor));
    }


    public LabelText(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setBackgroundResource(R.drawable.shape_corner_white);
        setGravity(Gravity.CENTER);
        setGravity(Gravity.CENTER);
        setTextAlignment(TEXT_ALIGNMENT_CENTER);
        int paddingWidth = dp2px(getContext(), PADDING_WIDTH_DP);
        int paddingHeight = dp2px(getContext(), PADDING_HEIGHT_DP);
        setPadding(paddingWidth, paddingHeight, paddingWidth, paddingHeight);
        setTextColor(getResources().getColor(R.color.color_text_main));
//        setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                PrefOperationUtils.putSearchHis(getContext(), getText().toString());
//                String url = Constants.BaseUrl.AmazonSearchUrl + getText().toString();
//                SimpleWebViewActivity.startWebViewActivity(url, getContext());
//                if (getContext() instanceof Activity) {
//                    ((Activity) getContext()).finish();
//                }
//            }
//        });
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
//            case MotionEvent.ACTION_DOWN:
//                setTextColor(getResources().getColor(R.color.white));
//                setBackgroundResource(R.drawable.label_bg_press);
//                break;
//            case MotionEvent.ACTION_UP:
//                setTextColor(getResources().getColor(R.color.colorDefaultText));
//                setBackgroundResource(R.drawable.label_bg);
//                break;

        }
        return super.onTouchEvent(event);
    }


    @Override
    public boolean isSelected() {
        return selected;
    }

    @Override
    public void setSelected(boolean selected) {
        this.selected = selected;
        if (currentMode != MODE_ADDED) {
            initView(selected ? MODE_SELECTED : MODE_UN_SELECT);
        }
    }


}
