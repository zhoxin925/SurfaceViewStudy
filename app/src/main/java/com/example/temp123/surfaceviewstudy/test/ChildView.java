package com.example.temp123.surfaceviewstudy.test;

import android.content.Context;
import android.graphics.Canvas;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

/**
 * Created by temp123 on 2019/1/24.
 */

public class ChildView extends SuperView {

    public ChildView(Context context) {
        this(context, null);
    }

    public ChildView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ChildView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        test();
    }

    public void paintObjects() {
        System.out.println("test---Child------paintObjects()");
        super.paintObjects();
    }

    public void getValue() {
        System.out.println("test---Child------getValue()");
    }
}
