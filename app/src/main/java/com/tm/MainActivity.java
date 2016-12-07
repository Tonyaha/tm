package com.tm;

import android.app.Activity;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;

/**
 * Created by TSM on 2016/10/17.
 */
public class MainActivity extends AppCompatActivity {
    private MediaPlayer mediaPlayer;
    private Button btn_play, btn_pause, btn_stop, btn_replay, btn_last, btn_next;
   // private EditText editText;
    private int selectedFlag = 66;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        //editText = (EditText) findViewById(R.id.edit_text);
        btn_play = (Button) findViewById(R.id.bt_play);
        btn_pause = (Button) findViewById(R.id.bt_pause);
        btn_stop = (Button) findViewById(R.id.bt_stop);
        btn_replay = (Button) findViewById(R.id.bt_replay);
        btn_last = (Button) findViewById(R.id.bt_last);
        btn_next = (Button) findViewById(R.id.bt_next);

        btn_play.setOnClickListener(clickListener);
        btn_pause.setOnClickListener(clickListener);
        btn_stop.setOnClickListener(clickListener);
        btn_replay.setOnClickListener(clickListener);
        btn_last.setOnClickListener(clickListener);
        btn_next.setOnClickListener(clickListener);


        //  设置 Tabel 导航  在状态栏下面
        //actionBar.setNavigationMode(actionBar.NAVIGATION_MODE_TABS);

    }

    public View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.bt_play:
                    play();
                    break;
                case R.id.bt_pause:
                    pause();
                    break;
                case R.id.bt_stop:
                    stop();
                    break;
                case R.id.bt_replay:
                    replay();
                    break;
                case R.id.bt_last:
                    selectedFlag = 0;
                    stop();
                    play();
                    break;
                case R.id.bt_next:
                    selectedFlag = 1;
                    stop();
                    play();
                    break;
            }
        }
    };

    public void onClick(View v) {
        Intent intent = new Intent(MainActivity.this, ColourDraw_Activity.class);
        //Bundle bundle = new Bundle(); //第一种方法接收intent传递的数据，这里是id

        switch (v.getId()) {
            case R.id.imageA1:
                //bundle.putInt("name", R.drawable.a1);  //第一种方法接收intent传递的数据，这里是id
                //intent.putExtras(bundle);
                intent.putExtra("name", R.drawable.a1); //第二种方法接收intent传递的数据，这里是id
                break;
            case R.id.imageA2:
                intent.putExtra("name", R.drawable.a2);
                //intent.putExtras(bundle);
                break;
            case R.id.imageA3:
                intent.putExtra("name", R.drawable.a3);
                //intent.putExtras(bundle);
                break;
            case R.id.imageA4:
                intent.putExtra("name", R.drawable.a4);
                //intent.putExtras(bundle);
                break;
            case R.id.imageA5:
                intent.putExtra("name", R.drawable.a5);
                //intent.putExtras(bundle);
                break;
            case R.id.imageA6:
                intent.putExtra("name", R.drawable.a6);
                //intent.putExtras(bundle);
                break;
            case R.id.imageA7:
                intent.putExtra("name", R.drawable.a7);
                //intent.putExtras(bundle);
                break;
            case R.id.imageA8:
                intent.putExtra("name", R.drawable.a8);
                //intent.putExtras(bundle);
                break;
            case R.id.imageA9:
                intent.putExtra("name", R.drawable.a9);
                //intent.putExtras(bundle);
                break;
            case R.id.imageA10:
                intent.putExtra("name", R.drawable.a10);
                //intent.putExtras(bundle);
                break;
            case R.id.imageA11:
                intent.putExtra("name", R.drawable.a11);
                //intent.putExtras(bundle);
                break;
            case R.id.imageA12:
                intent.putExtra("name", R.drawable.a12);
                //intent.putExtras(bundle);
                break;
            case R.id.imageA13:
                intent.putExtra("name", R.drawable.a13);
                //intent.putExtras(bundle);
                break;
            case R.id.imageA14:
                intent.putExtra("name", R.drawable.a14);
                //intent.putExtras(bundle);
                break;
            case R.id.imageA15:
                intent.putExtra("name", R.drawable.a15);
                //intent.putExtras(bundle);
                break;
            case R.id.imageA16:
                intent.putExtra("name", R.drawable.a16);
                //intent.putExtras(bundle);
                break;
            case R.id.imageA17:
                intent.putExtra("name", R.drawable.a17);
                //intent.putExtras(bundle);
                break;
            case R.id.imageA18:
                intent.putExtra("name", R.drawable.a18);
                //intent.putExtras(bundle);
                break;
            case R.id.imageA19:
                intent.putExtra("name", R.drawable.a19);
                //intent.putExtras(bundle);
                break;
            case R.id.imageA20:
                intent.putExtra("name", R.drawable.a20);
                //intent.putExtras(bundle);
                break;
            case R.id.imageA21:
                intent.putExtra("name", R.drawable.a21);
                //intent.putExtras(bundle);
                break;
            case R.id.imageA22:
                intent.putExtra("name", R.drawable.a22);
                //intent.putExtras(bundle);
                break;
            case R.id.imageA23:
                intent.putExtra("name", R.drawable.a23);
                //intent.putExtras(bundle);
                break;
            case R.id.imageA24:
                intent.putExtra("name", R.drawable.a24);
                //intent.putExtras(bundle);
                break;
            case R.id.imageA25:
                intent.putExtra("name", R.drawable.a25);
                //intent.putExtras(bundle);
                break;
            case R.id.imageA26:
                intent.putExtra("name", R.drawable.a26);
                //intent.putExtras(bundle);
                break;
            case R.id.imageA27:
                intent.putExtra("name", R.drawable.a27);
                //intent.putExtras(bundle);
                break;
        }
        startActivity(intent);
    }


    public boolean onCreateOptionsMenu(Menu menu) {
        //MenuInflater menuInflater = getMenuInflater();
        getMenuInflater().inflate(R.menu.main_menu, menu);
        //MenuItem shareItem = menu.findItem(R.id.share);
        // ShareActionProvider provider = (ShareActionProvider) shareItem.getActionProvider();
        //provider.setShareIntent(getDefaultIntent());
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.home:
                finish();
                return true;
            case R.id.user:
                Toast.makeText(this, "你点击了“播放”按键！", Toast.LENGTH_SHORT).show();
                play();
                return true;
            case R.id.setting:
                Toast.makeText(this, "你点击了“设置”按键！", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void play() {
        //String fileName = editText.getText().toString().trim();
        //if( !fileName.isEmpty()){
        try {
            mediaPlayer = new MediaPlayer();
            AssetManager assetManager = getAssets();
            AssetFileDescriptor fileDescriptor = assetManager.openFd("a2.mp3");

            if(selectedFlag==0){
                fileDescriptor = assetManager.openFd("a1.mp3");
            }else if(selectedFlag==1){
                fileDescriptor = assetManager.openFd("a2.mp3");
            }


            //设置流媒体地址
            mediaPlayer.setDataSource(fileDescriptor.getFileDescriptor(), fileDescriptor.getStartOffset(), fileDescriptor.getLength());
            //设置音频类型
            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);

            //通过异步方式加载流媒体
            mediaPlayer.prepareAsync();
            mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mediaPlayer) {
                    //加载完毕，开始播放音频
                    mediaPlayer.start();
                    Toast.makeText(MainActivity.this, "开始播放", Toast.LENGTH_LONG).show();
                    //避免重复播放，把播放按钮设置为不可用
                    btn_play.setEnabled(false);
                }
            });

            //设置循环播放
            //medioPlay.setLoopring(true);
            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mediaPlayer) {
                    //在播放完毕后回调
                    btn_play.setEnabled(true);
                }
            });

            mediaPlayer.setOnErrorListener(new MediaPlayer.OnErrorListener() {
                @Override
                public boolean onError(MediaPlayer mediaPlayer, int i, int i1) {
                    replay();
                    return false;
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "文件不存在", Toast.LENGTH_LONG).show();
        }
       /*else {
            Toast.makeText(this,"文件不存在",Toast.LENGTH_LONG).show();
        }*/
    }


    /*private void play() {
        String fileName = editText.getText().toString().trim();
      if( !fileName.isEmpty()){
          try {
              mediaPlayer = new MediaPlayer();
              AssetManager assetManager = getAssets();
              AssetFileDescriptor fileDescriptor=assetManager.openFd("a1.mp3");

              if(fileName.equals("a1.mp3") || fileName.equals("a1")){
                 fileDescriptor = assetManager.openFd("a1.mp3");
              }else if(fileName.equals("a2.mp3") || fileName.equals("a2")){
                  fileDescriptor = assetManager.openFd("a2.mp3");
              }


              //设置流媒体地址
              mediaPlayer.setDataSource(fileDescriptor.getFileDescriptor(),fileDescriptor.getStartOffset(),fileDescriptor.getLength());
              //设置音频类型
              mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);

              //通过异步方式加载流媒体
              mediaPlayer.prepareAsync();
              mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                  @Override
                  public void onPrepared(MediaPlayer mediaPlayer) {
                      //加载完毕，开始播放音频
                      mediaPlayer.start();
                      Toast.makeText(MainActivity.this,"开始播放",Toast.LENGTH_LONG).show();
                      //避免重复播放，把播放按钮设置为不可用
                      btn_play.setEnabled(false);
                  }
              });

              //设置循环播放
              //medioPlay.setLoopring(true);
              mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                  @Override
                  public void onCompletion(MediaPlayer mediaPlayer) {
                      //在播放完毕后回调
                      btn_play.setEnabled(true);
                  }
              });

              mediaPlayer.setOnErrorListener(new MediaPlayer.OnErrorListener() {
                  @Override
                  public boolean onError(MediaPlayer mediaPlayer, int i, int i1) {
                      replay();
                      return false;
                  }
              });

          }catch (Exception e){
              e.printStackTrace();
              Toast.makeText(this,"播放失败",Toast.LENGTH_LONG).show();
          }
      }else {
          Toast.makeText(this,"文件不存在",Toast.LENGTH_LONG).show();
      }
    }
*/
    private void replay() {
        //判断是否正在播放
        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            //把播放进度设置为 0
            mediaPlayer.seekTo(0);
            Toast.makeText(this, "重复播放", Toast.LENGTH_LONG).show();
            btn_pause.setText("暂停");
            return;
        }
        play();
    }

    protected void pause() {
        if (btn_pause.getText().toString().trim().equals("继续")) {
            //播放
            mediaPlayer.start();
            btn_pause.setText("暂停");
            Toast.makeText(this, "继续播放", Toast.LENGTH_LONG).show();
            return;
        }
        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
            btn_pause.setText("继续");
            Toast.makeText(this, "暂停播放", Toast.LENGTH_LONG).show();
        }
    }

    protected void stop() {
        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            mediaPlayer.stop();
            //释放资源
            mediaPlayer.release();
            mediaPlayer = null;
            btn_play.setEnabled(true);
            Toast.makeText(this, "停止播放", Toast.LENGTH_LONG).show();
        }
    }

    protected void onDestroy() {
        //在activity结束时回收资源
        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            mediaPlayer.stop();
            //释放资源
            mediaPlayer.release();
            mediaPlayer = null;
        }
        super.onDestroy();
    }

    @Override
    public void finish() {
        super.finish();
    }


}
