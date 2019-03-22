package com.jenking.graduatesinternship.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.jenking.graduatesinternship.R;

public class CustomRelativeLayout extends RelativeLayout {
    public CustomRelativeLayout(Context context) {
        this(context,null);
    }

    public CustomRelativeLayout(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public CustomRelativeLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        View inflate = inflate(getContext(), R.layout.layout_empty_data, this);

        ImageView emptyIcon = new ImageView(context);
        emptyIcon.setImageDrawable(getResources().getDrawable(R.mipmap.icon_data_empty));

        addView(emptyIcon,1);
        emptyIcon.bringToFront();
    }
}
