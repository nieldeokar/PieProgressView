package com.niel.circularprogresscustomview;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

public class PieAndBGprogressView extends View {

    /*private static final int DEFAULT_MAX = 100;
    private static final int DEFAULT_PROGRESS = 0;
    private static final int DEFAULT_START_ANGLE = -90;

*/


    private static final String TAG =PieAndBGprogressView.class.getSimpleName();

    private int mMax = 100;
    private int mProgress = 0;
    private int mStartAngle = -90;


    private int mViewSize;
    private int mCircleViewSize;

    private static final int DEFAULT_VIEW_SIZE = 96;

    private Paint mProgressPaintCircle;
    private Paint mProgressPaintRect;
    private Paint mBackgroundPaintCircle;
    private Paint mBackgroundPaintRect;
    private Paint textPaint;

    int mColorGrey;
    int mColorRed;

    private RectF mCircleRectF;

    public PieAndBGprogressView(Context context, AttributeSet attrs) {
        super(context, attrs);

        final Resources res = getResources();

        mColorRed = res.getColor(R.color.bg_red);
        mColorGrey = res.getColor(R.color.bg_grey);

        int mColorWhite = res.getColor(android.R.color.white);

        mBackgroundPaintCircle = new Paint(Paint.ANTI_ALIAS_FLAG);
        mBackgroundPaintCircle.setColor(mColorRed);
        mBackgroundPaintCircle.setStyle(Paint.Style.FILL);

        mProgressPaintCircle = new Paint(Paint.ANTI_ALIAS_FLAG);
        mProgressPaintCircle.setColor(mColorWhite);
        mProgressPaintCircle.setStyle(Paint.Style.FILL);



        mBackgroundPaintRect = new Paint(Paint.ANTI_ALIAS_FLAG);
        mBackgroundPaintRect.setColor(mColorGrey);
        mBackgroundPaintRect.setStyle(Paint.Style.FILL);


        mProgressPaintRect = new Paint(Paint.ANTI_ALIAS_FLAG);
        mProgressPaintRect.setColor(mColorWhite);
        mProgressPaintRect.setStyle(Paint.Style.FILL);



        textPaint = new Paint();
        textPaint.setColor(mColorRed);
        textPaint.setTextAlign(Paint.Align.CENTER);
        textPaint.setTextSize(48);
        textPaint.setTypeface(Typeface.DEFAULT_BOLD);


        mCircleRectF = new RectF();

    }

    @Override
    protected void onDraw(Canvas canvas) {

        /*
         *
         *   This section draws the rectangle
         *
         */


        canvas.drawRect(0, 0, mViewSize, mViewSize, mBackgroundPaintRect);

        float sweepAngle = mViewSize * mProgress / mMax;

        sweepAngle = mViewSize - sweepAngle;

        canvas.drawRect(0, 0, sweepAngle, mViewSize, mProgressPaintRect);
        //Log.d(TAG,String.format("sweepAngle is : %f and  ViewSize is %d",sweepAngle,mViewSize));

        if(mProgress>93){
            canvas.drawText(String.valueOf(mProgress), sweepAngle+30, mViewSize-30, textPaint);
            Log.d(TAG,String.format("sweepAngle  : %f and View : %d progress %d",sweepAngle+30,mViewSize-30,mProgress));
        }else {
            canvas.drawText(String.valueOf(mProgress), sweepAngle-30, mViewSize-30, textPaint);
            Log.d(TAG,String.format("sweepAngle  : %f and View : %d progress %d",sweepAngle-30,mViewSize-30,mProgress));
        }


        //canvas.drawText(String.valueOf(mProgress), sweepAngle-30, mViewSize-30, textPaint);

        /*
         *
         *   This section draws the circle arch and background circle
         *
         */

        mCircleRectF.set(mViewSize/2- mCircleViewSize/2, mViewSize/2 -  mCircleViewSize/2,
                mViewSize/2 + mCircleViewSize, mViewSize/2 + mCircleViewSize);

        canvas.drawArc(mCircleRectF, 0, 360, true, mBackgroundPaintCircle);

        float sweepAngleCircle = 360 * mProgress / mMax;

        if(mProgress >= 50) {
            mProgressPaintCircle.setColor(mColorGrey);
        }

        canvas.drawArc(mCircleRectF, mStartAngle, sweepAngleCircle, true, mProgressPaintCircle);


    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int width = resolveSize(DEFAULT_VIEW_SIZE, widthMeasureSpec);
        int height = resolveSize(DEFAULT_VIEW_SIZE, heightMeasureSpec);
        mViewSize = Math.min(width, height);
        mCircleViewSize = 20;
        setMeasuredDimension(width, height);
    }

    /**
     * Sets the current progress (must be between 0 and max).
     */
    public void setProgress(int progress) {
        if (progress > mMax || progress < 0) {
            throw new IllegalArgumentException(
                String.format("Progress (%d) must be between %d and %d", progress, 0, mMax));
        }
        mProgress = progress;

        invalidate();
    }


}