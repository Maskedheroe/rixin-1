package jcydshanks.com.rixin;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.GravityCompat;


import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import fragment.RixinDelegate;
import jcydshanks.com.rixin.activity.BaseActivity;
import jcydshanks.com.rixin.activity.NewsActivity;
import jcydshanks.com.rixin.activity.WallPaperActivity;
import jcydshanks.com.rixin.fragment.NewsFragment;
import jcydshanks.com.rixin.fragment.ShouyeFragment;
import jcydshanks.com.rixin.fragment.UserFragment;
import jcydshanks.com.rixin.net.NetApi;
import jcydshanks.com.rixin.utils.NoScrollViewPager;
import okhttp3.ResponseBody;
import retrofit2.Call;


public class MainActivity extends BaseActivity implements View.OnClickListener {

//    储存fragment
    private List<Fragment> TAB_FRAGMENTS = new ArrayList();
//    tab图片数量
    private final int COUNT = Global.TAB_IMGS.length;
//    实例化三个fragment对象
    private NewsFragment newsFragment;
    private ShouyeFragment shouyeFragment;
    private UserFragment userFragment;

    private TabViewPagerAdapter mAdapter;
    private NoScrollViewPager mViewPager;
    private TabLayout tabLayout;
    private NavigationView nav;
    private DrawerLayout drawLayout;
    private ImageView head_img;
    private TextView tv_title;
    private ImageView notification;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (Build.VERSION.SDK_INT >= 21) {
            View decorView = getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
            decorView.setSystemUiVisibility(option);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
        newsFragment = new NewsFragment();
        shouyeFragment = new ShouyeFragment();
        userFragment = new UserFragment();
        TAB_FRAGMENTS.add(newsFragment);
        TAB_FRAGMENTS.add(shouyeFragment);
        TAB_FRAGMENTS.add(userFragment);
        initView();
        openLeftMenu();
    }

    //初始化界面
    private void initView() {
        tabLayout = (TabLayout) findViewById(R.id.tablayout);
        mAdapter = new TabViewPagerAdapter(getSupportFragmentManager());
        mViewPager = (NoScrollViewPager) findViewById(R.id.viewpager);
        drawLayout = (DrawerLayout) findViewById(R.id.draw_layout);
        tv_title = (TextView) findViewById(R.id.tv_title);
        head_img = findViewById(R.id.head_img);
        notification=findViewById(R.id.notification_img);
        nav = findViewById(R.id.nav_view);
        mViewPager.setAdapter(mAdapter);
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));


        setTabs(tabLayout, this, getLayoutInflater(), Global.TAB_IMGS);
        tv_title.setText("新闻");
        notification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, NewsActivity.class));
            }
        });
    }


    //  侧滑监听
    private void openLeftMenu() {

        nav.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.cover_wallpaper:
                        startActivity(new Intent(MainActivity.this,WallPaperActivity.class));
                        break;
                }

                drawLayout.closeDrawers();//关闭侧滑

                return true;
            }
        });

        if (head_img != null) {
            head_img.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    drawLayout.openDrawer(GravityCompat.START);
                }
            });
        }

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (tv_title != null) {
                    switch (position) {
                        case 0:
                            tv_title.setText("新闻");
                            break;
                        case 1:
                            tv_title.setText("首页");

                            break;
                        case 2:
                            tv_title.setText("我");
                            break;
                    }
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }
    //设置底部tab导航

    private void setTabs(TabLayout tabLayout, MainActivity mainActivity, LayoutInflater inflater, int[] tabImags) {
        for (int i = 0; i < tabImags.length; i++) {
            TabLayout.Tab tab = tabLayout.newTab();
            View view = inflater.inflate(R.layout.item_tab, null);
            tab.setCustomView(view);
            ImageView imgTab = (ImageView) view.findViewById(R.id.img_tab);
            imgTab.setImageResource(Global.TAB_IMGS[i]);
            tabLayout.addTab(tab);

        }

    }


    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back:
                onBackPressed();
                break;
            case R.id.head_img:
                drawLayout.openDrawer(GravityCompat.START);
                break;
            case R.id.notification_img:
                startActivity(new Intent(MainActivity.this, NewsActivity.class));
                break;
        }

    }



    //适配器

    private class TabViewPagerAdapter extends FragmentPagerAdapter {

        public TabViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {

            return TAB_FRAGMENTS.get(position);
        }

        @Override
        public int getCount() {
            return COUNT;
        }


    }
}
