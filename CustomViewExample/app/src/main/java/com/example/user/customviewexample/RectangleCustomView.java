package com.example.user.customviewexample;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import java.util.Random;

public class RectangleCustomView extends View{

    private float figureWidth;
    private float figureHeight;

    private int figureColor;

    private float figureXCenter;
    private float figureYCenter;

    private FigureCenterChangeCallback mEventListener;

    public RectangleCustomView(Context context) {
        super(context);
        init();
    }

    public RectangleCustomView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        unzipAttr(attrs);
        init();
    }

    public RectangleCustomView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        unzipAttr(attrs);
        init();
    }

    public RectangleCustomView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        unzipAttr(attrs);
        init();
    }

    private void unzipAttr(@Nullable AttributeSet attrs) {
        if (attrs == null)
            return;

        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.RectangleCustomView);
        figureWidth = typedArray.getFloat(R.styleable.RectangleCustomView_rectangle_width, 40);
        figureHeight = typedArray.getFloat(R.styleable.RectangleCustomView_rectangle_height, 40);
        figureColor = typedArray.getColor(R.styleable.RectangleCustomView_rectangle_color, Color.RED);
        typedArray.recycle();
    }

    private void init() {
    }

    public void setEventListener(FigureCenterChangeCallback mEventListener) {
        this.mEventListener = mEventListener;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction()==MotionEvent.ACTION_MOVE){
            return onMotionTouch(event);
        }

        if(event.getAction()==MotionEvent.ACTION_DOWN) {
            return onTapTouch(event);
        }
        return false;
    }

    private boolean onMotionTouch(MotionEvent event){
        figureXCenter = event.getX();
        figureYCenter = event.getY();
        this.invalidate();
        if (mEventListener != null)
            mEventListener.ChangedCenter(figureXCenter, figureYCenter);
        return true;
    }

    private boolean onTapTouch(MotionEvent event) {

        if (event.getX() > (figureXCenter + figureWidth / 2) || event.getX() < (figureXCenter - figureWidth / 2))
            return false;

        if (event.getY() > (figureYCenter + figureHeight / 2) || event.getY() < (figureYCenter - figureHeight / 2))
            return false;

        Random random = new Random();
        figureColor = Color.rgb(random.nextInt(), random.nextInt(), random.nextInt());
        this.invalidate();
        return true;
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);

        Paint paint = new Paint();
        paint.setColor(figureColor);

        float top = figureYCenter - figureHeight / 2;
        float bottom = figureYCenter + figureHeight / 2;
        float left = figureXCenter - figureWidth / 2;
        float right = figureXCenter + figureWidth / 2;

        canvas.drawRect(left, top, right, bottom, paint);
    }

    interface FigureCenterChangeCallback{
        void ChangedCenter(float x, float y);
    }
}
