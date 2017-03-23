package com.hifunki.funki.module.home.viewholder;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.CountDownTimer;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.Surface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.VideoView;

import com.hifunki.funki.R;
import com.hifunki.funki.net.back.Post;
import com.hifunki.funki.util.TimeUtil;
import com.hifunki.funki.widget.FunKiPlayer;
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
 * @value com.hifunki.funki.module.home.viewholder.Follow_Movie.java
 * @link
 * @since 2017-03-16 10:04:04
 */
public class Follow_Movie extends PowViewHolder<Post> {


    @BindView(R.id.fun_player)
    FunKiPlayer fun_player;

    public Follow_Movie(Activity activity, ViewGroup viewGroup) {
        super(activity, viewGroup);

        ButterKnife.bind(this,mItemView);

    }


    @Override
    protected int getItemViewRes() {
        return R.layout.viewholder_post_movie;
    }

    @Override
    protected boolean acceptData(Post data) {
        return data != null && data.type == 3;
    }

    @Override
    public void loadData(AdapterDelegate<? super Post> multipleAdapter, Post data, int postion) {
        fun_player.play("http://59.37.108.69/vcloud.tc.qq.com/b0012f7x6qa.m301.mp4?sha=&vkey=EB9D4A342A9613B387392AE6191D152C93DB51F4715EAF823F614C1C82CB6560940B146929D8C322EA8281FFD17DE4A0F43EB074CB44B29F5D9C68F2C17B9823723F4A63B3BE79F9D57E931B8CCE6056E7C2E52AD2D455E00D2F98DCA39D66391BAE57B3FE37B1DC88D127C13D8A1065&ocid=1778445322");
    }


}

















