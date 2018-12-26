package com.example.temp123.surfaceviewstudy.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
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

    private int width;
    private int height;
    private int padding;
    private int radius;
    private RectF arcRectF;

    private boolean isViewCreated;
    private SurfaceHolder mHolder;
    private Thread mThread;

    private int startAngle;
    private int sweepAngle = 360/8;
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

        //设置可获得焦点
        setFocusable(true);
        setFocusableInTouchMode(true);
        //屏幕常亮
        this.setKeepScreenOn(true);

        mShapePaint = new Paint();
        mShapePaint.setAntiAlias(true);
        mShapePaint.setColor(0xfff1f1f1);

        mTextPaint = new Paint();
        mTextPaint.setAntiAlias(true);
    }

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
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
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        width = MeasureSpec.getSize(widthMeasureSpec);
        height = MeasureSpec.getSize(heightMeasureSpec);
        padding = width / 15;
        radius = (width - padding * 2) / 2;
        arcRectF = new RectF(padding, padding, width, width);
    }

    @Override
    public void run() {
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
            }
        }
    }

    //角度=180°×弧度÷π ，弧度=角度×π÷180°。
    private void drawSomeThing(Canvas canvas) {
        canvas.drawRect(new Rect(padding, (height - radius*2) / 2
                , padding + radius*2, (height - radius*2) / 2 + radius*2), mShapePaint);
        canvas.translate(width/2, height/2);

        mShapePaint.setColor(Color.RED);
        mShapePaint.setStrokeWidth(8);
        mShapePaint.setStyle(Paint.Style.STROKE);
        canvas.drawCircle(0, 0, radius, mShapePaint);

        for(int i=0; i<arcCount; i++) {
            mShapePaint.setColor(colors[i]);
            canvas.drawArc(arcRectF, startAngle, sweepAngle, true, mShapePaint);
            sweepAngle += sweepAngle;
        }


    }
}
