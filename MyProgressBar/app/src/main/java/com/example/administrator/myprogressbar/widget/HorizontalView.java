package com.example.administrator.myprogressbar.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Scroller;

public class HorizontalView extends ViewGroup {

    private int lastX;
    private int lastY;
    private int currentIndex = 0;
    private int childWidth = 0;
    private Scroller scroller;

    private VelocityTracker velocityTracker;
    private int lastInterceptX;
    private int lastInterceptY;

    public HorizontalView(Context context) {
        super(context);
    }

    public HorizontalView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public HorizontalView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void init(){
        scroller = new Scroller(getContext());
        velocityTracker = VelocityTracker.obtain();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        //获取设置的属性
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        measureChildren(widthMeasureSpec,heightMeasureSpec);
        //如果没有子元素
        if (getChildCount() == 0){
            setMeasuredDimension(0,0);
        }
        //宽和高都是at_most,则设置宽度为所有子元素的宽度的和，这里没有考虑到子View的marign之类的
        else if (widthMode == MeasureSpec.AT_MOST && heightMode == MeasureSpec.AT_MOST){
            View childOne = getChildAt(0);
            int childWidth = childOne.getMeasuredWidth();
            int childHeight = childOne.getMeasuredHeight();
            setMeasuredDimension(childWidth*getChildCount(),childHeight);

        }else if (widthMode == MeasureSpec.AT_MOST){
            //宽度是AT_MOST 设置宽度为子View宽度之和
            setMeasuredDimension(getChildAt(0).getMeasuredWidth()*getChildCount(),heightSize);
        }else if (heightMode == MeasureSpec.AT_MOST){
            //高度为AT_MOST，则高度为第一个子元素的高度
            setMeasuredDimension(widthSize,getChildAt(0).getMeasuredHeight());
        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int childCount = getChildCount();
        int left = 0;
        View child;
        for (int i = 0;i < childCount;i++){
            child = getChildAt(i);
            if (child.getVisibility() != View.GONE){
                int width = child.getMeasuredWidth();
                childWidth = width;
                child.layout(left,0,left+width,child.getMeasuredHeight());
                left +=width;
            }
        }
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int x = (int) event.getX();
        int y = (int) event.getY();
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                break;

            case MotionEvent.ACTION_MOVE:
                int deltaX = x - lastX;
                scrollBy(-deltaX,0);
                break;
            case MotionEvent.ACTION_UP:

                //松手切换
                int distance  =  getScrollX() - currentIndex*childWidth;
                if (Math.abs(distance) > childWidth/2){
                    if (distance > 0){
                        currentIndex ++;
                    }else {
                        currentIndex -- ;
                    }
                }else{
                    velocityTracker.computeCurrentVelocity(1000);
                    float xV = velocityTracker.getXVelocity();
                    if (Math.abs(xV) > 50){

                        if (xV >0 ){
                            currentIndex -- ;
                        }else {
                            currentIndex ++;
                        }
                    }
                }

                currentIndex = currentIndex < 0?0:currentIndex > getChildCount()-1?getChildCount()-1:currentIndex;

                smoothScrollTo(currentIndex*childWidth,0);

                velocityTracker.clear();

                break;
        }

        return super.onTouchEvent(event);
    }

    //拦截水平滑动
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        //true代表拦截 不往下传递，false代表不拦截；
        boolean intercept = false;

        int x = (int) ev.getX();
        int y = (int) ev.getY();

        switch (ev.getAction()){
            case MotionEvent.ACTION_DOWN:
                //拦截滑动的时候点击
                intercept  = false;
                //点击事件往下传递
                if (!scroller.isFinished()){
                    scroller.abortAnimation();
                }
                break;
            case MotionEvent.ACTION_MOVE:
                int deltaX = x - lastInterceptX;
                int deltaY = y - lastInterceptY;
                if (Math.abs(deltaX) - Math.abs(deltaY) >0){
                    intercept  = true;
                }
                break;
            case MotionEvent.ACTION_UP:
                intercept = false;
                break;
        }
        lastX = x;
        lastY = y;
        lastInterceptX = x;
        lastInterceptY = y;
      //  return super.onInterceptTouchEvent(ev);
        return intercept;
    }

    public void smoothScrollTo(int destX , int destY){
        scroller.startScroll(getScrollX(),getScrollY(),destX-getScrollX(),destY - getScrollY(),1000);
        invalidate();
    }

    @Override
    public void computeScroll() {
        super.computeScroll();

        if (scroller.computeScrollOffset()){
            scrollTo(scroller.getCurrX(),scroller.getCurrY());
            postInvalidate();
        }

    }












}
