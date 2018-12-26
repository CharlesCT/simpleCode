package com.example.administrator.myprogressbar.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.administrator.myprogressbar.R;
import com.example.administrator.myprogressbar.utils.L;

public class LinerLayoutView extends ViewGroup {
    private static final int offset = 200;//默认长宽
    private float defaultMargin = 20;
    public LinerLayoutView(Context context) {
        super(context);
    }

    public LinerLayoutView(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray typedValue = context.obtainStyledAttributes(attrs,R.styleable.LinerLayoutView);
        defaultMargin = typedValue.getDimension(R.styleable.LinerLayoutView_child_margin,20);
        typedValue.recycle();
    }

    public LinerLayoutView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        //获取父容器的mode
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        //让子View测量自己的尺寸 不考虑marginTop之类的
        measureChildren(widthMeasureSpec,heightMeasureSpec);

        int withSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(widthMeasureSpec);
        int width = 0;
        int height = 0;
        int childCount = getChildCount();
        for (int i =0;i<childCount;i++){
            View child = getChildAt(i);
            if (child.getVisibility()==VISIBLE){
                ViewGroup.LayoutParams lp = child.getLayoutParams();
                int childWidthMeasureSpec = getChildMeasureSpec(widthMeasureSpec,0,lp.width);
                int childHeightMeasureSpec = getChildMeasureSpec(heightMeasureSpec,0,lp.width);
                child.measure(childWidthMeasureSpec,childHeightMeasureSpec);
            }
        }

        switch (widthMode){
            case MeasureSpec.EXACTLY:
                width = withSize;
                break;
            case MeasureSpec.AT_MOST:
            case MeasureSpec.UNSPECIFIED:
                int offtWidth = 0;
                for (int i=0;i<childCount;i++){
                    View child = getChildAt(i);
                    if (child.getVisibility() == VISIBLE){
                       offtWidth += child.getMeasuredWidth();
                    }
                }
                //子View加起来
                width = offtWidth +offset;
                L.v("vierGroup width is add : " + width);
                break;
        }

        switch (heightMode){
            case MeasureSpec.EXACTLY:
                height = heightSize;
                break;
            case MeasureSpec.AT_MOST:
            case MeasureSpec.UNSPECIFIED:
                int offtHeight = 0;
                for (int i=0;i<childCount;i++){
                    View child = getChildAt(i);
                    if (child.getVisibility() == VISIBLE){
                        offtHeight =  child.getMeasuredHeight();
                        //找出最高的
                        height = Math.max(offtHeight,offset);
                    }
                }
                break;
        }
        L.v("vierGroup width : " + width);
        setMeasuredDimension(width,height);
    }


    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int childCount = getChildCount();
        int left =0;
        int right =0;
        int top  = 0;
        int boom = 0;
        int width = getMeasuredWidth();//总的宽度
        int availableChildCount = 0;
        int childTotalWidth =0;
        for (int i=0;i<childCount;i++){
            View child = getChildAt(i);
            if (child.getVisibility() == VISIBLE){
                availableChildCount ++;
                childTotalWidth += child.getMeasuredWidth();
            }
        }
        double marginWidth = (width - childTotalWidth)/(availableChildCount+1);
        for (int i=0;i<childCount;i++){
            View child = getChildAt(i);
            final LinearLayout.LayoutParams lp =
                    (LinearLayout.LayoutParams) child.getLayoutParams();
            if (child.getVisibility() == VISIBLE){
                top = lp.topMargin;
                int childWidth = child.getMeasuredWidth();
                int childHeight = child.getMeasuredHeight();
                left += marginWidth;
                L.v("child start left: " + left +" child width : " + childWidth);
                right = left + childWidth;
                L.v("child start right: " + right);
                boom = top + childHeight;
                child.layout(left,top,right,boom);
                left = right;
                L.v("child end left: " + left);

            }
        }


    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new LinearLayout.LayoutParams(getContext(), attrs);
    }
}
