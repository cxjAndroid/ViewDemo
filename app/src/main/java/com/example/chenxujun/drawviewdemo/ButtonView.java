package com.example.chenxujun.drawviewdemo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by chenxujun on 2016/4/6.
 */
public class ButtonView extends View {

    private RectF rect;

    public ButtonView(Context context) {
        this(context, null);
    }

    public ButtonView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ButtonView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        rect = new RectF(20, 20, 120, 70);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //setMeasuredDimension(bitmap.getWidth(),bitmap.getHeight());
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawRoundRect(rect,30,30,new Paint());
    }
}
