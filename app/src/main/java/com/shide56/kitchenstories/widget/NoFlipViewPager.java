package com.shide56.kitchenstories.widget;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * 可以设置禁止用户通过数据页面左右翻转。
 */
public class NoFlipViewPager extends ViewPager {

    /**
     * true 支持翻页，默认不支持翻页
     */
    private boolean flip;

    public NoFlipViewPager(Context context) {
        super(context);
    }

    public NoFlipViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * 去除页面切换时的滑动翻页效果
     */
    @Override
    public void setCurrentItem(int item) {
        super.setCurrentItem(item, false);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return flip && super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return flip && super.onTouchEvent(ev);
    }

    public boolean isFlip() {
        return flip;
    }

    public void setFlip(boolean flip) {
        this.flip = flip;
    }
}
