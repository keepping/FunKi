package com.hifunki.funki.module.live.danmu;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.OvershootInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.TextView;


import com.hifunki.funki.R;
import com.hifunki.funki.global.config.GiftType;

import java.util.Random;

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

    static Random random = new Random(1000);

    private TranslateAnimation inAnim;
    private TranslateAnimation inAnimSecond;
    private AnimatorSet repeatAnimator;
    private AnimationSet dropAnim;

    View mainView;

    @BindView(R.id.gif_type)
    ImageView gifType;

    @BindView(R.id.gif_user_avatar)
    ImageView giftUserAvatar;

    @BindView(R.id.gif_repeat)
    TextView gifUserRepeat;

    ModelGift mData;
    DanMuGroup mGroup;

    @Override
    public View getItemView(Context context, ViewGroup group){

        inAnim = (TranslateAnimation) AnimationUtils.loadAnimation(context, R.anim.gift_in);
        inAnimSecond = (TranslateAnimation) AnimationUtils.loadAnimation(context, R.anim.gift_type_in);
        dropAnim = (AnimationSet) AnimationUtils.loadAnimation(context, R.anim.gift_out);

        mainView = LayoutInflater.from(context).inflate(R.layout.item_gift_show,group, false);

        ButterKnife.bind(this,mainView);

        gifUserRepeat.setText("1");

        return mainView;
    }


    @Override
    public void onBindData(ModelGift data , DanMuGroup group){

        mData = data;
        mGroup = group;

        GiftType[] types = GiftType.values();
        int imageRes = types[random.nextInt(100)%types.length].imageRes;

        gifType.setImageResource(imageRes);
    }
    @Override
    public void dropIn(){

        gifType.setVisibility(View.INVISIBLE);

        inAnim.setAnimationListener(new Animation.AnimationListener() {
            boolean exit;
            @Override
            public void onAnimationStart(Animation animation) {
            }
            @Override
            public void onAnimationEnd(Animation animation) {
                if(exit) return;
                exit = true;
                System.out.println("2222222222222222222222222222222222222  1 "+mData);
                gifType.setVisibility(View.VISIBLE);
                gifType.startAnimation(inAnimSecond);
            }
            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        inAnimSecond.setAnimationListener(new Animation.AnimationListener() {
            boolean exit;
            @Override
            public void onAnimationStart(Animation animation) {
            }
            @Override
            public void onAnimationEnd(Animation animation) {
                if(exit) return;
                exit = true;
                System.out.println("2222222222222222222222222222222222222  2 "+mData);
                startAnimationRepeat();

            }
            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });

        mainView.startAnimation(inAnim);
    }


    private int repeatCount =0 ;

    private void startAnimationRepeat(){
        repeatCount++;

        ObjectAnimator anim1 = ObjectAnimator.ofFloat(gifUserRepeat, "scaleX",
                1.0f, 1.5f);
        ObjectAnimator anim2 = ObjectAnimator.ofFloat(gifUserRepeat, "scaleY",
                1.0f, 1.5f);
        ObjectAnimator anim3 = ObjectAnimator.ofFloat(gifUserRepeat, "scaleX",
                1.5f, 1.0f);
        ObjectAnimator anim4 = ObjectAnimator.ofFloat(gifUserRepeat, "scaleY",
                1.5f, 1.0f);

        final AnimatorSet animSet = new AnimatorSet();
        repeatAnimator = animSet;
        animSet.setDuration(700);
        animSet.setInterpolator(new OvershootInterpolator());

        animSet.play(anim1).with(anim2);
        animSet.play(anim2).before(anim3);
        animSet.play(anim3).with(anim4);

        animSet.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {
            }
            @Override
            public void onAnimationEnd(Animator animator) {                       // 次方法可能多次执行 repeatAnimator==animSet 能避免这种情况

                if(repeatAnimator==animSet){
                    if(mData.count> repeatCount){
                        startAnimationRepeat();
                        gifUserRepeat.setText(String.valueOf(repeatCount));
                    }else {
                        startAnimationDropOut();
                        System.out.println("2222222222222222222222222222222222222  3 "+mData);
                        repeatAnimator = null;
                    }
                }

            }
            @Override
            public void onAnimationCancel(Animator animator) {

            }
            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });

        animSet.start();
    }


    private void startAnimationDropOut(){
        System.out.println("2222222222222222222222222222222222222  4-1 "+mData);
        dropAnim.setAnimationListener(new Animation.AnimationListener() {
            boolean exit = false;
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                if(exit){
                    return;
                }
                exit = true;
                System.out.println("2222222222222222222222222222222222222  4 "+mData);
                mGroup.removeRunning(mData);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        mainView.startAnimation(dropAnim);
    }


}
