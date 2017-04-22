package com.niel.circularprogresscustomview;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

public class PieAndBGprogressView extends View {

    /*private static final int DEFAULT_MAX = 100;
    private static final int DEFAULT_PROGRESS = 0;
    private static final int DEFAULT_START_ANGLE = -90;
*/

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

    int progressColor;

    private RectF mInnerRectF;

    public PieAndBGprogressView(Context context, AttributeSet attrs) {
        super(context, attrs);

        final Resources res = getResources();

        int backgroundColor = res.getColor(R.color.circle);
        progressColor = res.getColor(R.color.bg_grey);
        int progressColorWhite = res.getColor(R.color.bg_grey);

        mBackgroundPaintCircle = new Paint(Paint.ANTI_ALIAS_FLAG);
        mBackgroundPaintCircle.setColor(backgroundColor);
        mBackgroundPaintCircle.setStyle(Paint.Style.FILL);

        mProgressPaintCircle = new Paint(Paint.ANTI_ALIAS_FLAG);
        mProgressPaintCircle.setColor(progressColorWhite);
        mProgressPaintCircle.setStyle(Paint.Style.FILL);



        mBackgroundPaintRect = new Paint(Paint.ANTI_ALIAS_FLAG);
        mBackgroundPaintRect.setColor(backgroundColor);
        mBackgroundPaintRect.setStyle(Paint.Style.FILL);


        mProgressPaintRect = new Paint(Paint.ANTI_ALIAS_FLAG);
        mProgressPaintRect.setColor(progressColor);
        mProgressPaintRect.setStyle(Paint.Style.FILL);


        mInnerRectF = new RectF();

    }

    @Override
    protected void onDraw(Canvas canvas) {




        canvas.drawRect(0, 0, mViewSize, mViewSize, mBackgroundPaintRect);

        float sweepAngle = mViewSize * mProgress / mMax;

        sweepAngle = mViewSize - sweepAngle;

        canvas.drawRect(0, 0, sweepAngle, mViewSize, mProgressPaintRect);


        mInnerRectF.set(0, 0, mViewSize, mViewSize);
        mInnerRectF.offset((getWidth() - mViewSize) / 2, (getHeight() - mViewSize) / 2);

        mInnerRectF.set(mViewSize/2- mCircleViewSize/2, mViewSize/2 -  mCircleViewSize/2, mViewSize/2 + mCircleViewSize, mViewSize/2 + mCircleViewSize);


        canvas.drawArc(mInnerRectF, 0, 360, true, mBackgroundPaintCircle);

        float sweepAngleCircle = 360 * mProgress / mMax;

        if(mProgress >= 50) {
            mProgressPaintCircle.setColor(progressColor);
        }

        canvas.drawArc(mInnerRectF, mStartAngle, sweepAngleCircle, true, mProgressPaintCircle);


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