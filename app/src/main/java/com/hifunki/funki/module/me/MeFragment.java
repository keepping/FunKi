package com.hifunki.funki.module.me;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.hifunki.funki.R;
import com.hifunki.funki.base.fragment.BaseFragment;
import com.hifunki.funki.common.CommonConst;
import com.hifunki.funki.module.dynamic.me.activity.MyDynamicActivity;
import com.hifunki.funki.module.login.VisitorFillActivity;
import com.hifunki.funki.module.me.adapter.MeInfoAdapter;
import com.hifunki.funki.module.me.bill.activity.BillActivity;
import com.hifunki.funki.module.me.blacklist.activity.BlackListActivtiy;
import com.hifunki.funki.module.me.entity.MeBottomEntity;
import com.hifunki.funki.module.me.exchange.activity.ExchangeActivity;
import com.hifunki.funki.module.me.fans.activity.MyFansActivity;
import com.hifunki.funki.module.me.follow.activity.MyFollowActivity;
import com.hifunki.funki.module.me.live.activity.MyLiveActicity;
import com.hifunki.funki.module.me.profile.activity.EditProfileActivity;
import com.hifunki.funki.module.me.recharge.activity.RechargeActivity;
import com.hifunki.funki.module.me.settting.activity.SettingsActivity;
import com.hifunki.funki.module.me.user.UserAvatarActivity;
import com.hifunki.funki.module.me.visit.activity.VisitActivity;
import com.hifunki.funki.module.me.withdraw.activity.WithdrawActivity;
import com.hifunki.funki.module.photo.gallery.activity.PhotoActivity;
import com.hifunki.funki.module.photo.personal.activity.PersonalPhotoActivity;
import com.hifunki.funki.module.rank.me.activity.MeRankActivity;
import com.hifunki.funki.util.DisplayUtil;
import com.hifunki.funki.util.Number.NumIntAnim;
import com.hifunki.funki.util.PopWindowUtil;
import com.hifunki.funki.util.StatusBarUtil;
import com.hifunki.funki.util.ViewUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * 个人中心Fragment
 *
 * @author monotone
 * @version V1.0 <描述当前版本功能>
 * @value com.hifunki.funki.module.me.MeFragment.java
 * @link
 * @since 2017-03-08 10:06:06
 */
public class MeFragment extends BaseFragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    @BindView(R.id.civ_third_photo)
    CircleImageView civThirdPhoto;
    @BindView(R.id.civ_second_photo)
    CircleImageView civSecondPhoto;
    @BindView(R.id.civ_first_photo)
    CircleImageView civFirstPhoto;
    @BindView(R.id.civ_me_photo)
    CircleImageView civMePhoto;
    @BindView(R.id.iv_me_bill)
    ImageView ivMeBill;
    @BindView(R.id.iv_me_profile)
    ImageView ivMeProfile;
    @BindView(R.id.iv_me_share)
    ImageView ivMeShare;
    @BindView(R.id.iv_me_list)
    ImageView ivMeList;
    @BindView(R.id.iv_me_authentication)
    ImageView ivMeAuthentication;
    @BindView(R.id.ll_follow)
    LinearLayout llFollow;//关注
    @BindView(R.id.ll_fans)
    LinearLayout llFans;//粉丝
    @BindView(R.id.tv_recharge)
    TextView tvRecharge;
    @BindView(R.id.tv_exchange)
    TextView tvExchange;
    @BindView(R.id.tv_withdraw)
    TextView tvWithDraw;
    @BindView(R.id.tv_me_account_num)
    TextView tvAccountNum;

    private String mParam1;
    private String mParam2;

    @BindView(R.id.rv_me)
    RecyclerView rvMe;
    @BindView(R.id.nest)
    NestedScrollView nest;

    private PopWindowUtil sharePopWindow;//分享popWindow
    private View shareView;

    private OnFragmentInteractionListener mListener;
    private List<MeBottomEntity> mInfoTag;//个人中心信息标签
    private String TAG = getClass().getSimpleName();

    public MeFragment() {
    }

    public static MeFragment newInstance(String param1, String param2) {
        MeFragment fragment = new MeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_me;
    }

    @Override
    protected void initVariable() {
        super.initVariable();
        mInfoTag = new ArrayList<>();
        mInfoTag.add(new MeBottomEntity(getString(R.string.self_gallery), getResources().getDrawable(R.drawable.iv_me_gallery)));
        mInfoTag.add(new MeBottomEntity(getString(R.string.vistor_record), getResources().getDrawable(R.drawable.iv_me_visitlist)));
        mInfoTag.add(new MeBottomEntity(getString(R.string.my_field_control), getResources().getDrawable(R.drawable.iv_me_blacklist)));
        mInfoTag.add(new MeBottomEntity(getString(R.string.blacklist), getResources().getDrawable(R.drawable.iv_me_order)));
        mInfoTag.add(new MeBottomEntity(getString(R.string.order_management), getResources().getDrawable(R.drawable.iv_me_safty)));
        mInfoTag.add(new MeBottomEntity(getString(R.string.setting), getResources().getDrawable(R.drawable.iv_me_setting)));
        mInfoTag.add(new MeBottomEntity(getString(R.string.help_feedback), getResources().getDrawable(R.drawable.iv_me_help)));
        mInfoTag.add(new MeBottomEntity(getString(R.string.business_cooperate), getResources().getDrawable(R.drawable.iv_me_business)));

    }

    @Override
    protected void initView(View root) {
        super.initView(root);
        rvMe.setFocusable(false);
        StatusBarUtil.adjustStatusBarHei(root.findViewById(R.id.layout_me_head));
        MeInfoAdapter meInfoAdapter = new MeInfoAdapter(R.layout.item_me_info, mInfoTag);
        //    rvMe.setNestedScrollingEnabled(false);//防止滑动事件传递到RecycleView
        rvMe.setLayoutManager(new LinearLayoutManager(getContext()));
        rvMe.setAdapter(meInfoAdapter);
        ViewUtil.adjustScrollViewHei(rvMe);

        //圆形头像
        Glide.with(mContext).load(CommonConst.photo).into(civMePhoto);
        Glide.with(mContext).load(CommonConst.photo).into(civFirstPhoto);
        Glide.with(mContext).load(CommonConst.photo).into(civSecondPhoto);
        Glide.with(mContext).load(CommonConst.photo).into(civThirdPhoto);
    }

    @Override
    protected void initListener() {
        super.initListener();
        rvMe.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {

            }

            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                super.onItemChildClick(adapter, view, position);
                switch (view.getId()) {
                    case R.id.rl_item_info:
                        if (position == 0) {
                            PersonalPhotoActivity.show(getContext(), PersonalPhotoActivity.VALUE_ME_PHOTO_TO_GALLERY);
                        } else if (position == 1) {
                            VisitActivity.show(getContext());
                        } else if (position == 3) {
                            BlackListActivtiy.show(getContext());
                        } else if (position == 4) {
                            VisitorFillActivity.show(getContext());
                        } else if (position == 5) {
                            PhotoActivity.show(getContext());
                        } else if (position == 6) {
                            SettingsActivity.show(getContext());
                        }
                        break;
                }
            }
        });
    }

    @Override
    protected void bindData() {

    }

    @Override
    protected void bindData4NoNet() {

    }

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.e(TAG, "onResume: ");
    }


    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {
            NumIntAnim.startAnim(tvAccountNum, 5000,200);
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @OnClick({R.id.iv_me_bill, R.id.iv_me_profile, R.id.iv_me_share, R.id.iv_me_list, R.id.iv_me_authentication, R.id.ll_follow, R.id.ll_fans, R.id.rl_recharge, R.id.rl_exchange, R.id.rl_withdraw, R.id.rl_dymic, R.id.rl_live, R.id.civ_me_photo})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_me_bill:
                BillActivity.show(getContext());
                break;
            case R.id.iv_me_profile://编辑个人资料
                EditProfileActivity.show(getContext());
                break;
            case R.id.iv_me_share://下拉的分享popupWindow
                //创建PopWindow
                if (sharePopWindow == null) {
                    sharePopWindow = PopWindowUtil.getInstance(getContext());
                    shareView = LayoutInflater.from(getContext()).inflate(R.layout.pop_me_share, null);
                    sharePopWindow.getPopWindow().setOnDismissListener(onDissmissListener);
                }
                sharePopWindow.fitPopupWindowOverStatusBar(true);
                sharePopWindow.init((int) DisplayUtil.dip2Px(getContext(), 198), LinearLayout.LayoutParams.MATCH_PARENT);
                sharePopWindow.showPopWindow(shareView, PopWindowUtil.ATTACH_LOCATION_WINDOW, null, 1, 0);
                break;
            case R.id.civ_me_photo:
                System.out.println(rvMe.getHeight());
                break;
            case R.id.iv_me_authentication:
                UserAvatarActivity.show(getContext());
                break;
            case R.id.iv_me_list:
                MeRankActivity.show(getContext());
                break;
            case R.id.ll_follow://关注
                MyFollowActivity.show(getContext());
                break;
            case R.id.rl_dymic://我的动态
                MyDynamicActivity.show(getContext());
                break;
            case R.id.rl_live://我的直播
                MyLiveActicity.show(getContext());
                break;
            case R.id.ll_fans://粉丝
                MyFansActivity.show(getContext());
                break;
            case R.id.rl_recharge://充值
                RechargeActivity.show(getContext());
                break;
            case R.id.rl_exchange://兑换
                ExchangeActivity.show(getContext());
                break;
            case R.id.rl_withdraw://提现
                WithdrawActivity.show(getContext());
                break;
        }
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
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
