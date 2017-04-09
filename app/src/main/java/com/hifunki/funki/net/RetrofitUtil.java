package com.hifunki.funki.net;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 在此写用途
 *
 * @author monotone
 * @version V1.0 <描述当前版本功能>
 * @value com.hifunki.funki.net.RetrofitUtil.java
 * @link
 * @since 2017-04-07 16:11:11
 */
public class RetrofitUtil {
    private static Retrofit retrofit;

    public static Retrofit getInstance() {
        return retrofit = new Retrofit.Builder().
                baseUrl(Host.WANG_HOST).
                addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
    }

}
