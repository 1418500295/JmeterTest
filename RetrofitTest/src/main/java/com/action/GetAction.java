package com.action;

import com.alibaba.fastjson.JSONObject;
import com.config.RetrofitConfig;
import com.interfaces.GetService;
import feign.Feign;
import feign.gson.GsonDecoder;
import okhttp3.ResponseBody;
import org.springframework.stereotype.Service;
import retrofit2.Call;

@Service
public class GetAction implements GetService {


    private GetService getService = RetrofitConfig.retrofitConfig().create(GetService.class);

    @Override
    public Call<ResponseBody> getCookies() {
        return getService.getCookies();
    }

    @Override
    public Call<ResponseBody> setCookies() {
        return getService.setCookies();
    }

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

    @Override
    public Call<ResponseBody> login(JSONObject jsonObject, JSONObject jsonObject1) {
        return getService.login(jsonObject,jsonObject1);
    }

}
