package com.immymemine.kevin.tablayout;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

/**
 * Created by quf93 on 2017-09-28.
 */

public class Two extends FrameLayout {
    public Two(Context context) {
        super(context);
        initView();
    }

    public Two(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView();
    }
    private void initView() {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_fragment_two, null);
        addView(view);
    }
}
