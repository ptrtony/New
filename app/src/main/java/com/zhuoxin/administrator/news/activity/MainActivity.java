package com.zhuoxin.administrator.news.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.zhuoxin.administrator.news.Fragment.CollectFragment;
import com.zhuoxin.administrator.news.Fragment.CollectImageFragment;
import com.zhuoxin.administrator.news.Fragment.ImageFragment;
import com.zhuoxin.administrator.news.Fragment.NewsFragment;
import com.zhuoxin.administrator.news.Fragment.ScrollingFragment;
import com.zhuoxin.administrator.news.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    @BindView(R.id.layout_framelayout)
    FrameLayout mFranlayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initSystemUI();
        showScrollingFragment();
    }



    public void showScrollingFragment(){
        ScrollingFragment scrollingFragment=new ScrollingFragment();
        FragmentManager fragmentManager=getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.layout_framelayout,scrollingFragment).commit();

    }
    public void showImageFragment(){
        ImageFragment imageFragment=new ImageFragment();
        FragmentManager fragmentManager=getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.layout_framelayout,imageFragment).commit();

    }
    public void showCollectFragment(){
        CollectFragment collectFragment=new CollectFragment();
        FragmentManager fragmentManager=getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.layout_framelayout,collectFragment).commit();

    }
    public void showImageCollectFragment(){
        CollectImageFragment collectImageFragment=new CollectImageFragment();
        FragmentManager fragmentFragment=getSupportFragmentManager();
        fragmentFragment.beginTransaction().replace(R.id.layout_framelayout,collectImageFragment).commit();
    }
    private boolean isExit;
    private void initSystemUI() {
        //工具栏
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //把toolbar作为actionBar显示出来
        setSupportActionBar(toolbar);
        //浮动按钮
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        //设置单击事件
        fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                //显示条状提示栏
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        //抽屉布局  主页面内容，侧边框（导航菜单）内容

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        //创建抽屉的开关按钮
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        //设置抽屉的开关侦听
        drawer.setDrawerListener(toggle);
        //同步开关按钮的状态
        toggle.syncState();
        //导航菜单，侧边栏
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        //设置导航栏中的单击事件
        navigationView.setNavigationItemSelectedListener(this);
    }


    //返回按钮的设置
    @Override
    public void onBackPressed() {
        //获取抽屉布局
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        //判断抽屉是否打开   如果打开，则关闭抽屉
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            //如果关闭 调用父类方法
//            super.onBackPressed();
            if(!isExit){
                isExit=true;
                Toast.makeText(this, "再按一次退出", Toast.LENGTH_SHORT).show();
                drawer.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        isExit=false;
                    }
                },2000);
            }
            else{
                finish();
            }
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //填充菜单  到actionbar上面，如果actionbar显示则他也显示出来
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    //设置菜单的单击事件
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_news) {
            // Handle the camera action
            Toast.makeText(this, "新闻", Toast.LENGTH_SHORT).show();
            showScrollingFragment();
        } else if (id == R.id.nav_image) {
            Toast.makeText(this, "图片", Toast.LENGTH_SHORT).show();
            showImageFragment();

        } else if (id == R.id.nav_collect) {
            Toast.makeText(this, "收藏", Toast.LENGTH_SHORT).show();
            showCollectFragment();

        } else if (id == R.id.nav_manage) {
            Toast.makeText(this, "收藏图片", Toast.LENGTH_SHORT).show();
            showImageCollectFragment();

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }
        //整个activity的抽屉布局

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        //关闭导航菜单（侧边栏）
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
