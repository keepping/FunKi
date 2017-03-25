package com.hifunki.funki.module.home.me.profile.activity;

import android.content.Context;
import android.content.Intent;
import android.widget.ImageView;

import com.hifunki.funki.R;
import com.hifunki.funki.base.activity.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 编辑个人资料
 *
 * @author monotone
 * @version V1.0 <描述当前版本功能>
 * @value com.hifunki.funki.module.home.me.fans.EditProfileActivity.java
 * @link
 * @since 2017-03-23 12:01:01
 */
public class EditProfileActivity extends BaseActivity {

    @BindView(R.id.civ_profile_photo)
    ImageView civProfilePhoto;

    public static void show(Context context) {
        context.startActivity(new Intent(context, EditProfileActivity.class));
    }

    @Override
    protected int getViewResId() {
        return R.layout.activity_edit_profile;
    }

    @Override
    protected void initDatas() {
//        ProfileEntity entity=new ProfileEntity(ProfileEntity.getPhoto(), getResources().getString(R.string.photo), CommonConst.photo);
//        ProfileEntity entity1=new ProfileEntity(ProfileEntity.getMargintop(), getResources().getString(R.string.nickname), "鸡心罗饭");
//        ProfileEntity entity2=new ProfileEntity(ProfileEntity.getNormal(), getResources().getString(R.string.ID), "2073614");
//        ProfileEntity entity3=new ProfileEntity(ProfileEntity.getNormal(), getResources().getString(R.string.sex), "女");
//        ProfileEntity entity4=new ProfileEntity(ProfileEntity.getNormal(), getResources().getString(R.string.age), "22");
//        ProfileEntity entity5=new ProfileEntity(ProfileEntity.getNormal(), getResources().getString(R.string.singnature), "");
//        ProfileEntity entity6=new ProfileEntity(ProfileEntity.getNormal(), getResources().getString(R.string.language), "中文");
//        ProfileEntity entity7=new ProfileEntity(ProfileEntity.getMargintop(), getResources().getString(R.string.loveStatus), "心有所属");
//        ProfileEntity entity8=new ProfileEntity(ProfileEntity.getNormal(), getResources().getString(R.string.genderOrientation), "我爱异性");
//        ProfileEntity entity9=new ProfileEntity(ProfileEntity.getNormal(), getResources().getString(R.string.livePlace), "横须贺");
//        ProfileEntity entity10=new ProfileEntity(ProfileEntity.getNormal(), getResources().getString(R.string.liveStatus), "合租");
//        ProfileEntity entity11=new ProfileEntity(ProfileEntity.getMargintop(), getResources().getString(R.string.stature), "178cm");
//        ProfileEntity entity12=new ProfileEntity(ProfileEntity.getNormal(), getResources().getString(R.string.weight), "60kg");
//        ProfileEntity entity13=new ProfileEntity(ProfileEntity.getNormal(), getResources().getString(R.string.shapes), "苗条");
//        ProfileEntity entity14=new ProfileEntity(ProfileEntity.getNormal(), getResources().getString(R.string.dress), "休闲偏多");
//        ProfileEntity entity15=new ProfileEntity(ProfileEntity.getNormal(), getResources().getString(R.string.profileHairColor), "金色");
//        ProfileEntity entity16=new ProfileEntity(ProfileEntity.getNormal(), getResources().getString(R.string.profileEyeColor), "蓝色");
//        ProfileEntity entity17=new ProfileEntity(ProfileEntity.getNormal(), getResources().getString(R.string.profileMyType), "阳光宅男");
//        ProfileEntity entity18=new ProfileEntity(ProfileEntity.getNormal(), getResources().getString(R.string.profileLoveType), "哥特Coker");
//        ProfileEntity entity19=new ProfileEntity(ProfileEntity.getNormal(), getResources().getString(R.string.profileSmokeStatus), "偶尔抽烟");
//        ProfileEntity entity20=new ProfileEntity(ProfileEntity.getNormal(), getResources().getString(R.string.profileDrinkStatus), "偶尔交际");
//        ProfileEntity entity21=new ProfileEntity(ProfileEntity.getNormal(), getResources().getString(R.string.personality), "长腿欧巴，哈哈");
//        ProfileEntity entity22=new ProfileEntity(ProfileEntity.getNormal(), getResources().getString(R.string.profession), "设计师");
//        ProfileEntity entity23=new ProfileEntity(ProfileEntity.getNormal(), getResources().getString(R.string.profileReligionBelief), "天主教");


    }


    @Override
    protected void initView() {
//        pop_profile_edit_photo
    }

    @Override
    protected void initAdapter() {
        super.initAdapter();
//        ProfileAdapter profileAdapter = new ProfileAdapter();

    }


    @OnClick(R.id.civ_profile_photo)
    public void onClick() {
        //创建PopWindow
//        if (pwdPopWindow == null) {
//            pwdPopWindow = PopWindowUtil.getInstance(this);
//            pwdView = LayoutInflater.from(this).inflate(R.layout.pop_login_forget_pwd, null);
//            pwdPopWindow.getPopWindow().setOnDismissListener(onDissmissListener);
//        }
//        pwdPopWindow.init((int) DisplayUtil.dip2Px(this, 173), LinearLayout.LayoutParams.MATCH_PARENT);
//        pwdPopWindow.showPopWindow(pwdView, PopWindowUtil.ATTACH_LOCATION_WINDOW, null, 0, 0);
//        TextView tvPhonePwd = (TextView) pwdView.findViewById(R.id.tv_phone_pwd);
//        TextView tvEmailPwd = (TextView) pwdView.findViewById(R.id.tv_email_pwd);
//        ImageView iv_close = (ImageView) pwdView.findViewById(R.id.iv_close);
//        tvPhonePwd.setOnClickListener(this);
//        tvEmailPwd.setOnClickListener(this);
//        iv_close.setOnClickListener(this);
    }
}
