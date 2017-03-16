package com.hifunki.funki.net.http;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Looper;

import com.google.gson.Gson;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by powyin on 2016/10/21.
 */

public class HttpManager {

//    public  static final String SERVER_URL = "http://192.168.0.141:8088/";

    //public  static final String SERVER_URL = "http://61.130.10.82:3805/";



    private static JavaServerApi mJavaServerApi;
    private static OkHttpClient mClient;
    private static Handler mHandler;
    private static Gson gson = new Gson();

    public static JavaServerApi getServerApi() {
        return mJavaServerApi;
    }


    // 配置公共Client
    public static void init(final Context context) {

        mHandler = new Handler(Looper.myLooper());

        mClient = new OkHttpClient.Builder()
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {

                        Request request = chain.request();



                        String token = "xxx";

                        //    if(!TextUtils.isEmpty(token)){
                        Request.Builder builder = request.newBuilder();
                      //  builder.addHeader("token", UserClient.getDefault().getUserToken());

                        builder.addHeader("CLEARANCE", "I_AM_AR_MASTER");

                     //   builder.addHeader("token", UserClient.getDefault().getUserToken());


                        request = builder.build();
                        //    }

                        return chain.proceed(request);
                    }
                })

                .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .client(mClient)
                .addConverterFactory(GsonConverterFactory.create())
                //       .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(JavaServerApi.SERVER_URL)
                .build();
        mJavaServerApi = retrofit.create(JavaServerApi.class);

    }


    // 上传图片  // 使用multiple/format_data
    public static void uploadObject(String uri, Map<String, String> paras, Map<String, Object> bitmaps, Callback<?> callback) {
        final Callback temCallBack = callback;
        MultipartBody.Builder multipartBody = new MultipartBody.Builder();

        if (paras != null && paras.size() > 0) {
            for (Map.Entry<String, String> entry : paras.entrySet()) {
                multipartBody.addFormDataPart(entry.getKey(), entry.getValue());
            }
        }

        if (bitmaps != null && bitmaps.size() > 0) {
            for (Map.Entry<String, Object> entry : bitmaps.entrySet()) {
                Object object = entry.getValue();
                if (object instanceof Bitmap) {
                    RequestBody body = getByBitmap((Bitmap) object);
                    multipartBody.addFormDataPart("file", entry.getKey(), body);
                } else if (object instanceof File) {
                    multipartBody.addFormDataPart("file", entry.getKey(), RequestBody.create(MediaType.parse("image/png"), (File) object));
                } else {
                    throw new RuntimeException("upload data type not support : " + entry.getValue().getClass());
                }
            }
        }

        multipartBody.setType(MultipartBody.FORM);

        ProgressDelegate delegate = new ProgressDelegate(mHandler, multipartBody.build(), temCallBack);
        Request.Builder builder = new Request.Builder();
        Request request = builder.url(JavaServerApi.SERVER_URL + uri).post(delegate).build();

        mClient.newCall(request).enqueue(new okhttp3.Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        temCallBack.failure(Callback.ErrorType.ERROR_NETCONNECT, -1);
                    }
                });

            }

            @SuppressWarnings("unchecked")
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                try {
                    String json = response.body().string();

                    Type genericType;                                                                                              // class类(泛型修饰信息)
                    Class typeClass = temCallBack.getClass();                                                                       // class类
                    do {
                        genericType = typeClass.getGenericSuperclass();
                        typeClass = typeClass.getSuperclass();
                    } while (typeClass != Callback.class && typeClass != Object.class);

                    if (typeClass != Callback.class || genericType == Callback.class) {
                        throw new RuntimeException("参数类必须继承泛型ViewHolder");
                    }
                    ParameterizedType paramType = (ParameterizedType) genericType;
                    Type genericClass = paramType.getActualTypeArguments()[0];

                    final Object model = gson.fromJson(json, genericClass);
                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            temCallBack.success(model);
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            temCallBack.failure(Callback.ErrorType.ERROR_FORMAT, -1);
                        }
                    });
                }

            }
        });
    }


    private static RequestBody getByBitmap(Bitmap bitmap) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 60, outputStream);

        byte[] bytes = outputStream.toByteArray();

        try {
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        MediaType mediaType = MediaType.parse("image/png");
        return RequestBody.create(mediaType, bytes);
    }

}













































