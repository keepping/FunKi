package com.hifunki.funki.module.home.widget.banner;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.hifunki.funki.R;


public class GlideBannerImageLoader extends ImageLoader {
    @Override
    public void displayImage(Context context, Object path, ImageView imageView) {
        //具体方法内容自己去选择，次方法是为了减少banner过多的依赖第三方包，所以将这个权限开放给使用者去选择
        Glide.with(context.getApplicationContext())
                .load(path)
                .placeholder(R.mipmap.ic_launcher)//设置失败图
                .crossFade()
                .into(imageView);
    }


}
