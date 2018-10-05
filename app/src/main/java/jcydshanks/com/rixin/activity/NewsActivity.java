package jcydshanks.com.rixin.activity;

import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.gyf.barlibrary.ImmersionBar;

import net.tsz.afinal.annotation.view.ViewInject;

import jcydshanks.com.rixin.R;

public class NewsActivity extends BaseActivity {

    private Toolbar toolbar;
    private TextView contentText;
    private ImageView imageView;
    private CollapsingToolbarLayout collapsingToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        initView();
        setSupportActionBar(toolbar);
        ActionBar actionBar=getSupportActionBar();
        if (actionBar!=null){
            actionBar.setDisplayShowHomeEnabled(true);
        }
        Glide.with(this).load(R.drawable.start_default).into(imageView);
        String textdiscription=generateContent("Content Text");
        contentText.setText(textdiscription);
    }

    private void initView() {
        collapsingToolbar=findViewById(R.id.collapsing_toolbar);
        imageView=findViewById(R.id.background_title_image);
        contentText=findViewById(R.id.discription_text);
        toolbar=findViewById(R.id.toolbar);
        collapsingToolbar.setTitle("测试");
    }

    private String generateContent(String text){
        StringBuilder content=new StringBuilder();
        for (int i=0;i<300;i++){
            content.append(text);
        }
        return content.toString();
    }

}
