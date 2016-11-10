package com.tm;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;

import com.tm.view.MySurfaceView;

import static com.tm.view.MySurfaceView.mPaint;


public class ColourDraw_Activity extends Activity{
    private int paintStroke = 5;  //橡皮擦 和 笔 的大小
    private int sizeCount = 0; //点了 放大缩小几次
    private Button btn_width;  //笔的大小

    private boolean isVisible = true;
    public ImageButton btn_black, btn_brown, btn_wathet, btn_green, btn_violet, btn_blue, btn_orange, btn_claret, btn_pink, btn_yellow, btn_gray, btn_red,
            btn_eraser, btn_preservation, btn_home, btn_clear;

    /*private int[] PaintID = {R.id.black_imageView,R.id.brown_imageView,R.id.wathet_imageView,R.id.green_imageView,
                                R.id.violet_imageView,R.id.blue_imageView,R.id.orange_imageView,R.id.claret_imageView,
                               R.id.pink_imageView,R.id.yellow_imageView,R.id.gray_imageView,R.id.red_imageView};
    *//* 对画笔颜色处理*//*
    public int[] colorArray = {Color.BLACK , getResources().getColor(R.color.BROWN) , getResources().getColor(R.color.WATHET),
            Color.GREEN , getResources().getColor(R.color.VIOLET) , Color.BLUE, getResources().getColor(R.color.ORANGE),
            getResources().getColor(R.color.CLARET) , getResources().getColor(R.color.PINK) , Color.YELLOW, Color.GRAY, Color.RED}; //自定义颜色数组

*/
    public MySurfaceView surfaceView;
    private View lastSelectPen = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.colour_draw);

        /*
        *  SurfaceView 是一个view ，用实例或类来 接收数据
        *  MySurfaceView 在改activity里面
        */
        MySurfaceView.myID = getIntent().getExtras().getInt("name");


        mPaint.setStrokeWidth(paintStroke);//初始笔大小
        surfaceView = (MySurfaceView) findViewById(R.id.surfaceview);
        lastSelectPen = findViewById(R.id.orange_imageView); //默认铅笔
        lastSelectPen.startAnimation(AnimationUtils.loadAnimation(ColourDraw_Activity.this, R.anim.scale_zoom_out_anim));


        btn_black = (ImageButton) findViewById(R.id.black_imageView);
        btn_blue = (ImageButton) findViewById(R.id.blue_imageView);
        btn_brown = (ImageButton) findViewById(R.id.brown_imageView);
        btn_claret = (ImageButton) findViewById(R.id.claret_imageView);
        btn_clear = (ImageButton) findViewById(R.id.clear_imageButton);
        btn_eraser = (ImageButton) findViewById(R.id.eraser_imageView);
        btn_gray = (ImageButton) findViewById(R.id.gray_imageView);
        btn_green = (ImageButton) findViewById(R.id.green_imageView);
        btn_home = (ImageButton) findViewById(R.id.home_imageButton);
        btn_orange = (ImageButton) findViewById(R.id.orange_imageView);
        btn_pink = (ImageButton) findViewById(R.id.pink_imageView);
        btn_preservation = (ImageButton) findViewById(R.id.preservation_imageButton);
        btn_red = (ImageButton) findViewById(R.id.red_imageView);
        btn_violet = (ImageButton) findViewById(R.id.violet_imageView);
        btn_wathet = (ImageButton) findViewById(R.id.wathet_imageView);
        btn_yellow = (ImageButton) findViewById(R.id.yellow_imageView);
        btn_width = (Button) findViewById(R.id.paint_stroke);



        MyOnClickListener myOnClickListener = new MyOnClickListener();
        btn_yellow.setOnClickListener(myOnClickListener);
        btn_wathet.setOnClickListener(myOnClickListener);
        btn_violet.setOnClickListener(myOnClickListener);
        btn_red.setOnClickListener(myOnClickListener);
        btn_black.setOnClickListener(myOnClickListener);
        btn_blue.setOnClickListener(myOnClickListener);
        btn_brown.setOnClickListener(myOnClickListener);
        btn_claret.setOnClickListener(myOnClickListener);
        btn_clear.setOnClickListener(myOnClickListener);
        btn_eraser.setOnClickListener(myOnClickListener);
        btn_gray.setOnClickListener(myOnClickListener);
        btn_green.setOnClickListener(myOnClickListener);
        btn_home.setOnClickListener(myOnClickListener);
        btn_orange.setOnClickListener(myOnClickListener);
        btn_pink.setOnClickListener(myOnClickListener);
        btn_preservation.setOnClickListener(myOnClickListener);
        btn_width.setOnClickListener(myOnClickListener);


        //设置控件是否显示

/*
        surfaceView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                switch (event.getAction() & MotionEvent.ACTION_MASK) {
                    case MotionEvent.ACTION_DOWN:

                        break;
                    case MotionEvent.ACTION_UP:
                        break;
                    case MotionEvent.ACTION_POINTER_UP:
                        if(isVisible){
                            btn_yellow.setVisibility(View.INVISIBLE);
                            btn_wathet.setVisibility(View.INVISIBLE);
                            btn_violet.setVisibility(View.INVISIBLE);
                            btn_red.setVisibility(View.INVISIBLE);
                            btn_black.setVisibility(View.INVISIBLE);
                            btn_blue.setVisibility(View.INVISIBLE);
                            btn_brown.setVisibility(View.INVISIBLE);
                            btn_claret.setVisibility(View.INVISIBLE);
                            btn_clear.setVisibility(View.INVISIBLE);
                            btn_eraser.setVisibility(View.INVISIBLE);
                            btn_gray.setVisibility(View.INVISIBLE);
                            btn_green.setVisibility(View.INVISIBLE);
                            btn_home.setVisibility(View.INVISIBLE);
                            btn_orange.setVisibility(View.INVISIBLE);
                            btn_pink.setVisibility(View.INVISIBLE);
                            btn_preservation.setVisibility(View.INVISIBLE);
                            btn_width.setVisibility(View.INVISIBLE);
                            isVisible=false;
                        }else {
                            btn_yellow.setVisibility(View.VISIBLE);
                            btn_wathet.setVisibility(View.VISIBLE);
                            btn_violet.setVisibility(View.VISIBLE);
                            btn_red.setVisibility(View.VISIBLE);
                            btn_black.setVisibility(View.VISIBLE);
                            btn_blue.setVisibility(View.VISIBLE);
                            btn_brown.setVisibility(View.VISIBLE);
                            btn_claret.setVisibility(View.VISIBLE);
                            btn_clear.setVisibility(View.VISIBLE);
                            btn_eraser.setVisibility(View.VISIBLE);
                            btn_gray.setVisibility(View.VISIBLE);
                            btn_green.setVisibility(View.VISIBLE);
                            btn_home.setVisibility(View.VISIBLE);
                            btn_orange.setVisibility(View.VISIBLE);
                            btn_pink.setVisibility(View.VISIBLE);
                            btn_preservation.setVisibility(View.VISIBLE);
                            btn_width.setVisibility(View.VISIBLE);
                            isVisible=true;
                        }
                        break;
                    case MotionEvent.ACTION_POINTER_DOWN:

                        break;
                }
                    return true;
            }
        });*/
    }

    /*
    * 播放动画效果
    *  v 当前选中的铅笔
    */
    private void playAnim(View v) {
        if (lastSelectPen != null) {
            lastSelectPen.startAnimation(AnimationUtils.loadAnimation(ColourDraw_Activity.this,
                    R.anim.scale_zoom_in_anim));
            v.startAnimation(AnimationUtils.loadAnimation(ColourDraw_Activity.this,
                    R.anim.scale_zoom_out_anim));
        }
        lastSelectPen = v;
    }

    public class MyOnClickListener implements View.OnClickListener {

        //@Override
        public void onClick(View v) {
/*
            for (int i=0;i<PaintID.length;i++){
                playAnim(v); //显示动画效果

                MySurfaceView.mPath.reset();//重置当前路径
                MySurfaceView.mPaint.setColor(colorArray[i]);
            }*/

            switch (v.getId()) {
                case R.id.black_imageView:
                    playAnim(v);  //动画效果
                    MySurfaceView.mPath.reset();//重置当前路径
                    mPaint.setColor(Color.BLACK); //位画笔设置颜色
                    break;
                case R.id.brown_imageView:
                    playAnim(v);
                    MySurfaceView.mPath.reset();//重置当前路径
                    mPaint.setColor(getResources().getColor(R.color.BROWN));
                    break;
                case R.id.wathet_imageView:
                    playAnim(v);
                    MySurfaceView.mPath.reset();//重置当前路径
                    mPaint.setColor(getResources().getColor(R.color.WATHET));
                    break;
                case R.id.green_imageView:
                    playAnim(v);
                    MySurfaceView.mPath.reset();//重置当前路径
                    mPaint.setColor(Color.GREEN);
                    //surfaceView.setGreenPaint();
                    break;
                case R.id.violet_imageView:
                    playAnim(v);
                    MySurfaceView.mPath.reset();//重置当前路径
                    mPaint.setColor(getResources().getColor(R.color.VIOLET));
                    //surfaceView.setVioletPaint();
                    break;
                case R.id.blue_imageView:
                    playAnim(v);
                    MySurfaceView.mPath.reset();//重置当前路径
                    mPaint.setColor(Color.BLUE);
                    //surfaceView.setBluePaint();
                    break;
                case R.id.orange_imageView:
                    playAnim(v);
                    MySurfaceView.mPath.reset();//重置当前路径
                    mPaint.setColor(getResources().getColor(R.color.ORANGE));
                    //surfaceView.setOrangePaint();
                    break;
                case R.id.claret_imageView:
                    playAnim(v);
                    MySurfaceView.mPath.reset();//重置当前路径
                    mPaint.setColor(getResources().getColor(R.color.CLARET));
                    //surfaceView.setClaretPaint();
                    break;
                case R.id.pink_imageView:
                    playAnim(v);
                    MySurfaceView.mPath.reset();//重置当前路径
                    mPaint.setColor(getResources().getColor(R.color.PINK));
                    //surfaceView.setPinkPaint();
                    break;
                case R.id.yellow_imageView:
                    playAnim(v);
                    MySurfaceView.mPath.reset();//重置当前路径
                    mPaint.setColor(Color.YELLOW);
                    //surfaceView.setYellowPaint();
                    break;
                case R.id.gray_imageView:
                    playAnim(v);
                    MySurfaceView.mPath.reset();//重置当前路径
                    mPaint.setColor(Color.GRAY);
                    //surfaceView.setGrayPaint();
                    break;
                case R.id.red_imageView:
                    playAnim(v);
                    MySurfaceView.mPath.reset();//重置当前路径
                    mPaint.setColor(Color.RED);
                    //surfaceView.setRedPaint();
                    break;
                case R.id.eraser_imageView:
                    playAnim(v);
                    MySurfaceView.mPath.reset();//重置当前路径
                    mPaint.setColor(Color.WHITE);
                    //surfaceView.setEraserPaint();
                    break;
                case R.id.clear_imageButton:
                    surfaceView.clean();

                    MySurfaceView.mPath.reset();//重置当前路径
                    paintStroke = 5;
                    mPaint.setStrokeWidth(paintStroke);
                    btn_width.setText(" size " + 5);
                    sizeCount = 0;

                    break;
                case R.id.home_imageButton:
                    finish();
                    break;
                case R.id.preservation_imageButton:
                    surfaceView.saveImageToGallery();
                    break;
                case R.id.paint_stroke:
                    MySurfaceView.mPath.reset();//重置当前路径
                    if (sizeCount < 3) {
                        paintStroke = paintStroke + 15;
                        mPaint.setStrokeWidth(paintStroke);
                        btn_width.setText(" size " + String.valueOf(paintStroke));
                        sizeCount++;
                    } else {
                        paintStroke = 5;
                        mPaint.setStrokeWidth(paintStroke);
                        btn_width.setText(" size " + String.valueOf(paintStroke));
                        sizeCount = 0;
                    }
                    break;
                   /*  这里面不能用for循环
                   for (int i =0 ;i<4;i++){
                        MySurfaceView.mPath.reset();//重置当前路径
                        paintStroke = paintStroke + 15;
                        MySurfaceView.mPaint.setStrokeWidth(paintStroke);
                        if (i>=3){
                            MySurfaceView.mPaint.setStrokeWidth(15);
                        }
                    }*/
            }
        }
    }

}
