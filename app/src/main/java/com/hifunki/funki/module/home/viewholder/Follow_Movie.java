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
        fun_player.play("http://vliveachy.tc.qq.com/vcloud.tc.qq.com/n0011yeemua.m301.mp4?vkey=85E512C3DDCF24AF50663F9F9C64B698218C0F1025233FBED75CD1825E03B74AD0197596D5DC67EB04C5F5EF109F54E7E2D815CF0E28D2D7DC4BDC929B8BB811E6EA52C744C30EF5C5B874F363659BD18D58613C4D8447D50CE703CDC34C8CC19DF6EE057F4014CAE3C75F6A019E4D66&guid=714F50E7615D5EBAF3296F3A66B43579");


    }


}

















