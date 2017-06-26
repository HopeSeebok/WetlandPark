package com.yunwei.wetlandpark.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * @Package: com.yunwei.zaina.view
 * @Description:
 * @author: Aaron
 * @date: 2016-06-15
 * @Time: 09:19
 * @version: V1.0
 */
public class ShowImageViewPager extends ViewPager {

    public ShowImageViewPager(Context context) {
        super(context);
    }

    public ShowImageViewPager(Context context, AttributeSet attr) {
        super(context, attr);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        try {
            return super.onInterceptTouchEvent(ev);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            return false;
        }
    }
}
