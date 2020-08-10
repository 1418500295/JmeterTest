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
                     .baseUrl(RetrofitConfig.getHost())
                     .addConverterFactory(GsonConverterFactory.create())
                     .build();
             return retrofit;

        }

    public static void main(String[] args) {
        String s = RetrofitConfig.getHost();
        System.out.println(s+s.length()+s.getClass());
        String a = "http://localhost:8889";
        System.out.println(a+a.length()+a.getClass());
        System.out.println(s.equals(a));

    }



}
