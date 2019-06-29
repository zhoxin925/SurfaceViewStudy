package com.example.temp123.surfaceviewstudy.view;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by temp123 on 2019/1/21.
 * 测试surfaceview的缓冲机制
 */

public class SurfaceViewBuffer extends SurfaceView implements SurfaceHolder.Callback, Runnable {
    private SurfaceHolder sHolder;
    private Paint mPaint;

    public SurfaceViewBuffer(Context context) {
        this(context, null);
    }

    public SurfaceViewBuffer(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SurfaceViewBuffer(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        sHolder = getHolder();
        sHolder.addCallback(this);

        mPaint = new Paint();
        mPaint.setColor(Color.RED);
        mPaint.setTextSize(40);
    }


    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        new Thread(this).start();

//        int n = 0;
//        Canvas canvas = sHolder.lockCanvas();
//        Bitmap tempBitmap = Bitmap.createBitmap(getWidth(), getHeight(), Bitmap.Config.RGB_565);
//        Canvas tempCanvas = new Canvas(tempBitmap);
//        for(int i=0;i<20;i++) {
//            tempCanvas.drawText(i+"", getPaddingLeft(), getPaddingTop() + n, mPaint);
//            n += 60;
//        }
//        if(canvas != null) {
//            canvas.drawBitmap(tempBitmap, getPaddingLeft(), getPaddingTop(), mPaint);
//            sHolder.unlockCanvasAndPost(canvas);
//        }

//        //不开线程的话没有多缓冲问题？
//        int n = 0;
//        for(int i=0;i<20;i++) {
//            Canvas canvas = sHolder.lockCanvas();
//            if(canvas != null) {
//                canvas.drawText(i+"", getPaddingLeft(), getPaddingTop() + n, mPaint);
//                sHolder.unlockCanvasAndPost(canvas);
//                n += 20;
//            }
//            try {
//                Thread.sleep(600);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }

//        ValueAnimator animator = ValueAnimator.ofInt( 0, 1000000000);
//        animator.setDuration(1000000000);
//        animator.setInterpolator(null);
//        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
//            @Override
//            public void onAnimationUpdate(ValueAnimator animation) {
//                int n = 0;
//                for(int i=0;i<20;i++) {
//                    Canvas canvas = sHolder.lockCanvas();
//                    if(canvas != null) {
//                        canvas.drawText(i+"", getPaddingLeft(), getPaddingTop() + n, mPaint);
//                        sHolder.unlockCanvasAndPost(canvas);
//                        n += 60;
//                    }
//                    try {
//                        Thread.sleep(1000);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        });
//        animator.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {

    }

    @Override
    public void run() {
        int n = 0;
//        for(int i=0;i<20;i++) {
//            Canvas canvas = sHolder.lockCanvas();
//            if(canvas != null) {
//                canvas.drawText(i+"", getPaddingLeft(), getPaddingTop() + n, mPaint);
//                sHolder.unlockCanvasAndPost(canvas);
//                n += 20;
//            }
//        }

//        for(int i=0;i<20;i++) {
//            Canvas canvas = sHolder.lockCanvas();
//            if(canvas != null) {
//                canvas.drawText(i+"", getPaddingLeft(), getPaddingTop() + n, mPaint);
//                sHolder.unlockCanvasAndPost(canvas);
//                n += 20;
//            }
//            try {
//                Thread.sleep(600);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }

        Canvas canvas = sHolder.lockCanvas();
        Bitmap tempBitmap = Bitmap.createBitmap(getWidth(), getHeight(), Bitmap.Config.RGB_565);
        Canvas tempCanvas = new Canvas(tempBitmap);
        for(int i=0;i<20;i++) {
            tempCanvas.drawText(i+"", getPaddingLeft(), getPaddingTop() + n, mPaint);
            n += 60;
        }
        if(canvas != null) {
            canvas.drawBitmap(tempBitmap, getPaddingLeft(), getPaddingTop(), mPaint);
            sHolder.unlockCanvasAndPost(canvas);
        }
    }
}
