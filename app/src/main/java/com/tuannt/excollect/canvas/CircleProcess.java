package com.tuannt.excollect.canvas;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Comment
 *
 * @author TuanNT
 */
public class CircleProcess extends View {

    private Paint mFinishedPaint;
    private Paint mUnfinishedPaint;
    private Paint mBackgroundPaint;


    public CircleProcess(Context context) {
        this(context, null);
    }

    public CircleProcess(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CircleProcess(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        initAttr(attrs);
        initPaints();
    }

    private void initPaints() {
        mBackgroundPaint = new Paint();
        mBackgroundPaint.setColor(Color.GRAY);
        mBackgroundPaint.setAntiAlias(true);

        mFinishedPaint = new Paint();
        mFinishedPaint.setColor(Color.RED);
        mFinishedPaint.setAntiAlias(true);
        mFinishedPaint.setStyle(Paint.Style.STROKE);
        mFinishedPaint.setStrokeWidth(10);


        mUnfinishedPaint = new Paint();
        mUnfinishedPaint.setColor(Color.BLUE);
        mUnfinishedPaint.setAntiAlias(true);
        mUnfinishedPaint.setStyle(Paint.Style.STROKE);
        mUnfinishedPaint.setStrokeWidth(10);

    }

    private void initAttr(AttributeSet attrs) {
    }


    @Override
    protected void onDraw(Canvas canvas) {

        RectF finishedRect = new RectF();
        finishedRect.set(5, 5, getWidth() - 5, getWidth() - 5); // need to convert 5px to dp

        canvas.drawCircle(getWidth() / 2f, getHeight() / 2f, getWidth() / 2f, mBackgroundPaint);
        canvas.drawArc(finishedRect, 0, 360, false, mFinishedPaint);
    }

    // TODO: 11/24/15 need to clear  onSaveInstanceState and onRestoreInstanceState
    @Override
    protected Parcelable onSaveInstanceState() {
        super.onSaveInstanceState();
        Bundle bundle = new Bundle();
        bundle.putInt("key", 10);
        return bundle;
    }

    @Override
    protected void onRestoreInstanceState(Parcelable state) {
        super.onRestoreInstanceState(state);
        if (state != null) {
            Bundle bundle = (Bundle) state;
            bundle.getInt("key", 0);
        }
    }

    /**
     * @param widthMeasureSpec
     * @param heightMeasureSpec http://stackoverflow.com/questions/12266899/onmeasure-custom-view-explanation
     *                          http://stackoverflow.com/questions/7423082/authorative-way-to-override-onmeasure
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int resultSize = 0;
        int mode = MeasureSpec.getMode(widthMeasureSpec);
        int width = MeasureSpec.getSize(widthMeasureSpec);
        if (mode == MeasureSpec.EXACTLY) {
            resultSize = width;
        } else if (mode == MeasureSpec.AT_MOST) {
            resultSize = Math.min(resultSize, width);
        } else {
            resultSize = 100;
        }
        setMeasuredDimension(resultSize, resultSize);
    }
}
