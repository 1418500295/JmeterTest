package com.rrtv.interfaces;

import com.alibaba.fastjson.JSONObject;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.HeaderMap;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

public interface ${serviceName} {

    @GET("/demo")
    Call<ResponseBody> ${methodName}(@QueryMap JSONObject jsonObject,
                             @HeaderMap JSONObject jsonObject1);
}