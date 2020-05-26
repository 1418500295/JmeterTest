package com.config;

import com.alibaba.fastjson.JSONObject;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitConfig {


    public static Retrofit retrofitConfig(){
        return new Retrofit.Builder()
                .baseUrl("http://localhost:8889")
//                .baseUrl(HostUtil.getHost())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

    }


}
