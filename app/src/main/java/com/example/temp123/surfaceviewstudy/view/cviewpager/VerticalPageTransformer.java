package com.example.temp123.surfaceviewstudy.view.cviewpager;

import android.support.v4.view.ViewPager;
import android.view.View;

/**
 * Created by temp123 on 2019/5/13.
 */

public class VerticalPageTransformer implements ViewPager.PageTransformer {

    @Override
    public void transformPage(View view, float position) {
        /**
         *  0 当前界面
         *  -1 前一页
         *  1 后一页
         */
        if (position >= -1 && position <= 1) {
            view.setTranslationX(view.getWidth() * -position);
            float yPosition = position * view.getHeight();
            view.setTranslationY(yPosition);
        }
    }
}