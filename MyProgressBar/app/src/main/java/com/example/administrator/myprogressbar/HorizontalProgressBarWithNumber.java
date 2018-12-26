package com.example.administrator.myprogressbar;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.ProgressBar;

/**
 * Created by Administrator on 2017/2/22.
 */

public class HorizontalProgressBarWithNumber extends ProgressBar {
    private static final int DEFAULT_TEXT_SIZE = 10;

    private static final int DEFAULT_TEXT_COLOR = 0XFFFC00D1;
    private static final int DEFAULT_COLOR_UNREACHED_COLOR = 0XFFd3d6da;
    private static final int DEFAULT_HEIGHT_REACHED_PROGRESS_BAR = 2;
    private static final int DEFAULT_HEIGHT_UNREACHED_PROGRESS_BAR = 2;
    private static final int DEFAULT_SIZE_TEXT_OFFSET = 0;

    //painter of all drawing things
    protected Paint mPaint = new Paint();

    //color of progress number

    protected int mTextColor  = DEFAULT_TEXT_COLOR;

    //size of text(sp)
    protected int mTextSize = sp2px(DEFAULT_TEXT_SIZE);

    protected int mTextOffset = dp2px(DEFAULT_SIZE_TEXT_OFFSET);

    protected int mReachedProgressBarHeight = dp2px(DEFAULT_HEIGHT_REACHED_PROGRESS_BAR);

    private int dp2px(int dpVal) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,dpVal,getResources().getDisplayMetrics());
    }

    protected int mReachedBarColor = DEFAULT_TEXT_COLOR;

    protected int mUnReachedBarColor = DEFAULT_COLOR_UNREACHED_COLOR;

    protected int mUnReachedProgressBarHeight = dp2px(DEFAULT_HEIGHT_UNREACHED_PROGRESS_BAR);

    protected int mRealWidth;
    protected boolean mIfDrawText = true;
    protected static final int VISIBLE = 0;

    protected int sp2px(int spVal)
    {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,
                spVal, getResources().getDisplayMetrics());

    }
    public HorizontalProgressBarWithNumber(Context context , AttributeSet attrs) {

        super(context,attrs,0);

    }
    public HorizontalProgressBarWithNumber(Context context , AttributeSet attrs,int defStyle) {

        super(context,attrs,defStyle);
        setHorizontalFadingEdgeEnabled(true);
        obtainStyledAttributes(attrs);
        mPaint.setTextSize(mTextSize);
        mPaint.setColor(mTextColor);
    }
    //get style attributes
    private void obtainStyledAttributes(AttributeSet attrs){
        final TypedArray attributes = getContext().obtainStyledAttributes(attrs,R.styleable.HorizontalProgressBarWithNumber);
        //获取自定义属性
        mTextColor = attributes.getColor(R.styleable.HorizontalProgressBarWithNumber_progress_text_color,DEFAULT_TEXT_COLOR);

        mTextSize  = (int) attributes.getDimension(R.styleable.HorizontalProgressBarWithNumber_progress_text_size,DEFAULT_TEXT_SIZE);

        mReachedBarColor = attributes.getColor(R.styleable.HorizontalProgressBarWithNumber_progress_reached_color,mTextColor);

        mUnReachedBarColor = attributes.getColor(R.styleable.HorizontalProgressBarWithNumber_progress_unreached_color,DEFAULT_COLOR_UNREACHED_COLOR);

        mReachedProgressBarHeight = (int) attributes.getDimension(R.styleable.HorizontalProgressBarWithNumber_progress_reached_bar_height,DEFAULT_HEIGHT_REACHED_PROGRESS_BAR);
        mUnReachedProgressBarHeight = (int) attributes.getDimension(R.styleable.HorizontalProgressBarWithNumber_progress_unreached_bar_hight,DEFAULT_HEIGHT_UNREACHED_PROGRESS_BAR);

        mTextOffset = (int) attributes.getDimension(R.styleable.HorizontalProgressBarWithNumber_progress_text_offset,DEFAULT_SIZE_TEXT_OFFSET);

        int textVisible = (int) attributes.getDimension(R.styleable.HorizontalProgressBarWithNumber_progress_text_visibility,VISIBLE);

        if(textVisible !=VISIBLE ){
            mIfDrawText = false;
        }

        attributes.recycle();

    }

    @Override
    protected synchronized void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int heightMeasure =MeasureSpec.getMode(heightMeasureSpec);

        if(heightMeasure!=MeasureSpec.EXACTLY){
            float textHeight = (mPaint.descent() -  mPaint.ascent());
            int exceptHeight = (int) (getPaddingTop() + getPaddingBottom() +Math.max(Math.max(mReachedProgressBarHeight,mUnReachedProgressBarHeight),Math.abs(textHeight)));
            heightMeasureSpec = MeasureSpec.makeMeasureSpec(exceptHeight,MeasureSpec.EXACTLY);
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected synchronized void onDraw(Canvas canvas) {
        canvas.save();
        //画笔平移到指定的paddingLeft，getHeight/2的位置
        canvas.translate(getPaddingLeft(),getHeight()/2);
        boolean noNeedBg = false;
        //设置已经完成进度和当前总值的对比
        float radio = getProgress()*0.1f/getMax();
        //已经达到的高度
        float progressPosx = (int)(mRealWidth * radio);
        //绘制文本
        String text = getProgress() + "%";
        //String text = String.format("%.1f", radio*100) + "%";
        //拿到字体的宽度和高度
        float textWidth = mPaint.measureText(text);
        float textHeight = (mPaint.descent()  + mPaint.ascent())/2;
        //如果到达了最后，则为到达的
        /*if(progressPosx + textWidth > mRealWidth){
            progressPosx = mRealWidth - textWidth ;
            noNeedBg = true;
        }*/
        //绘制已经到达高度
        float endX = progressPosx - mTextOffset/2;
        if(endX>0){
            mPaint.setColor(mReachedBarColor);
            mPaint.setStrokeWidth(mReachedProgressBarHeight);
            canvas.drawLine(0,0,endX,0,mPaint);
        }
        //绘制文本
        if(mIfDrawText){
            mPaint.setColor(mTextColor);
            canvas.drawText(text,progressPosx,-textHeight,mPaint);
        }
        //绘制未达到的进度条
        if(!noNeedBg){
            float start = progressPosx + mTextOffset/2 + textWidth;
            mPaint.setColor(mUnReachedBarColor);
            mPaint.setStrokeWidth(mUnReachedProgressBarHeight);
            canvas.drawLine(start,0,mRealWidth,0,mPaint);
        }
        canvas.restore();


        //super.onDraw(canvas);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mRealWidth = w - getPaddingRight() - getPaddingLeft();
    }
}
