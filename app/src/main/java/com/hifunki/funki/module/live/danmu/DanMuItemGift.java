package com.hifunki.funki.module.live.danmu;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.TextView;


import com.hifunki.funki.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 在此写用途
 *
 * @author yinhaoxiang
 * @version V1.0 <描述当前版本功能>
 * @value com.hifunki.funki.module.live.danmu.DanMuItemGift.java
 * @link
 * @since 2017-04-05 15:52:52
 */
public class DanMuItemGift implements IDanMuDelegate {

    private TranslateAnimation inAnim;
    private TranslateAnimation inAnimSecond;
    private TranslateAnimation outAnim;

    View mainView;

    @BindView(R.id.gif_type)
    ImageView gifType;

    @BindView(R.id.gif_user_avatar)
    ImageView giftUserAvatar;

    @BindView(R.id.gif_repeat)
    TextView gifUserRepeat;

    public View getItemView(Context context, ViewGroup group){

        inAnim = (TranslateAnimation) AnimationUtils.loadAnimation(context, R.anim.gift_in);
        inAnimSecond = (TranslateAnimation) AnimationUtils.loadAnimation(context, R.anim.gift_type_in);
        outAnim = (TranslateAnimation) AnimationUtils.loadAnimation(context, R.anim.gift_out);

        mainView = LayoutInflater.from(context).inflate(R.layout.item_gift_show,group, false);

        ButterKnife.bind(this,mainView);

        return mainView;
    }

    public void onBindData(ModelGift data){


    }

    public void dropIn(){
        int wid = mainView.getWidth();
        int hei = mainView.getHeight();

        mainView.startAnimation(inAnim);
        inAnim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                gifType.startAnimation(inAnimSecond);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });


    }


    public void dropOut(){


    }



}
