package jcydshanks.com.rixin.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import fragment.RixinDelegate;
import jcydshanks.com.rixin.R;


public class ShouyeFragment extends RixinDelegate {


    @Override
    public Object setLayout() {
        return R.layout.fragment_shouye;
    }

    @Nullable
    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);

        //TODO 处理事件
    }
}
