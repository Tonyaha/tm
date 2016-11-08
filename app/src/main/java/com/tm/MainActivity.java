package com.tm;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

/**
 * Created by TSM on 2016/10/17.
 */
public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        //  设置 Tabel 导航  在状态栏下面
        //actionBar.setNavigationMode(actionBar.NAVIGATION_MODE_TABS);

    }

    public void onClick(View v) {
        Intent intent = new Intent(MainActivity.this,ColourDraw_Activity.class);
        //Bundle bundle = new Bundle(); //第一种方法接收intent传递的数据，这里是id

        switch (v.getId()){
            case R.id.imageA1:
                //bundle.putInt("name", R.drawable.a1);  //第一种方法接收intent传递的数据，这里是id
                //intent.putExtras(bundle);
                intent.putExtra("name",R.drawable.a1); //第二种方法接收intent传递的数据，这里是id
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
        getMenuInflater().inflate(R.menu.main_menu,menu);
        //MenuItem shareItem = menu.findItem(R.id.share);
        // ShareActionProvider provider = (ShareActionProvider) shareItem.getActionProvider();
        //provider.setShareIntent(getDefaultIntent());
        return super.onCreateOptionsMenu(menu);
    }
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.home:
                finish();
                return true;
            case R.id.user:
                Toast.makeText(this, "你点击了“用户”按键！", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.setting:
                Toast.makeText(this, "你点击了“设置”按键！", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void finish() {
        super.finish();
    }
}
