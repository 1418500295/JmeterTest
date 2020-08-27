package com.rrtv.action;

import com.alibaba.fastjson.JSONObject;
import com.rrtv.config.RetrofitApiConfig;
import com.rrtv.interfaces.${serviceName};
import okhttp3.ResponseBody;
import org.springframework.stereotype.Service;
import retrofit2.Call;

@Service
public class ${actionName} implements ${serviceName} {

    private ${serviceName} service = RetrofitApiConfig.retrofitConfig()
            .create(${serviceName}.class);


    @Override
    public Call<ResponseBody> ${methodName}(JSONObject jsonObject,JSONObject jsonObject1) {
        return service.${methodName}(jsonObject,jsonObject1);
    }
}