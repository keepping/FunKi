package com.hifunki.funki.module.live.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.gson.Gson;
import com.hifunki.funki.R;
import com.hifunki.funki.module.live.event.EventPlayContent;
import com.hifunki.funki.net.back.LiveModel;
import com.hifunki.funki.util.TextUtil;

import org.greenrobot.eventbus.EventBus;

import java.util.Locale;
import java.util.Random;

import tv.danmaku.ijk.media.player.IjkMediaPlayer;

/**
 * Created by xiongxingxing on 16/12/3.
 */

public class RoomFragment extends Fragment {


    private View mainView;

    private LiveModel model;


    public static RoomFragment newInstance(LiveModel model) {
        Bundle args = new Bundle();
        RoomFragment fragment = new RoomFragment();

        if(model!=null){
            args.putString(LiveModel.class.getName(),new Gson().toJson(model));
        }

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String json =  getArguments().getString(LiveModel.class.getName(),"");                   //接受数据
        if(!TextUtils.isEmpty(json)){
            model = new Gson().fromJson(json,LiveModel.class);
        }
    }



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        mainView = inflater.inflate(R.layout.fragment_room, container, false);

        return mainView;
    }


    private boolean reResume = false;      //用于控制当前显示视频
    private boolean isVisual = false;      //用于控制当前显示视频

    private void startPlay(){
        if(reResume && isVisual){
            EventBus.getDefault().post(new EventPlayContent());
        }
    }




    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }






    @Override
    public void onResume() {
        super.onResume();
        reResume = true;
        startPlay();
    }

    @Override
    public void onPause() {
        super.onPause();
        reResume = false;
    }
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        isVisual = isVisibleToUser;
        if(isVisibleToUser){
            startPlay();
        }
    }

}

