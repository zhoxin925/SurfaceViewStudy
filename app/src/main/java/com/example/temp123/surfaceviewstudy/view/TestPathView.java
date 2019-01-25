package com.example.temp123.surfaceviewstudy.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.example.temp123.surfaceviewstudy.R;

/**
 * Created by temp123 on 2019/1/25.
 */

public class TestPathView extends View {
    private Paint paint;
    private RectF range;
    int radius;
    private Bitmap icon;
    private int center;

    public TestPathView(Context context) {
        this(context, null);
    }

    public TestPathView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TestPathView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setDither(true);
        paint.setTextSize(20);

        icon = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        radius = (getWidth() - getPaddingRight() - getPaddingLeft()) / 2;
        center = getPaddingLeft() + radius;

        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.BLUE);
        range = new RectF(getPaddingLeft(), getPaddingTop(), getPaddingLeft()+radius * 2, getPaddingTop()+radius * 2);
        canvas.drawRect(range, paint);

        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.LTGRAY);
        canvas.drawArc(range, 0, 60, true, paint);

        String ss = "测试是好事";
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.BLUE);
        Path path = new Path();
        path.addArc(range, 0, 60);

        //水平垂直是根据字的方向来的？？
        //Math.PI * radius * 2  圆的周长
        int hOffset = (int) (Math.PI * radius * 2 / 6 / 2 - paint.measureText(ss) / 2);
        int vOffset = radius / 5;

        canvas.drawTextOnPath(ss, path, hOffset, vOffset, paint);

        //   30 * (Math.PI / 180)   转换
        float left = (float) (center + radius / 2 * Math.cos(30 * (Math.PI / 180)) - icon.getWidth()/2);
        float top = (float)(center + radius / 2 * Math.sin(30 * (Math.PI / 180)) - icon.getHeight()/2);
        canvas.drawBitmap(icon, left, top, paint);
    }
}
