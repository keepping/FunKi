package com.hifunki.funki.module.live.fragment;

import android.graphics.Bitmap;
import android.graphics.PointF;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.hifunki.funki.R;
import com.hifunki.funki.base.fragment.BaseFragment;
import com.hifunki.funki.client.User;
import com.hifunki.funki.module.live.danmu.bDanMu.DanMuData;
import com.hifunki.funki.module.live.danmu.bDanMu.DanMuKuHelper;
import com.hifunki.funki.module.live.danmu.vDanMu.DanMuGroup;
import com.hifunki.funki.module.live.danmu.vDanMu.ModelGift;
import com.hifunki.funki.module.live.event.EventPlayContent;
import com.hifunki.funki.module.live.mode.ChatMessage;
import com.hifunki.funki.module.live.viewholder.ChatComing;
import com.hifunki.funki.module.live.viewholder.ChatFan;
import com.hifunki.funki.module.live.viewholder.ChatText;
import com.hifunki.funki.module.live.viewholder.Gift;
import com.hifunki.funki.module.live.widget.BlockView;
import com.hifunki.funki.net.back.LiveModel;
import com.hifunki.funki.util.DisplayUtil;
import com.hifunki.funki.util.PopWindowUtil;
import com.hifunki.funki.widget.bessel.DivergeView2;
import com.powyin.scroll.adapter.MultipleRecycleAdapter;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import master.flame.danmaku.ui.widget.DanmakuView;

/**
 * 观众端fragment
 *
 * @author monotone
 * @version V1.0 <描述当前版本功能>
 * @value com.hifunki.funki.module.live.fragment.RoomFragment.java
 * @link
 * @since 2017-03-25 13:37:37
 */
public class RoomFragment extends BaseFragment {

    @BindView(R.id.host_avatar)
    ImageView hostAvatar;
    @BindView(R.id.banner_fan)
    RecyclerView recyclerViewAvatar;
    @BindView(R.id.rl_gift)
    RecyclerView rlGift;
    @BindView(R.id.host_chat)
    RecyclerView recyclerViewMessage;
    @BindView(R.id.dv)
    DivergeView2 divergeView3;
    @BindView(R.id.tv_follow)
    TextView tvFollow;
    @BindView(R.id.dan_mu_group)
    DanMuGroup danMuGroup;
    @BindView(R.id.dan_mu_text)
    DanmakuView mDanmakuView;
    @BindView(R.id.iv_room_msg)
    ImageView ivRoomMsg;

    MultipleRecycleAdapter<ChatMessage> messageMultipleRecycleAdapter;
    MultipleRecycleAdapter<User> avatarMultipleRecycleAdapter;
    MultipleRecycleAdapter<String> giftAdapter;
    @BindView(R.id.block_1)
    BlockView block1;
    @BindView(R.id.block_2)
    BlockView block2;
    @BindView(R.id.fan_name)
    TextView fanName;
    @BindView(R.id.fan_num)
    TextView fanNum;
    @BindView(R.id.fan_location)
    LinearLayout fanLocation;
    @BindView(R.id.iv_room_exit)
    ImageView ivRoomExit;
    @BindView(R.id.rl_info)
    RelativeLayout rlInfo;
    @BindView(R.id.room_view)
    FrameLayout roomView;
    private boolean reResume = false;      //用于控制当前显示视频
    private boolean isVisual = false;      //用于控制当前显示视频
    private LiveModel model;
    private ArrayList<Bitmap> mList;//弹幕bitmap
    private int mIndex = 0;
    private PopWindowUtil sharePopWindow;//分享popWindow
    private View shareView;

    DanMuKuHelper danMuKuHelper;

    public static RoomFragment newInstance(LiveModel model) {
        Bundle args = new Bundle();
        RoomFragment fragment = new RoomFragment();
        if (model != null) {
            args.putString(LiveModel.class.getName(), new Gson().toJson(model));
        }
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_room;
    }

    @Override
    protected void initVariable() {
        super.initVariable();
        String json = mBundle.getString(LiveModel.class.getName(), "");                   //接受数据
        if (!TextUtils.isEmpty(json)) {
            model = new Gson().fromJson(json, LiveModel.class);
        }
        //加载主播头像
        Glide.with(this).load("http://v1.qzone.cc/avatar/201408/03/23/44/53de58e5da74c247.jpg%21200x200.jpg").into(hostAvatar);

        //游客头像
        avatarMultipleRecycleAdapter = MultipleRecycleAdapter.getByViewHolder(getActivity(), ChatFan.class);
        recyclerViewAvatar.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        recyclerViewAvatar.setAdapter(avatarMultipleRecycleAdapter);
        // 测试数据--------------
        List<User> users = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            users.add(new User());
        }
        avatarMultipleRecycleAdapter.addLast(users);

        //礼物
        giftAdapter = MultipleRecycleAdapter.getByViewHolder(getActivity(), Gift.class);
        rlGift.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        rlGift.setAdapter(avatarMultipleRecycleAdapter);
        // 测试数据--------------
        List<String> strings = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            strings.add(new String());
        }
        giftAdapter.addLast(strings);

        //聊天信息
        messageMultipleRecycleAdapter = MultipleRecycleAdapter.getByViewHolder(getActivity(), ChatComing.class, ChatText.class);
        recyclerViewMessage.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerViewMessage.setAdapter(messageMultipleRecycleAdapter);
        List<ChatMessage> chatMessages = new ArrayList<>();
//        chatMessages.add(new ChatMessage("生气的路透", "可以表扬什么呢？？？", ChatMessage.TYPE.person_in));
//        chatMessages.add(new ChatMessage("风筝的画廓", "剋来插手个吧？？？", ChatMessage.TYPE.person_in));
        chatMessages.add(new ChatMessage("颜色变化", "本文为博主原创文章，未经博主允许不得转载。", ChatMessage.TYPE.text));
        chatMessages.add(new ChatMessage("嵌入式企鹅圈", "如果你连日常工作的一些问题都解决不好，你也别期望自己能在很短的时间内提升很高的水平。还是那句话，就算你有十年的工作经验，如果你只是一年", ChatMessage.TYPE.text));
        chatMessages.add(new ChatMessage("颜色变化", "本文为博主原创文章，未经博主允许不得转载。", ChatMessage.TYPE.text));
        chatMessages.add(new ChatMessage("过程语言 ", "HAWQ我所使用过的SQL-on-Hadoop解决方案中唯一支持过程化", ChatMessage.TYPE.text));
//        chatMessages.add(new ChatMessage("生气的路透", "可以表扬什么呢？？？", ChatMessage.TYPE.person_in));
        chatMessages.add(new ChatMessage("颜色变化", "本文为博主原创文章，未经博主允许不得转载。", ChatMessage.TYPE.text));
        chatMessages.add(new ChatMessage("颜色变化", "本文为博主原创文章，未经博主允许不得转载。", ChatMessage.TYPE.text));
        messageMultipleRecycleAdapter.addLast(chatMessages);
        // 测试数据--------------

        mList = new ArrayList<>();
        mList.add(((BitmapDrawable) ResourcesCompat.getDrawable(getResources(), R.drawable.bg_common_action_bar, null)).getBitmap());
        mList.add(((BitmapDrawable) ResourcesCompat.getDrawable(getResources(), R.drawable.iv_funki, null)).getBitmap());
        mList.add(((BitmapDrawable) ResourcesCompat.getDrawable(getResources(), R.drawable.iv_me_facebook, null)).getBitmap());
        mList.add(((BitmapDrawable) ResourcesCompat.getDrawable(getResources(), R.drawable.iv_rank_first, null)).getBitmap());
        mList.add(((BitmapDrawable) ResourcesCompat.getDrawable(getResources(), R.drawable.iv_rank_first, null)).getBitmap());
        mList.add(((BitmapDrawable) ResourcesCompat.getDrawable(getResources(), R.drawable.iv_start_live_facebook, null)).getBitmap());


        divergeView3.setEndPoint(new PointF(divergeView3.getMeasuredWidth() / 2, 0));
        divergeView3.setDivergeViewProvider(new Provider());
    }

    @OnClick({R.id.host_avatar, R.id.tv_follow})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.host_avatar:
                if (mIndex == 5) {
                    mIndex = 0;
                }
                divergeView3.startDiverges(mIndex);
                mIndex++;
                break;
            case R.id.tv_follow:
                //创建PopWindow
                if (sharePopWindow == null) {
                    sharePopWindow = PopWindowUtil.getInstance(getContext());
                    shareView = LayoutInflater.from(getContext()).inflate(R.layout.pop_me_share, null);
                    sharePopWindow.getPopWindow().setOnDismissListener(onDissmissListener);
                }
                sharePopWindow.init((int) DisplayUtil.dip2Px(getContext(), 198), LinearLayout.LayoutParams.MATCH_PARENT);
                sharePopWindow.showPopWindow(shareView, PopWindowUtil.ATTACH_LOCATION_WINDOW, view, 0, 0);
            case R.id.iv_room_msg:

        }
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        danMuKuHelper = new DanMuKuHelper(getContext(), mDanmakuView);
        danMuKuHelper.onViewCreated(view, savedInstanceState);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        danMuKuHelper.onDestroyView();
    }

    @Override
    public void onResume() {
        super.onResume();
        danMuKuHelper.onResume();
        reResume = true;
        startPlay();

    }


    private void startPlay() {
        if (reResume && isVisual) {
            EventBus.getDefault().post(new EventPlayContent());

            CountDownTimer timer = new CountDownTimer(300000, 15000) {


                @Override
                public void onTick(long millisUntilFinished) {
                    //    System.out.println("----------------------------------xxxxxxxxxxxxxxxxxxxxxxxxxx");
                    ModelGift gift = new ModelGift();
                    danMuGroup.addData(gift);
                    gift = new ModelGift();
                    danMuGroup.addData(gift);

                    danMuKuHelper.addDanMu(new DanMuData());
                    danMuKuHelper.addDanMu(new DanMuData());
                    danMuKuHelper.addDanMu(new DanMuData());
                    danMuKuHelper.addDanMu(new DanMuData());
                    danMuKuHelper.addDanMu(new DanMuData());
                    danMuKuHelper.addDanMu(new DanMuData());
                    danMuKuHelper.addDanMu(new DanMuData());
                    danMuKuHelper.addDanMu(new DanMuData());
                    danMuKuHelper.addDanMu(new DanMuData());
                    danMuKuHelper.addDanMu(new DanMuData());
                    danMuKuHelper.addDanMu(new DanMuData());
                    danMuKuHelper.addDanMu(new DanMuData());
                    danMuKuHelper.addDanMu(new DanMuData());
                    danMuKuHelper.addDanMu(new DanMuData());
                    danMuKuHelper.addDanMu(new DanMuData());
                    danMuKuHelper.addDanMu(new DanMuData());
                    danMuKuHelper.addDanMu(new DanMuData());
                    danMuKuHelper.addDanMu(new DanMuData());
                    danMuKuHelper.addDanMu(new DanMuData());
                    danMuKuHelper.addDanMu(new DanMuData());
                    danMuKuHelper.addDanMu(new DanMuData());
                    danMuKuHelper.addDanMu(new DanMuData());
                }

                @Override
                public void onFinish() {

                }
            };
            timer.start();


        }
    }

    @Override
    public void onPause() {
        super.onPause();
        danMuKuHelper.onPause();
        reResume = false;

    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        isVisual = isVisibleToUser;
        startPlay();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }


    private class Provider implements DivergeView2.DivergeViewProvider {

        @Override
        public Bitmap getBitmap(Object obj) {
            return mList == null ? null : mList.get((int) obj);
        }
    }

    /**
     * popupWindow dimiss
     */
    PopupWindow.OnDismissListener onDissmissListener = new PopupWindow.OnDismissListener() {
        @Override
        public void onDismiss() {

        }
    };


}

