package com.tm.view;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Toast;

import com.tm.ColourDraw_Activity;
import com.tm.R;

import java.io.Closeable;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

import static android.R.attr.path;
import static android.app.Activity.RESULT_OK;


/**
 * Created by TSM on 2016/10/31.
 */

public class MySurfaceView extends SurfaceView implements SurfaceHolder.Callback, Runnable {
    public static int myID;  //接收从其他activity传来数据的变量
    public int mScreenWidth; //屏幕的宽
    public int mScreenHeight;
    public  Bitmap bg_bitmap = null;


    /*是否处于绘制状态*/
    private boolean mIsDrawing;

    /*帮助类*/
    private SurfaceHolder mHolder;

    /*SurfaceHolder的画布*/
    public Canvas mCanvas; //负责将痕迹画在屏幕上

    /*路径*/
    public static Path mPath;

    /*画笔*/
    public static Paint mPaint;

    /*用于保存当前绘画的画布*/
    public Canvas cacheCanvas;

    /*保存当前绘制的内容*/
    public Bitmap cacheBitmap;

    public MySurfaceView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initView();
    }

    public MySurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public MySurfaceView(Context context) {
        super(context);
        initView();
    }

    /* 初始化颜色之类的 */
    private void initView() {
        mHolder = getHolder();
        mHolder.addCallback(this);
        setFocusable(true);
        setFocusableInTouchMode(true);
        this.setKeepScreenOn(true);

        mPath = new Path();

        mPaint = new Paint();
        mPaint.setAntiAlias(true);//抗锯齿
        mPaint.setColor(Color.BLACK);
        mPaint.setStyle(Paint.Style.STROKE);

        //mPaint.setAlpha(100);  //设置透明度为0
        mPaint.setStrokeJoin(Paint.Join.ROUND); //结合方式，平滑
        mPaint.setStrokeCap(Paint.Cap.ROUND);  //圆头

        //mPaint.setStrokeWidth(15); //设置笔的大小

    }

    /**
     * 触摸事件处理器
     *
     * @param event
     * @return
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int x = (int) event.getX();
        int y = (int) event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mPath.moveTo(x, y);
                break;
            case MotionEvent.ACTION_MOVE:
                mPath.lineTo(x, y);
                break;
            case MotionEvent.ACTION_UP:
                break;
            default:
                break;
        }
        invalidate();
        return super.onTouchEvent(event); //消耗触摸事件  false为不消耗
    }

    /*  线程工作函数  */
    @Override
    public void run() {
        long start = System.currentTimeMillis();
        while (mIsDrawing) {
            draw();
        }
        long end = System.currentTimeMillis();
        if (end - start < 100) {
            try {
                Thread.sleep(100 - (end - start));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }



    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        mIsDrawing = true;
        initCacheBitmapAndDrawBackground(true);
        new Thread(this).start();
    }

    private void initCacheBitmapAndDrawBackground(boolean isFirst) {

        BitmapFactory.Options opts = new BitmapFactory.Options(); //图片加载器，用于配置一些缩放比例，和像素单位
        opts.inSampleSize = 0; //制定加载器把原图片的宽高缩放到2/1的效果加载


        if(ColourDraw_Activity.imgSelectedFlag == false){
            bg_bitmap = BitmapFactory.decodeResource(getResources(), myID,opts);
            ColourDraw_Activity.imgSelectedFlag = true;
        }


        Matrix matrix = new Matrix();
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();  //获取屏幕大小的方法
        mScreenWidth = displayMetrics.widthPixels;
        mScreenHeight = displayMetrics.heightPixels; //为什么要减掉 50
        bg_bitmap = Bitmap.createScaledBitmap(bg_bitmap, getWidth(), getHeight(), true); // bitmap填满屏幕

        if (isFirst) { //第一次初始化cache画布的时候
            cacheBitmap = Bitmap.createBitmap(getWidth(), getHeight(), Bitmap.Config.ARGB_8888);
            //cacheBitmap = Bitmap.createScaledBitmap(bg_bitmap,mScreenWidth,mScreenHeight,true); //填满屏幕

            cacheCanvas = new Canvas();
            //设置都画在这个位图上
            cacheCanvas.setBitmap(cacheBitmap);
        }

        cacheCanvas.drawColor(Color.TRANSPARENT);
        cacheCanvas.drawBitmap(bg_bitmap,matrix,null);


        /*  缩放 bitmap  再 绘制到画布上
        int view_width = getWidth(); // 当前surfaceview的宽度
        int view_height = getHeight();
        int bgBitmapWidth = bg_bitmap.getWidth();
        int bgBitmapHeight = bg_bitmap.getHeight();
         if (isFirst){ //第一次初始化cache画布的时候
            cacheBitmap = Bitmap.createBitmap(getWidth(),getHeight(), Bitmap.Config.ARGB_8888);
            cacheCanvas = new Canvas();
            //设置都画在这个位图上
            cacheCanvas.setBitmap(cacheBitmap);
        }
        float scale = 1.0f;
        int left = 10,top = 10 ;
        if (bgBitmapWidth <= view_width && bgBitmapHeight <= view_height){
            left = (view_width - bgBitmapWidth) / 2;//计算左边位置
            top = (view_height - bgBitmapHeight) / 2; //计算顶部位置
        }else {
            //view 的高宽比
            double viewRatio = view_height / (double)view_width;
            double bitmapRatio = bgBitmapHeight / (double)bgBitmapWidth;
            if (bitmapRatio > viewRatio){
                //说明这个图片相对 view 来说很高，但是很窄
                top = 0;
                left = (int) ((view_width - view_height) / (double)(bitmapRatio) / 2);
                scale = view_height / (float)bgBitmapHeight;
            } else {
                left = 0;
                top = (int) ((view_height - view_width * bitmapRatio )/ 2);
                scale = view_width / (float)bgBitmapHeight;
            }
        }
        matrix.postScale(scale,scale);//等比例缩放
        matrix.postTranslate(left,top);//缩放后再移位

        cacheCanvas.drawColor(Color.BLUE);//绘制背景色
        cacheCanvas.drawBitmap(bg_bitmap,matrix,null);//绘制图片*/
    }


    private void draw() {
        try {
            mCanvas = mHolder.lockCanvas();
            cacheCanvas.drawPath(mPath, mPaint);
            mCanvas.drawBitmap(cacheBitmap, 0, 0, new Paint());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (mCanvas != null) {
                mHolder.unlockCanvasAndPost(mCanvas);
            }
        }


    }

/* 设置颜色 *//*

    public void setBlackPaint() {
        mPath.reset(); //重置当前路径
        mPaint.setColor(Color.BLACK);
    }

    public void setBrownPaint() {
        mPath.reset();
        mPaint.setColor(getResources().getColor(R.color.colorPrimary));
    }
*/

    /*  橡皮擦工具*/


    /*   清楚内容  */
    public void clean() {
        initView();
        //清楚内容即可
        initCacheBitmapAndDrawBackground(false);
    }

    /*  保存图片 */

    public File saveImageToGallery() {
        // 首先保存图片
        //File file = new File("/sdcard/DCIM/mPicture/");
        File appDir = new File(Environment.getExternalStorageDirectory(), "/DCIM/mPicture/");
        if (!appDir.exists()) {
            appDir.mkdir();
        }
        //String m =  String.valueOf(Calendar.YEAR) + String.valueOf((Calendar.MONTH+1)) + String.valueOf(Calendar.DAY_OF_MONTH);

        //String fileName ="IMG" + System.nanoTime()+ ".jpg";//System.currentTimeMillis() + ".jpg";
        File fileName = new File(myID + ".jpg");
        //File f = new File(fPath, UUID.randomUUID().toString() + ".png");
        File file = new File(appDir, fileName.toString());
        try {
            FileOutputStream fos = new FileOutputStream(file);
            cacheBitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.flush();
            fos.close();
            //提示出来的啊  啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊
            Toast.makeText(getContext(), "*　保存成功　*", Toast.LENGTH_SHORT).show();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 其次把文件插入到系统图库
        try {
            MediaStore.Images.Media.insertImage(getContext().getContentResolver(),
                    file.getAbsolutePath(), fileName.toString(), null);
            //提示出来的啊  啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊
            Toast.makeText(getContext(), "*　保存成功　*", Toast.LENGTH_SHORT).show();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        // 最后通知图库更新
        getContext().sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.parse("file://" + path)));
        return fileName;
    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {

        // 回收
        if ((cacheBitmap != null && !cacheBitmap.isRecycled()) || (bg_bitmap != null && !bg_bitmap.isRecycled())) {
            cacheBitmap.recycle();
            cacheBitmap = null;
            bg_bitmap.recycle();
            bg_bitmap = null;
            System.gc();
            Log.i("垃圾回收", "已经回收");
        }
        mIsDrawing = false;
    }

}
