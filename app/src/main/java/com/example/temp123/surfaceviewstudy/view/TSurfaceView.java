package com.example.temp123.surfaceviewstudy.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by temp123 on 2018/12/26.
 */

public class TSurfaceView extends SurfaceView implements SurfaceHolder.Callback, Runnable {
    private Paint mShapePaint;
    private Paint mTextPaint;

    private int center;
    private int padding;
    private int radius;
    private RectF arcRange;

    private boolean isViewCreated;
    private SurfaceHolder mHolder;
    private Thread mThread;

    private int startAngle;
    private int sweepAngle = 360/8;
    private int tempAngle;
    private int arcCount = 8;
    private int[] colors = new int[]{Color.BLUE, Color.YELLOW, Color.GREEN, Color.GRAY
            , Color.BLUE, Color.YELLOW, Color.GREEN, Color.GRAY};

    public TSurfaceView(Context context) {
        this(context, null);
    }

    public TSurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        mHolder = getHolder();
        mHolder.addCallback(this);

//        setZOrderOnTop(true);
//        mHolder.setFormat(PixelFormat.TRANSLUCENT);
//        mHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);

        //设置可获得焦点
        setFocusable(true);
        setFocusableInTouchMode(true);
        //屏幕常亮
        this.setKeepScreenOn(true);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);

        center = width / 2;
        padding = getPaddingLeft();
        radius = width - getPaddingRight() - getPaddingLeft();

        setMeasuredDimension(Math.min(width, height), Math.min(width, height));
    }

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        mShapePaint = new Paint();
        mShapePaint.setAntiAlias(true);
        mShapePaint.setStrokeWidth(8);
        mShapePaint.setStyle(Paint.Style.FILL);

        mTextPaint = new Paint();
        mTextPaint.setAntiAlias(true);

        arcRange = new RectF(padding, padding, radius+padding, radius+padding);

        isViewCreated = true;
        mThread = new Thread(this);
        mThread.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) { }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        isViewCreated = false;
        mHolder.removeCallback(this);
    }

    @Override
    public void run() {
        int start = (int) System.currentTimeMillis();
        while(isViewCreated) {
            Canvas canvas = null;
            try {
                canvas = mHolder.lockCanvas();
                if(canvas != null) {
                    drawSomeThing(canvas);
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if(canvas != null) {
                    mHolder.unlockCanvasAndPost(canvas);
                }
                int end = (int) System.currentTimeMillis();
                if(end - start < 100) {
                    try {
                        Thread.sleep(100 - (end - start));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    //角度=180°×弧度÷π ，弧度=角度×π÷180°。
    private void drawSomeThing(Canvas canvas) {
        canvas.drawColor(0xffffff);

        mShapePaint.setStyle(Paint.Style.FILL);
        startAngle = 0;
        tempAngle = 0;
        for(int i=0; i<arcCount; i++) {
            mShapePaint.setColor(colors[i]);
            tempAngle += sweepAngle;
            canvas.drawArc(arcRange, startAngle, sweepAngle, true, mShapePaint);
            startAngle = tempAngle;
        }

        mShapePaint.setColor(Color.RED);
        mShapePaint.setStyle(Paint.Style.STROKE);
        canvas.drawCircle(center, center, radius/2, mShapePaint);
    }
}
