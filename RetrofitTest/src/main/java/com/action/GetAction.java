package com.action;

import com.alibaba.fastjson.JSONObject;
import com.config.RetrofitConfig;
import com.interfaces.GetService;
import okhttp3.ResponseBody;
import org.springframework.stereotype.Component;
import retrofit2.Call;

public class GetAction implements GetService {


    private GetService getService = RetrofitConfig.retrofitConfig().create(GetService.class);


    @Override
    public Call<ResponseBody> getInfo(JSONObject jsonObject) {
        return getService.getInfo(jsonObject);
    }

    @Override
    public Call<ResponseBody> postInfo(JSONObject jsonObject) {
        return getService.postInfo(jsonObject);
    }

    @Override
    public Call<ResponseBody> postJson(JSONObject jsonObject) {
        return getService.postJson(jsonObject);
    }

}
