package com.android.yapp.scenetalker;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class ViewPagerIndicatorView extends LinearLayout {
    private Context mContext;
    private ImageView[] dotImages;
    private int mDefaultDot;
    private int mSelectedDot;

    public ViewPagerIndicatorView(Context context) {
        super(context);

        mContext = context;
    }

    public ViewPagerIndicatorView(Context context, AttributeSet attrs) {
        super(context, attrs);

        mContext = context;
    }

    public void init(int count, int defaultDot, int selectedDot, int margin) {
        this.removeAllViews();

        dotImages = new ImageView[count];
        mDefaultDot = defaultDot;
        mSelectedDot = selectedDot;

        for (int i=0;i<count;i++) {
            dotImages[i] = new ImageView(mContext);

            /*
            final int width = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 8, getResources().getDisplayMetrics());
            final int height = (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 8, getResources().getDisplayMetrics());
            LayoutParams params = new LayoutParams(width,height);
            */
            LayoutParams params = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);

            params.topMargin = 0;
            params.bottomMargin = 0;
            params.leftMargin = 0;
            if (i == count - 1) params.rightMargin = 0;
            else params.rightMargin = margin;
            params.gravity = Gravity.CENTER;

            dotImages[i].setLayoutParams(params);
            dotImages[i].setImageResource(defaultDot);
            dotImages[i].setTag(dotImages[i].getId(), false);
            this.addView(dotImages[i]);
        }

        setSelection(0);
    }

    public void setSelection(int position) {
        for (int i=0;i<dotImages.length;i++) {
            if (i == position) {
                dotImages[i].setImageResource(mSelectedDot);
            } else {
                dotImages[i].setImageResource(mDefaultDot);
            }
        }
    }
}