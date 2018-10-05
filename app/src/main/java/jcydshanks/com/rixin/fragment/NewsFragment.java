package jcydshanks.com.rixin.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import net.tsz.afinal.FinalBitmap;
import net.tsz.afinal.annotation.view.ViewInject;

import fragment.RixinDelegate;
import jcydshanks.com.rixin.R;


public class NewsFragment extends RixinDelegate {

    @ViewInject(id = R.id.head_img,click = "OnClick")ImageView head_img;
    @ViewInject(id = R.id.draw_layout)DrawerLayout drawLayout;
    @ViewInject(id= R.id.test)ImageView test;
    public FinalBitmap finalBitmap;

    @Override
    public Object setLayout() {

        return R.layout.fragment_news;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);


    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }
    public void OnClick(View view){
        switch (view.getId()){
            case R.id.head_img:
                drawLayout.openDrawer(GravityCompat.START);
                break;

        }
    }

}
