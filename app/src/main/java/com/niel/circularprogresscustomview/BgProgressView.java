package com.niel.circularprogresscustomview;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

public class BgProgressView extends View {

    private int mMax = 100;
    private int mProgress = 0;
    private int mStartAngle = -90;


    private int mViewSize;

    private static final int DEFAULT_VIEW_SIZE = 96;

    private Paint mProgressPaint;
    private Paint mBackgroundPaint;

    public BgProgressView(Context context, AttributeSet attrs) {
        super(context, attrs);

        final Resources res = getResources();

        int backgroundColor = res.getColor(android.R.color.holo_orange_light);
        int progressColor = res.getColor(android.R.color.white);

        mBackgroundPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mBackgroundPaint.setColor(backgroundColor);
        mBackgroundPaint.setStyle(Paint.Style.FILL);

        mProgressPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mProgressPaint.setColor(progressColor);
        mProgressPaint.setStyle(Paint.Style.FILL);

    }

    @Override
    protected void onDraw(Canvas canvas) {

        canvas.drawRect(0, 0, mViewSize, mViewSize, mBackgroundPaint);

        float sweepAngle = mViewSize * mProgress / mMax;

        sweepAngle = mViewSize - sweepAngle;

        canvas.drawRect(0, 0, sweepAngle, mViewSize, mProgressPaint);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int width = resolveSize(DEFAULT_VIEW_SIZE, widthMeasureSpec);
        int height = resolveSize(DEFAULT_VIEW_SIZE, heightMeasureSpec);
        mViewSize = Math.min(width, height);

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