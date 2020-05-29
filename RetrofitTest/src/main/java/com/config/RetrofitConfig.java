package com.config;

import com.alibaba.fastjson.JSONObject;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.util.Locale;
import java.util.ResourceBundle;

public class RetrofitConfig {

    private static ResourceBundle resourceBundle = ResourceBundle
        .getBundle("hosts", Locale.CHINA);

    public static String getHost(){
        return  resourceBundle.getString("host");
    }

    public static Retrofit retrofitConfig() {
             Retrofit retrofit =  new Retrofit.Builder()
                    .baseUrl("http://localhost:8889")
//                     .baseUrl(RetrofitConfig.getHost())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
             return retrofit;

        }



}
