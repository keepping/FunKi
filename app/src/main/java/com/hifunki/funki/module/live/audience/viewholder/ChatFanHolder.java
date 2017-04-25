package com.hifunki.funki.module.live.audience.viewholder;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.hifunki.funki.R;
import com.hifunki.funki.client.User;
import com.hifunki.funki.common.CommonConst;
import com.hifunki.funki.module.room.activity.OtherRoomActivity;
import com.hifunki.funki.widget.dialog.FunKiDialog;
import com.powyin.scroll.adapter.AdapterDelegate;
import com.powyin.scroll.adapter.PowViewHolder;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 在此写用途
 *
 * @author yinhaoxiang
 * @version V1.0 <描述当前版本功能>
 * @value com.hifunki.funki.module.live.audience.viewholder.ChatFanHolder.java
 * @link
 * @since 2017-03-31 18:17:17
 */
public class ChatFanHolder extends PowViewHolder<User> implements View.OnClickListener {
    boolean isShare = false;
    @BindView(R.id.iv_audience)
    ImageView ivAudience;
    @BindView(R.id.line)
    View line;
    private FunKiDialog builder;
    public static String TAG = "ChatFanHolder";
    private LinearLayout llShare;

    public ChatFanHolder(Activity activity, ViewGroup viewGroup) {
        super(activity, viewGroup);
        ButterKnife.bind(this, mItemView);
    }

    @Override
    protected int getItemViewRes() {
        return R.layout.item_live_fan_item;
    }

    @Override
    public void loadData(AdapterDelegate<? super User> multipleAdapter, User data, int postion) {
        Glide.with(mActivity).load(CommonConst.photo).into(ivAudience);

    }

    @OnClick({R.id.iv_audience, R.id.line})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_audience:
                View rootView = LayoutInflater.from(mActivity).inflate(R.layout.dialog_audience_info, null);
                builder = FunKiDialog.getInstance(mActivity, rootView);
                builder.setViewHeight(279, 338);
                builder.show();
                RelativeLayout rlMain = (RelativeLayout) rootView.findViewById(R.id.rl_audience_main);
                llShare = (LinearLayout) rootView.findViewById(R.id.ll_audience_share);
                ImageView ivPhotoFriend = (ImageView) rootView.findViewById(R.id.civ_audience_photo_friend);
                ImageView ivPhotoMe = (ImageView) rootView.findViewById(R.id.civ_audience_photo_me);
                Glide.with(mActivity).load(CommonConst.photo).into(ivPhotoFriend);
                Glide.with(mActivity).load(CommonConst.photo).into(ivPhotoMe);
                TextView tvShare = (TextView) rootView.findViewById(R.id.tv_audience_share);
                TextView tvOtherRoom = (TextView) rootView.findViewById(R.id.tv_audience_otherroom);
                tvShare.setOnClickListener(this);
                tvOtherRoom.setOnClickListener(this);
                break;
            case R.id.line:
                break;
            case R.id.tv_audience_share:
                isShare = !isShare;
                if (isShare) {
                    builder.refreshDialogHeight(279, 367);
                    llShare.setVisibility(View.VISIBLE);
                } else {
                    builder.refreshDialogHeight(279, 338);
                    llShare.setVisibility(View.INVISIBLE);
                }
                break;
            case R.id.tv_audience_otherroom:
                OtherRoomActivity.show(mActivity);
                break;

        }
    }


}
