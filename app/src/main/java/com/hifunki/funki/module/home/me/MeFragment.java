package com.hifunki.funki.module.home.me;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.hifunki.funki.R;
import com.hifunki.funki.base.fragment.BaseFragment;
import com.hifunki.funki.module.home.me.adapter.MeInfoAdapter;
import com.hifunki.funki.module.home.me.bill.activity.BillActivity;
import com.hifunki.funki.module.home.me.exchange.activity.ExchangeActivity;
import com.hifunki.funki.module.home.me.fans.activity.MyFansActivity;
import com.hifunki.funki.module.home.me.profile.activity.EditProfileActivity;
import com.hifunki.funki.module.home.me.recharge.activity.RechargeActivity;
import com.hifunki.funki.module.home.me.user.UserAvatarActivity;
import com.hifunki.funki.module.home.me.withdraw.activity.WithdrawActivity;
import com.hifunki.funki.module.rank.me.activity.MeRankActivity;
import com.hifunki.funki.util.DisplayUtil;
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
 * @value com.hifunki.funki.module.home.me.MeFragment.java
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

    private String mParam1;
    private String mParam2;


    @BindView(R.id.rv_me)
    RecyclerView rvMe;
    @BindView(R.id.nest)
    NestedScrollView nest;

    private PopWindowUtil sharePopWindow;//分享popWindow
    private View shareView;
    private String photo = "http://img0.imgtn.bdimg.com/it/u=2329110913,2614087554&fm=214&gp=0.jpg";

    private OnFragmentInteractionListener mListener;
    private List<String> mInfoTag;//个人中心信息标签

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
    protected void initView(View root) {
        super.initView(root);
        StatusBarUtil.adjustStatusBarHei(root.findViewById(R.id.layout_me_head));
        MeInfoAdapter meInfoAdapter = new MeInfoAdapter(R.layout.item_me_info, mInfoTag);
    //    rvMe.setNestedScrollingEnabled(false);//防止滑动事件传递到RecycleView
        rvMe.setLayoutManager(new LinearLayoutManager(getContext()));
        rvMe.setAdapter(meInfoAdapter);
        ViewUtil.adjustScrollViewHei(rvMe);


        //圆形头像
        Glide.with(mContext).load(photo).into(civMePhoto);
        Glide.with(mContext).load(photo).into(civFirstPhoto);
        Glide.with(mContext).load(photo).into(civSecondPhoto);
        Glide.with(mContext).load(photo).into(civThirdPhoto);


    }

    @Override
    protected void initData() {
        super.initData();
        mInfoTag = new ArrayList<>();
        mInfoTag.add(getString(R.string.self_gallery));
        mInfoTag.add(getString(R.string.vistor_record));
        mInfoTag.add(getString(R.string.my_field_control));
        mInfoTag.add(getString(R.string.blacklist));
        mInfoTag.add(getString(R.string.order_management));
        mInfoTag.add(getString(R.string.account_privacy_safety));
        mInfoTag.add(getString(R.string.setting));
        mInfoTag.add(getString(R.string.help_feedback));
        mInfoTag.add(getString(R.string.business_cooperate));

    }

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
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
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }



    @OnClick({R.id.iv_me_bill, R.id.iv_me_profile,R.id.iv_me_share, R.id.iv_me_list, R.id.iv_me_authentication, R.id.ll_follow, R.id.ll_fans,R.id.tv_recharge, R.id.civ_me_photo,R.id.tv_exchange,R.id.tv_withdraw})
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

                break;
            case R.id.ll_fans://粉丝
                MyFansActivity.show(getContext());
                break;
            case R.id.tv_recharge://充值
                RechargeActivity.show(getContext());
                break;
            case R.id.tv_exchange://兑换
                ExchangeActivity.show(getContext());
                break;
            case R.id.tv_withdraw://提现
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
