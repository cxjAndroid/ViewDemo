package com.example.chenxujun.drawviewdemo;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;

/**
 * Created by chenxujun on 2016/3/23.
 */
public class DemoView extends View {
    private float startX;
    private float startY;
    private float cx;
    private float cy;
    //半径长度
    private float radius;
    Paint paint = new Paint();
    private RectF   rect;
    private int     right;
    private int     bottom;
    private int     progress;
    private Context context;
    private int     dp;


    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
        invalidate();
    }

    public DemoView(Context context) {
        this(context, null);
    }

    public DemoView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DemoView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        radius = 250;
        cx = 250;
        cy = 250;
        cy = 250;
        right = 250;
        bottom = 250;
        this.context = context;
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.DemoView);
        cx = typedArray.getDimensionPixelOffset(R.styleable.DemoView_cx, DpUtils.dip2px(context, 50));
        cy = typedArray.getDimensionPixelOffset(R.styleable.DemoView_cy, DpUtils.dip2px(context, 50));
        radius = typedArray.getDimensionPixelOffset(R.styleable.DemoView_radius, DpUtils.dip2px(context, 50));

        typedArray.recycle();
        rect = new RectF();
        dp = DpUtils.dip2px(context, 1);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthMode  = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize  = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int width      = 0;
        int height     = 0;
        //match parent,指定数值
        if (widthMode == MeasureSpec.EXACTLY) {
            width = widthSize;
        }
        //wrap content
        else if (widthMode == MeasureSpec.AT_MOST) {
            float desired = cx * 2;
            width = (int) desired;
        }
        if (heightMode == MeasureSpec.EXACTLY) {
            height = heightSize;
        } else if (heightMode == MeasureSpec.AT_MOST) {
            float desired = cy * 2;
            height = (int) desired;
        }
        setMeasuredDimension(width, height);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        paint.setAntiAlias(true);
        paint.setColor(Color.RED);
        paint.setStrokeWidth(0);
        canvas.drawCircle(cx, cy, radius, paint);

        paint.reset();
        paint.setAntiAlias(true);
        paint.setColor(Color.YELLOW);

      /*  自定义控件设置宽高wrap content时，getMeasuredWidth() 即为
        float desired = cx * 2;
        width = (int) desired;
        float desired = cy * 2;
        height = (int) desired;*/
        //canvas画出的view其实都是矩形背景，通过sweepAngle角度来控制矩形背景中扇形显示的角度
        rect.set(0, 0, getMeasuredWidth(), getMeasuredHeight());
        canvas.drawArc(rect, -90, progress, true, paint);

        paint.reset();
        paint.setAntiAlias(true);
        paint.setColor(Color.BLUE);
        canvas.drawCircle(cx, cy, radius - 50, paint);
        //canvas.drawOval(rect, paint);
        //canvas.drawLine(0, 0, startX, startY, paint);
    }
}
