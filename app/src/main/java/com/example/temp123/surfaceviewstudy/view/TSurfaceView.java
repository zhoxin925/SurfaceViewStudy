package com.example.temp123.surfaceviewstudy.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PixelFormat;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.example.temp123.surfaceviewstudy.R;

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
    private Bitmap[] iconBitmap;
    private int[] colors = new int[]{Color.RED, Color.YELLOW, Color.BLUE, Color.GREEN
            , Color.RED, Color.YELLOW, Color.BLUE, Color.GREEN};

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
//        setZOrderMediaOverlay(true);
//        mHolder.setFormat(PixelFormat.TRANSLUCENT);
//        mHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);

        //设置可获得焦点
        setFocusable(true);
        setFocusableInTouchMode(true);
        //屏幕常亮
        this.setKeepScreenOn(true);

        iconBitmap = new Bitmap[colors.length];
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
        for(int i=0; i<colors.length;i++) {
            iconBitmap[i] = bitmap;
        }
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

        mTextPaint = new Paint();
        mTextPaint.setAntiAlias(true);
        mTextPaint.setTextSize(34f);
        //mTextPaint.setColor(Color.WHITE);

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
        //mHolder.removeCallback(this);//这句话会导致home键后绘制的内容消失，这句话千万不能加！！！哈哈哈哈
    }

    @Override
    public void run() {
        while(isViewCreated) {
            int start = (int) System.currentTimeMillis();
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

    //角度=180°×弧度÷π ，弧度=角度×π÷180°。
    private void drawSomeThing(Canvas canvas) {
        mShapePaint.setStyle(Paint.Style.FILL);
        mShapePaint.setColor(0xf2f2f2f2);
        canvas.drawRect(0,0, getWidth(), getHeight(), mShapePaint);

        startAngle = 0;
        tempAngle = 0;
        for(int i=0; i<arcCount; i++) {
            mShapePaint.setColor(colors[i]);
            tempAngle += sweepAngle;
            canvas.drawArc(arcRange, startAngle, sweepAngle, true, mShapePaint);

            //canvas.drawBitmap(iconBitmap[i], );
            drawText(canvas, startAngle, sweepAngle, "哈哈哈"+i);
            drawIcon(canvas, startAngle, sweepAngle, iconBitmap[i]);

            startAngle = tempAngle;
        }

        mShapePaint.setColor(Color.GRAY);
        mShapePaint.setStyle(Paint.Style.STROKE);
        canvas.drawCircle(center, center, radius/2, mShapePaint);
    }

    private void drawText(Canvas canvas, int startAngle, int endAngle, String ss) {
        Path path = new Path();
        path.addArc(arcRange, startAngle, endAngle);
        int hOffset = (int) ((radius * Math.PI / arcCount - mTextPaint.measureText(ss)) / 2);
        int vOffset = radius /2 / 6;
        canvas.drawTextOnPath(ss, path, hOffset, vOffset, mTextPaint);
    }

    private void drawIcon(Canvas canvas, int startAngle, int sweepAngle, Bitmap bitmap) {
        double rad = (startAngle + sweepAngle / 2) * Math.PI/180;
        float left = (float) (Math.cos(rad) * radius/2/2 + center - bitmap.getWidth()/2);
        float top = (float) (Math.sin(rad) * radius/2/2 + center - bitmap.getHeight()/2);
        canvas.drawBitmap(bitmap, left, top, mShapePaint);
    }
}
