package com.example.administrator.myprogressbar.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.LinearLayout;

public class LinearLayoutInterceptTouchEvent extends LinearLayout {
    public LinearLayoutInterceptTouchEvent(Context context) {
        super(context);
    }

    public LinearLayoutInterceptTouchEvent(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public LinearLayoutInterceptTouchEvent(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

    }

    /**
     * 这个方法可以拦截事件往下传递
     * @param ev
     * @return
     */
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return super.onInterceptTouchEvent(ev);
    }
}
