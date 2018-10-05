package jcydshanks.com.rixin.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Build;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import net.tsz.afinal.FinalActivity;
import net.tsz.afinal.FinalBitmap;
import net.tsz.afinal.FinalHttp;
import net.tsz.afinal.annotation.view.ViewInject;
import net.tsz.afinal.http.AjaxCallBack;
import net.tsz.afinal.http.AjaxParams;

import java.io.File;
import java.util.TimerTask;

import jcydshanks.com.rixin.Global;
import jcydshanks.com.rixin.MainActivity;
import jcydshanks.com.rixin.R;
import jcydshanks.com.rixin.net.NetApi;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class StartActivity extends AppCompatActivity implements NetApi {
    private TimeCount time;             //时间计数
    private boolean isFirstIn = false;  //首次启动
    private static final int GO_HOME = 1000;        //非首次启动时加载本地的图片
    private static final int GO_GUIDE = 1001;       //首次加载引导
    private static final int GO_MAIN=1002;          //引导到mainActivity
    private String imageUrl = "";

    @ViewInject(id =R.id.guide_img)ImageView guide_img;
    @ViewInject(id = R.id.tg,click = "onClick")TextView tg;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Window window = getWindow();
        //隐藏标题栏
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //隐藏状态栏
        //定义全屏参数
        int flag= WindowManager.LayoutParams.FLAG_FULLSCREEN;
        //设置当前窗体为全屏显示
        window.setFlags(flag, flag);
        setContentView(R.layout.activity_start);
        FinalActivity.initInjectedView(this);
        time=new TimeCount(6000,1000);
        mHandler.sendEmptyMessageDelayed(1000,2500);
        getBingWallPaper(1080,1920);

    }

    @Override
    public Call<ResponseBody> getBingWallPaper(int w, int h) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Global.BingURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        NetApi netApi = retrofit.create(NetApi.class);
        Call<ResponseBody> call = netApi.getBingWallPaper(w,h);

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                imageUrl="https://bing.ioliu.cn/v1?w=1080&h=1920";
                Message message =new Message();
                message.what=1000;
                message.obj = response.body();
                mHandler.sendMessage(message);
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
        return null;
    }

    /**
     * 计时器类
     */

    private class TimeCount extends CountDownTimer{

        //参数依次为总时长,和计时的时间间隔
        public TimeCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {
            if(millisUntilFinished<=1000){
                time.onFinish();
                time.cancel();
            }
            tg.setText("跳过"+millisUntilFinished/1000+"s");
        }

        @Override
        public void onFinish() {
            if (!isFirstIn){
                mHandler.sendEmptyMessage(GO_MAIN);

            }else {
                mHandler.sendEmptyMessage(GO_GUIDE);
            }
        }
    }


    /**
     * 点击事件
     */
    public void onClick(View v){
        switch (v.getId()){
            case R.id.tg:
                time.onFinish();
                time.cancel();
                // 判断程序与第几次运行，如果是第一次运行则跳转到引导界面，否则跳转到主界面
                if (!isFirstIn) {
                    // 使用Handler的postDelayed方法，3秒后执行跳转到MainActivity
                    mHandler.sendEmptyMessage(GO_MAIN);
                } else {
                    mHandler.sendEmptyMessage(GO_GUIDE);
                }
                break;
        }
    }


    /**
     * 处理消息
     *
     */

    private Handler mHandler=new Handler(){
        public void handleMessage(Message message){
            switch (message.what){
                case GO_HOME:
                    try {

                        Glide.with(StartActivity.this).load(imageUrl).error(R.drawable.start_default).into(guide_img);
                        tg.setVisibility(View.VISIBLE);
                        time.start();

                    }catch (Exception e){
                        e.printStackTrace();
                    }
                    break;
                case GO_GUIDE:
                    goHome();
                    break;
                case  GO_MAIN:
                    goHome();
                    break;
            }
            super.handleMessage(message);
        }

    };

    private void goHome(){
        Intent intent=new Intent(this,MainActivity.class);
        startActivity(intent);
        finish();
    }

    private void goGudie(){
//        Intent intent=new Intent(this,WelcomeActivity.class);
//        startActivity(intent);
        finish();

    }


}
