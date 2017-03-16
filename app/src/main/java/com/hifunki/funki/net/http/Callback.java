package com.hifunki.funki.net.http;



import retrofit2.Call;
import retrofit2.Response;


/**
 * Created by Powyin on 15/12/18.
 */
public abstract class Callback<T> implements retrofit2.Callback<T>{


    @Override
    public final void onResponse(Call<T> call, Response<T> response) {

//        String kkk=  call.toString();
//
//        System.out.println("====================================================");
//
//        System.out.println(kkk);
//
//        System.out.println("====================================================");

        T data =  response.body();
        if(data != null){
            success(data);
        }else {
            failure(ErrorType.ERROR_API, response.code());
        }

        if(response.code() == 402){
          //  UserClient.getDefault().userReLogin();
        }

    }




    @Override
    public final void onFailure(Call<T> call, Throwable t) {
        //TODO 统一处理
        failure(ErrorType.ERROR_NETCONNECT, -1);

    }


    public abstract void success(T data);

    public abstract void failure(ErrorType type, int httpCode);

    public void onProgress(int progress){
        // TODO  监听进度
    }

    public enum ErrorType{
        ERROR_NETCONNECT,
        ERROR_API,
        ERROR_FORMAT,
    }


}
