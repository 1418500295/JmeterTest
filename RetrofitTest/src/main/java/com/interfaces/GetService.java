package com.interfaces;

import com.alibaba.fastjson.JSONObject;
import com.google.gson.JsonObject;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.Map;

public interface GetService {

    @GET("/v1/getDemo")
    Call<ResponseBody> getInfo(@QueryMap JSONObject jsonObject);

    @POST("/postSecond")
    @FormUrlEncoded
    Call<ResponseBody> postInfo(@FieldMap JSONObject jsonObject);

    @POST("/postDemo")
    @Headers({"content-type:application/json"})
    Call<ResponseBody> postJson(@Body JSONObject jsonObject);
}
