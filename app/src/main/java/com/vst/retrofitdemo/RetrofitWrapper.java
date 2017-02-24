package com.vst.retrofitdemo;

import android.content.Context;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 封装请求
 * 避免重复的创建Retrofit
 */
public class RetrofitWrapper  {
    private static RetrofitWrapper instance;
    private Context context;
    private Retrofit retrofit;
    private static final String BASE_URL="http://192.168.3.155/index.php/";

    private  RetrofitWrapper(){
        retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
    }

    public static RetrofitWrapper getInstance(){
        if (instance==null){
            synchronized (RetrofitWrapper.class){
                instance = new RetrofitWrapper();
            }
        }
        return instance;
    }

    public <T> T create(Class<T> service ){
        return retrofit.create(service);
    }

}
