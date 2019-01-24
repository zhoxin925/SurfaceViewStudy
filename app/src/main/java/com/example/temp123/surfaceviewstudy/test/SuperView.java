package com.example.temp123.surfaceviewstudy.test;

import android.content.Context;
import android.graphics.Canvas;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by temp123 on 2019/1/24.
 */

public class SuperView extends View {

    public SuperView(Context context) {
        this(context, null);
    }

    public SuperView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SuperView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        paintView();
    }

    private void paintView() {
        getValue();
        paintObjects();
    }

    public void paintObjects() {
        System.out.println("test---Super------paintObjects()");
        System.out.println("test------------------------------------------------------------------------");
    }

    public void getValue() {
        System.out.println("test---Super------getValue()");
    }
}
