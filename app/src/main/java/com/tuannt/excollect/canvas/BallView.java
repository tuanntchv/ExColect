package com.tuannt.excollect.canvas;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.Transformation;

import java.util.ArrayList;
import java.util.List;

/**
 * Comment
 *
 * @author TuanNT
 */
public class BallView extends View {
    private static final int DEFAULT_RADIUS = 40;
    private static final int DEFAULT_BALL_NUMBER = 5;
    private static final int DEFAULT_ITEM_DIVIDER = 60;
    private static final float SCALE_RADIO_MOVE_BALL = 0.6f;


    private List<Ball> mBalls;

    private MoveAnimation mMoveAnimation;

    private Paint mPaint;
    private Paint mPaintMove;

    private int mItemDivider = DEFAULT_ITEM_DIVIDER;
    private int mRadius = DEFAULT_RADIUS;
    private int mBallNumber = DEFAULT_BALL_NUMBER;

    private int mWidth;
    private int mHeight;
    private int mCenterY;
    private int mSizeMoveBall;
    private float mInterpolatedValue;
    private float mInterpolatedValueOld;


    public BallView(Context context) {
        this(context, null);
    }

    public BallView(Context context, AttributeSet attrs) {
        this(context, attrs, 1);
    }

    public BallView(Context context, AttributeSet attrs, int defStyleAttr) {

        super(context, attrs, defStyleAttr);
        init();
        initMoveAnimation();
        Log.d("result init", "ok");
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setColor(Color.BLUE);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setAntiAlias(true);

        mPaintMove = new Paint();
        mPaintMove.setColor(Color.GREEN);
        mPaintMove.setStyle(Paint.Style.STROKE);
        mPaintMove.setAntiAlias(true);

        mWidth = (mRadius * 2 + mItemDivider) * mBallNumber;
        mBalls = new ArrayList<>();

        mBalls.add(createBallItem(mRadius * 3 / 5, new float[]{mRadius + mItemDivider, mRadius}));

        for (int i = 1; i < mBallNumber; i++) {
            mBalls.add(createBallItem(mRadius, new float[]{(mRadius * 2 + mItemDivider) * i, mRadius}));
        }
    }

    private void initVariable() {
        mRadius = DEFAULT_RADIUS;
        mItemDivider = DEFAULT_ITEM_DIVIDER;
        mBallNumber = DEFAULT_BALL_NUMBER;

        mSizeMoveBall = (int) (mRadius * SCALE_RADIO_MOVE_BALL);
        mCenterY = mHeight / 2;
    }

    private void initMoveAnimation() {
        mMoveAnimation = new MoveAnimation();
        mMoveAnimation.setDuration(3500);
        mMoveAnimation.setInterpolator(new AccelerateDecelerateInterpolator());
        mMoveAnimation.setRepeatCount(Animation.INFINITE);
        mMoveAnimation.setRepeatMode(Animation.REVERSE);
    }

    private Ball createBallItem(int radius, float[] position) {
        Ball ball = new Ball();
        ball.position = position;
        ball.radius = radius;
        return ball;
    }

    private void stopAnimation() {
        clearAnimation();
        postInvalidate();
    }

    private void startAnimation() {
        startAnimation(mMoveAnimation);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Log.d("result draw", "ok");

        Ball moveBall = mBalls.get(0);
        moveBall.position[0] = mWidth * mInterpolatedValue;

        //  drawBall(canvas, mBalls.get(0));
        for (int i = 1; i < mBallNumber; i++) {
            drawMetaBall(canvas, i);
        }
    }

    private void drawMetaBall(Canvas canvas, int index) {
        Ball moveBall = mBalls.get(0);
        Ball currentBall = mBalls.get(index);
        canvas.drawCircle(moveBall.getX(), moveBall.getY(), moveBall.getRadius(), mPaintMove);
        canvas.drawCircle(currentBall.getX(), currentBall.getY(), currentBall.getRadius(), mPaint);



    }

    private void drawBall(final Canvas canvas, final Ball ball) {
        RectF moveBallRect = new RectF();
        moveBallRect.left = ball.getX() - ball.getRadius();
        moveBallRect.top = ball.getY() - ball.getRadius();
        moveBallRect.right = moveBallRect.left + ball.getRadius() * 2;
        moveBallRect.bottom = moveBallRect.top + ball.getRadius() * 2;
        canvas.drawCircle(ball.getX(), ball.getY(), ball.getRadius(), mPaint);
    }

    private class MoveAnimation extends Animation {
        @Override
        protected void applyTransformation(float interpolatedTime, Transformation t) {
            super.applyTransformation(interpolatedTime, t);
            if (mInterpolatedValueOld != interpolatedTime) {
                mInterpolatedValue = interpolatedTime;
                mInterpolatedValueOld = interpolatedTime;
                invalidate();
            }
        }
    }

    private class Ball {
        float[] position;
        int radius;

        public int getRadius() {
            return radius;
        }

        public float getX() {
            return position[0];
        }

        public float getY() {
            return position[1];
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mWidth = MeasureSpec.getSize(widthMeasureSpec);
        mHeight = MeasureSpec.getSize(heightMeasureSpec);
        initVariable();
    }

    @Override
    protected void onVisibilityChanged(View changedView, int visibility) {
        super.onVisibilityChanged(changedView, visibility);
        if (visibility == VISIBLE) {
            startAnimation();
        } else {
            stopAnimation();
        }
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        startAnimation();
    }

    @Override
    protected void onDetachedFromWindow() {
        stopAnimation();
        super.onDetachedFromWindow();
    }
}
