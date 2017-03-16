package com.hifunki.funki.net.http;


import com.hifunki.funki.net.back.GJBack;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;


/**
 * Created by powyin 2016/10/10
 */
public interface JavaServerApi {

    public static final String SERVER_URL = "http://192.168.199.208:3807/";

    @Headers({
            "Content-Type: application/json",
            "CLEARANCE: I_AM_AR_MASTER"})

    @GET("/V1_0/rectd_tags/R")
    Call<GJBack> getTest();



    /**
     * 获取登录验证码接口
     * @param userphoneNum
     */
    @FormUrlEncoded
    @POST("/v1/login/validator")
    Call<GJBack> getLoginNum(

            @Field("USER_PHONE_NUM") String userphoneNum
    );

    /**
     * 登录接口  手机
     * @param userSource
     * @param inputCode
     * @param userPhoneNum
     */
    @FormUrlEncoded
    @POST("/v1/login/login")
    Call<GJBack> getLoginUsesId(
            @Field("USER_SOURCE") String userSource,
            @Field("inputCode") String inputCode,
            @Field("USER_PHONE_NUM") String userPhoneNum
    );




}
