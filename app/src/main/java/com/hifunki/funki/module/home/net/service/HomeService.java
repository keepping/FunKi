package com.hifunki.funki.module.home.net.service;

import com.hifunki.funki.net.Urls;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * 在此写用途
 *
 * @author monotone
 * @version V1.0 <描述当前版本功能>
 * @value com.hifunki.funki.module.home.net.service.HomeService.java
 * @link
 * @since 2017-04-07 16:07:07
 */
public interface HomeService {

    @GET(Urls.HOME_NEW)
    Call<ResponseBody> getHomeNewJson();


}
