package com.hifunki.funki.module.live.audience.fragment;

import android.graphics.Bitmap;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.hifunki.funki.R;
import com.hifunki.funki.base.fragment.BaseFragment;
import com.hifunki.funki.client.User;
import com.hifunki.funki.module.live.audience.adapter.PrivateMsgAdapter;
import com.hifunki.funki.module.live.audience.event.EventPlayContent;
import com.hifunki.funki.module.live.audience.mode.ChatMessage;
import com.hifunki.funki.module.live.audience.viewholder.ChatComing;
import com.hifunki.funki.module.live.audience.viewholder.ChatFanHolder;
import com.hifunki.funki.module.live.audience.viewholder.ChatText;
import com.hifunki.funki.module.live.audience.viewholder.Gift;
import com.hifunki.funki.module.live.danmu.bDanMu.DanMuData;
import com.hifunki.funki.module.live.danmu.bDanMu.DanMuKuHelper;
import com.hifunki.funki.module.live.danmu.vDanMu.DanMuGroup;
import com.hifunki.funki.module.live.danmu.vDanMu.ModelGift;
import com.hifunki.funki.net.back.LiveModel;
import com.hifunki.funki.util.DisplayUtil;
import com.hifunki.funki.util.PopWindowUtil;
import com.hifunki.funki.util.keyBoard.KeyboardUtil;
import com.hifunki.funki.widget.bessel.DivergeView2;
import com.powyin.scroll.adapter.MultipleRecycleAdapter;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import eu.siacs.conversations.clent.ImManager;
import eu.siacs.conversations.clent.OnUpdateConversation;
import eu.siacs.conversations.entities.Conversation;
import eu.siacs.conversations.entities.Message;
import master.flame.danmaku.ui.widget.DanmakuView;

/**
 * 观众端fragment
 *
 * @author monotone
 * @version V1.0 <描述当前版本功能>
 * @value com.hifunki.funki.module.live.audience.fragment.AudienceFragment.java
 * @link
 * @since 2017-03-25 13:37:37
 */
public class AudienceFragment extends BaseFragment {

    @BindView(R.id.host_avatar)
    ImageView hostAvatar;
    @BindView(R.id.rl_fans_photo)
    RecyclerView rvFansPhoto;
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
    @BindView(R.id.live_edit_content)
    EditText editContent;
    @BindView(R.id.live_edit)
    View editGroup;
    @BindView(R.id.rl_info)
    View editSwitch;
    @BindView(R.id.empty)
    View empty;
    @BindView(R.id.fan_name)
    TextView fanName;
    @BindView(R.id.fan_num)
    TextView fanNum;
    @BindView(R.id.fan_location)
    LinearLayout fanLocation;
    @BindView(R.id.iv_room_exit)
    ImageView ivRoomExit;
    @BindView(R.id.room_view)
    FrameLayout roomView;
    @BindView(R.id.iv_room_private_msg)
    ImageView ivRoomPrivateMsg;

    MultipleRecycleAdapter<ChatMessage> messageMultipleRecycleAdapter;
    MultipleRecycleAdapter<User> avatarMultipleRecycleAdapter;
    MultipleRecycleAdapter<String> giftAdapter;

    private boolean reResume = false;      //用于控制当前显示视频
    private boolean isVisual = false;      //用于控制当前显示视频
    private LiveModel model;
    private ArrayList<Bitmap> mList;//弹幕bitmap
    private int mIndex = 0;
    private PopWindowUtil sharePopWindow;//分享popWindow
    private View shareView;
    private PopWindowUtil privateMsgPopWindow;//分享popWindow
    private View privateMsgView;
    DanMuKuHelper danMuKuHelper;

    ViewTreeObserver.OnGlobalLayoutListener layoutListener;
    public static AudienceFragment newInstance(LiveModel model) {
        Bundle args = new Bundle();
        AudienceFragment fragment = new AudienceFragment();
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
        avatarMultipleRecycleAdapter = MultipleRecycleAdapter.getByViewHolder(getActivity(), ChatFanHolder.class);
        rvFansPhoto.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        rvFansPhoto.setAdapter(avatarMultipleRecycleAdapter);
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

    @OnClick({R.id.host_avatar, R.id.tv_follow, R.id.iv_room_msg,R.id.iv_room_private_msg})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.host_avatar:
//                if (mIndex == 5) {
//                    mIndex = 0;
//                }
//                divergeView3.startDiverges(mIndex);
//                mIndex++;

                View view1 =  getActivity().getWindow().getDecorView().findViewById(android.R.id.content);

                Rect rect = new Rect();

                System.out.println("---------------------size "+mRoot.getHeight() + "   ");

                break;
            case R.id.tv_follow:
                if (sharePopWindow == null) {
                    sharePopWindow = PopWindowUtil.getInstance(getContext());
                    shareView = LayoutInflater.from(getContext()).inflate(R.layout.pop_me_share, null);
                    sharePopWindow.getPopWindow().setOnDismissListener(onDissmissListener);
                }
                sharePopWindow.init((int) DisplayUtil.dip2Px(getContext(), 198), LinearLayout.LayoutParams.MATCH_PARENT);
                sharePopWindow.showPopWindow(shareView, PopWindowUtil.ATTACH_LOCATION_WINDOW, view, 0, 0);

                break;
            case R.id.iv_room_msg:
                KeyboardUtil.showKeyboard(editContent);
                break;
            case R.id.iv_room_private_msg:
                if (sharePopWindow != null) {
                    sharePopWindow.hidePopWindow();
                }
                if (privateMsgPopWindow == null) {
                    privateMsgPopWindow = PopWindowUtil.getInstance(getContext());
                    privateMsgView = LayoutInflater.from(getContext()).inflate(R.layout.pop_audience_private_msg, null);
                    privateMsgPopWindow.getPopWindow().setOnDismissListener(onDissmissListener);
                }
                privateMsgPopWindow.init((int) DisplayUtil.dip2Px(getContext(), 198), LinearLayout.LayoutParams.MATCH_PARENT);
                privateMsgPopWindow.showPopWindow(privateMsgView, PopWindowUtil.ATTACH_LOCATION_WINDOW, view, 0, 0);
                RecyclerView rvPrivatemsg = (RecyclerView) privateMsgView.findViewById(R.id.rv_private_msg);
                TabLayout tbPrivateMsg = (TabLayout) privateMsgView.findViewById(R.id.tb_private_msg);
                tbPrivateMsg.removeAllTabs();
                tbPrivateMsg.addTab(tbPrivateMsg.newTab().setText("好友"));
                tbPrivateMsg.addTab(tbPrivateMsg.newTab().setText("未关注"));

                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
                rvPrivatemsg.setLayoutManager(layoutManager);
                List<String> list = new ArrayList<>();
                list.add("a");
                list.add("a");
                list.add("a");
                list.add("a");
                list.add("a");
                list.add("a");
                list.add("a");
                PrivateMsgAdapter privateMsgAdapter = new PrivateMsgAdapter(R.layout.item_audience_private_msg, list);
                rvPrivatemsg.setAdapter(privateMsgAdapter);
                break;
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        danMuKuHelper = new DanMuKuHelper(getContext(),mDanmakuView);
        danMuKuHelper.onViewCreated(view,savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
        danMuKuHelper.onResume();
        reResume = true;
        startPlay();
        layoutListener =  KeyboardUtil.attach(getActivity(), new KeyboardUtil.IPanelHeightTarget() {
            @Override
            public void refreshHeight(int panelHeight) {
                ViewGroup.LayoutParams layoutParams = empty.getLayoutParams();
                if (layoutParams.height != panelHeight) {
                    layoutParams.height = panelHeight;
                    empty.setLayoutParams(layoutParams);
                }
            }

            @Override
            public int getHeight() {
                ViewGroup.LayoutParams layoutParams = empty.getLayoutParams();
                return layoutParams.height;
            }

            @Override
            public void onKeyboardShowing(boolean showing) {
                if (!reResume || !isVisual) return;

                if (showing) {
                    editSwitch.setVisibility(View.GONE);
                    editGroup.setVisibility(View.VISIBLE);
                    danMuGroup.setVisibility(View.GONE);
                    mDanmakuView.setVisibility(View.GONE);
                    rlGift.setVisibility(View.GONE);
                } else {
                    editSwitch.setVisibility(View.VISIBLE);
                    editGroup.setVisibility(View.GONE);
                    danMuGroup.setVisibility(View.VISIBLE);
                    mDanmakuView.setVisibility(View.VISIBLE);
                    rlGift.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    String roomId = "fj@conference.192.168.100.163";

    private final OnUpdateConversation onUpdateConversation = new OnUpdateConversation() {
        @Override
        public void onFetch(Conversation conversation, Message... messages) {

            System.out.println("------>>>1 add action " + messages);

            System.out.println("null  " + mConversation.getJid());
            System.out.println("null  " + conversation.getJid());

            if(mConversation ==null || !(mConversation.getJid().toBareJid().equals(conversation.getJid().toBareJid()))) return;

            System.out.println("------>>>2 add action " + messages);

            if(messages==null || messages.length==0) return;

            System.out.println("------>>>3 add action " + messages);

            for(Message current : messages){
                DanMuData danMuData = new DanMuData();
                danMuData.message = current.getBody();
                System.out.println("------>>>4 add action " + messages);
         //       System.out.println("------>>> add action " + current.getBody());
                danMuKuHelper.addDanMu(danMuData);
            }
        }
    };

    Conversation mConversation;
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        danMuKuHelper.onDestroyView();
    }

    private void startPlay() {

        if (reResume && isVisual) {

            mConversation =  ImManager.getDefault().createConversation(roomId);

            System.out.println("null :::::::::::::::::::" + (mConversation==null));
            ImManager.getDefault().regester(onUpdateConversation);

            EventBus.getDefault().post(new EventPlayContent());
            CountDownTimer timer = new CountDownTimer(20000, 4000) {

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
//                    danMuKuHelper.addDanMu(new DanMuData());
//                    danMuKuHelper.addDanMu(new DanMuData());
//                    danMuKuHelper.addDanMu(new DanMuData());
//                    danMuKuHelper.addDanMu(new DanMuData());
//                    danMuKuHelper.addDanMu(new DanMuData());
//                    danMuKuHelper.addDanMu(new DanMuData());
//                    danMuKuHelper.addDanMu(new DanMuData());
                    danMuKuHelper.addDanMu(new DanMuData());
//                    danMuKuHelper.addDanMu(new DanMuData());
//                    danMuKuHelper.addDanMu(new DanMuData());
//                    danMuKuHelper.addDanMu(new DanMuData());
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
          //  timer.start();


        }else {
            ImManager.getDefault().unRegester(onUpdateConversation);
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        danMuKuHelper.onPause();
        reResume = false;

      //  System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------");

        KeyboardUtil.detach(getActivity(),layoutListener);
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        isVisual = isVisibleToUser;
        startPlay();
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

