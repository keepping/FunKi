package com.hifunki.funki.module.live.viewholder;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import com.bumptech.glide.Glide;
import com.hifunki.funki.R;
import com.hifunki.funki.client.User;
import com.hifunki.funki.util.DisplayUtil;
import com.hifunki.funki.util.PopWindowUtil;
import com.powyin.scroll.adapter.AdapterDelegate;
import com.powyin.scroll.adapter.PowViewHolder;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.hifunki.funki.base.application.ApplicationMain.getContext;

/**
 * 在此写用途
 *
 * @author yinhaoxiang
 * @version V1.0 <描述当前版本功能>
 * @value com.hifunki.funki.module.live.viewholder.ChatFan.java
 * @link
 * @since 2017-03-31 18:17:17
 */
public class ChatFan extends PowViewHolder<User> {

    private PopWindowUtil sharePopWindow;//分享popWindow
    private View shareView;

    @BindView(R.id.iv_audience)
    ImageView ivAudience;
    @BindView(R.id.line)
    View line;

    public ChatFan(Activity activity, ViewGroup viewGroup) {
        super(activity, viewGroup);
        ButterKnife.bind(this, mItemView);
    }

    @Override
    protected int getItemViewRes() {
        return R.layout.item_live_fan_item;
    }

    @Override
    public void loadData(AdapterDelegate<? super User> multipleAdapter, User data, int postion) {
        Glide.with(mActivity).load("http://img2.100bt.com/upload/ttq/20131103/1383470553132_middle.jpg").into(ivAudience);

    }

    @OnClick({R.id.iv_audience, R.id.line})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_audience://点击弹出一个popupWindow
                //创建PopWindow
                if (sharePopWindow == null) {
                    sharePopWindow = PopWindowUtil.getInstance(getContext());
                    shareView = LayoutInflater.from(getContext()).inflate(R.layout.pop_profile_edit_photo, null);
                    sharePopWindow.getPopWindow().setOnDismissListener(onDissmissListener);
                }
                sharePopWindow.init((int) DisplayUtil.dip2Px(getContext(), 198), LinearLayout.LayoutParams.MATCH_PARENT);
                sharePopWindow.showPopWindow(shareView, PopWindowUtil.ATTACH_LOCATION_WINDOW, view, 0, 0);
                break;
            case R.id.line:
                break;
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
