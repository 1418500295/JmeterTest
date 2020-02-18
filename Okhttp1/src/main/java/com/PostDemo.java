package com;

import com.alibaba.fastjson.JSONObject;
import okhttp3.*;
import okio.BufferedSink;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class PostDemo {
    private Logger logger = Logger.getLogger("PostDemo");

    /*
    参数为普通形势的post请求
     */
    @Test
    public void PostMethod() throws IOException {
        OkHttpClient client = new OkHttpClient();
        String url = "http://localhost:8889/postSecond";
        FormBody formBody = new FormBody.Builder()
                .add("name", "daine")
                .add("sex", "male")
                .build();
        Request request = new Request.Builder()
                .url(url)
                .post(formBody)
                .build();
        Response response = client.newCall(request).execute();
        String result = response.body().string();
        System.out.println(result);
    }

    /**
     * 带参数为json格式的post请求
     * @throws IOException
     */
    @Test
    public void getJSonPost() throws IOException {
        OkHttpClient client = new OkHttpClient();
        String url = "http://localhost:8889/postDemo";
        MediaType mediaType = MediaType.parse("application/json;charset=utf-8");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name","james");
        jsonObject.put("age","23");
        RequestBody body = RequestBody.create(mediaType, String.valueOf(jsonObject));
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        Response response = client.newCall(request).execute();
        String result = response.body().string();
        System.out.println(result);





    }
}
