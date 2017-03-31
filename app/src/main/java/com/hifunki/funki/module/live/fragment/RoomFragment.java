package com.hifunki.funki.module.live.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.hifunki.funki.R;
import com.hifunki.funki.client.User;
import com.hifunki.funki.module.live.event.EventPlayContent;
import com.hifunki.funki.module.live.mode.ChatMessage;
import com.hifunki.funki.module.live.viewholder.ChatComing;
import com.hifunki.funki.module.live.viewholder.ChatFan;
import com.hifunki.funki.module.live.viewholder.ChatText;
import com.hifunki.funki.module.pick.DatePick;
import com.hifunki.funki.net.back.LiveModel;
import com.powyin.scroll.adapter.MultipleRecycleAdapter;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by xiongxingxing on 16/12/3.
 */

public class RoomFragment extends Fragment {
    public static RoomFragment newInstance(LiveModel model) {
        Bundle args = new Bundle();
        RoomFragment fragment = new RoomFragment();

        if(model!=null){
            args.putString(LiveModel.class.getName(),new Gson().toJson(model));
        }

        fragment.setArguments(args);
        return fragment;
    }




//    // 控制滑动状态改变
//    private BannerSwitch.OnButtonLineScrollListener lineScrollListener = new BannerSwitch.OnButtonLineScrollListener() {
//        @Override
//        public void onButtonLineScroll(int viewCount, int leftIndex, int rightIndex, View leftView, View rightView, float leftNearWei, float rightNearWei) {
//            for(int i=0;i<viewCount;i++){
//                bannerSwitch.getChildAt(i).findViewById(R.id.line).setAlpha(0);
//            }
//            leftIndex = leftIndex - 3;                                            //left 返回中间主显示对象； 需要向左偏移3单位 才是第一项选择；
//            leftIndex = leftIndex<0 ? viewCount+leftIndex : leftIndex;
//
//            View line =  bannerSwitch.getChildAt(leftIndex).findViewById(R.id.line);
//            line.setAlpha(1);
//
//            leftIndex ++ ;
//            if(leftIndex>=viewCount){
//                leftIndex=rightIndex;
//            }
//            line =  bannerSwitch.getChildAt(leftIndex).findViewById(R.id.line);
//            line.setAlpha(0.7f);
//
//            leftIndex ++ ;
//            if(leftIndex>=viewCount){
//                leftIndex=rightIndex;
//            }
//            line =  bannerSwitch.getChildAt(leftIndex).findViewById(R.id.line);
//            line.setAlpha(0.3f);
//
//            leftIndex ++ ;
//            if(leftIndex>=viewCount){
//                leftIndex=rightIndex;
//            }
//            line =  bannerSwitch.getChildAt(leftIndex).findViewById(R.id.line);
//            line.setAlpha(0);
//        }
//    };




    private View mainView;
    private LiveModel model;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String json =  getArguments().getString(LiveModel.class.getName(),"");                   //接受数据
        if(!TextUtils.isEmpty(json)){
            model = new Gson().fromJson(json,LiveModel.class);
        }
    }

    @BindView(R.id.host_avatar)
    ImageView hostAvatar;

    @BindView(R.id.banner_fan)
    RecyclerView recyclerViewAvatar;


    @BindView(R.id.host_chat)
    RecyclerView recyclerViewMessage;


    MultipleRecycleAdapter<ChatMessage> messageMultipleRecycleAdapter;
    MultipleRecycleAdapter<User> avatarMutipleRecycleAdapter;

    @OnClick({
            R.id.host_avatar
    })
    void onClick(View view){

        DatePick datePick = new DatePick(getActivity());

        datePick.setOnDatePickListener(new DatePick.OnPickListener() {
            @Override
            public void onLocationSelect(Date date) {

            }
        });

        datePick.showFullScreen();

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mainView = inflater.inflate(R.layout.fragment_room, container, false);
        ButterKnife.bind(this,mainView);

        Glide.with(this).load("http://v1.qzone.cc/avatar/201408/03/23/44/53de58e5da74c247.jpg%21200x200.jpg").into(hostAvatar);



        messageMultipleRecycleAdapter = MultipleRecycleAdapter.getByViewHolder(getActivity(),ChatComing.class, ChatText.class);
        avatarMutipleRecycleAdapter = MultipleRecycleAdapter.getByViewHolder(getActivity(),ChatFan.class);

        recyclerViewAvatar.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL,false));
        recyclerViewAvatar.setAdapter(avatarMutipleRecycleAdapter);
        recyclerViewMessage.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerViewMessage.setAdapter(messageMultipleRecycleAdapter);


        // 测试数据--------------
        List<User> users = new ArrayList<>();
        for(int i=0 ;i <10 ;i ++){
            users.add(new User());
        }
        avatarMutipleRecycleAdapter.addLast(users);


        List<ChatMessage> chatMessages = new ArrayList<>();
        chatMessages.add(new ChatMessage("生气的路透", "可以表扬什么呢？？？", ChatMessage.TYPE.person_in));
        chatMessages.add(new ChatMessage("风筝的画廓", "剋来插手个吧？？？", ChatMessage.TYPE.person_in));
        chatMessages.add(new ChatMessage("颜色变化", "本文为博主原创文章，未经博主允许不得转载。", ChatMessage.TYPE.text));
        chatMessages.add(new ChatMessage("嵌入式企鹅圈", "如果你连日常工作的一些问题都解决不好，你也别期望自己能在很短的时间内提升很高的水平。还是那句话，就算你有十年的工作经验，如果你只是一年", ChatMessage.TYPE.text));
        chatMessages.add(new ChatMessage("颜色变化", "本文为博主原创文章，未经博主允许不得转载。", ChatMessage.TYPE.text));
        chatMessages.add(new ChatMessage("过程语言 ", "HAWQ我所使用过的SQL-on-Hadoop解决方案中唯一支持过程化", ChatMessage.TYPE.text));
        chatMessages.add(new ChatMessage("生气的路透", "可以表扬什么呢？？？", ChatMessage.TYPE.person_in));
        chatMessages.add(new ChatMessage("颜色变化", "本文为博主原创文章，未经博主允许不得转载。", ChatMessage.TYPE.text));
        chatMessages.add(new ChatMessage("颜色变化", "本文为博主原创文章，未经博主允许不得转载。", ChatMessage.TYPE.text));
        messageMultipleRecycleAdapter.addLast(chatMessages);
        // 测试数据--------------

        return mainView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


    }



    private boolean reResume = false;      //用于控制当前显示视频
    private boolean isVisual = false;      //用于控制当前显示视频

    private void startPlay(){
        if(reResume && isVisual){
            EventBus.getDefault().post(new EventPlayContent());
        }
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
        startPlay();
    }





}

