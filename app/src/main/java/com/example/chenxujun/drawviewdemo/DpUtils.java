package com.example.chenxujun.drawviewdemo;

import android.content.Context;
import android.util.TypedValue;

/**
 * Created by chenxujun on 2016/3/25.
 */
public class DpUtils {


    /** dip转换px */
    public static int dip2px(Context context,int dip) {
        float px = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dip, context.getResources().getDisplayMetrics());
        return (int) px;
    }

}
