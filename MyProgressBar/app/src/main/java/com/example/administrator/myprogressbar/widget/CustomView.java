package com.example.administrator.myprogressbar.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Scroller;

public class CustomView extends View {

    private int lastX ;
    private int lastY ;

    private Scroller mScroll;
    public CustomView(Context context) {
        super(context);
    }

    public CustomView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mScroll = new Scroller(context);
    }

    public CustomView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }


    @Override
    public void computeScroll() {
        super.computeScroll();
        if (mScroll.computeScrollOffset()){
            ((View)getParent()).scrollTo(mScroll.getCurrX(),mScroll.getCurrY());
            invalidate();
        }

    }

    public void smoothScrollTo(int destX,int destY){
        int scrollX = getScrollX();
        int delta = destX -scrollX;
        mScroll.startScroll(scrollX,delta,0,2000);
        invalidate();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(widthMeasureSpec,heightMeasureSpec);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);



    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int x = (int) event.getX();
        int y = (int) event.getY();
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                lastX = x;
                lastY = y;
                break;

            case MotionEvent.ACTION_MOVE:
                //主要的六种滑动layout() offsetLeftAndRight() offsetTopAndBottom（) 、LayoutParams、动画、scollTo 与 scollBy，以及Scroller。
                int offsetX = x -lastX;
                int offsetY = y - lastY;

                //============================调用layout来重新放置他的布局
                //layout(getLeft()+offsetX,getTop()+offsetY,getRight()+offsetX,getBottom()+offsetY);

                //===========================使用offsetRightandBoom的方式
                offsetLeftAndRight(offsetX);offsetTopAndBottom(offsetY);

                //===========================使用layoutParams
              /*  LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) getLayoutParams();
                layoutParams.leftMargin = getLeft() + offsetX;
                layoutParams.topMargin  = getTop() + offsetY;
                setLayoutParams(layoutParams);*/
              //============================





                break;
        }



        return true;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        return super.dispatchTouchEvent(event);
    }
}
