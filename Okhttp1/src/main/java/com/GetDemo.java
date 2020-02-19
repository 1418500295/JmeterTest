package com;

import okhttp3.*;
import org.apache.commons.logging.Log;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.Callable;

public class GetDemo {
    private String result = null;

    /*
    同步请求
     */
    @Test
    public void getMethod() throws IOException {
        OkHttpClient okHttpClient = new OkHttpClient();
        String url = "http://localhost:8889/v1/getDemo?name=daine&age=26";
        Request request = new Request.Builder()
                .url(url)
                .get()
                .build();
        Response response = okHttpClient.newCall(request).execute();
        String result = response.body().string();
        System.out.println(result);
    }

    @Test
    public void setCookie() throws IOException {
        OkHttpClient okHttpClient = new OkHttpClient();
        String url = "http://localhost:8889/get/with/cookies";
        Request request = new Request.Builder()
                .url(url)
                .addHeader("Cookie","login=true")
                .get()
                .build();

        Response response = okHttpClient.newCall(request).execute();
        String result = response.body().string();
        System.out.println(result);
    }



    @Test
    public void getCookie() throws IOException {
        OkHttpClient client = new OkHttpClient();
        String url = "http://localhost:8889/getCookies";
        Request request = new Request.Builder()
                .url(url)
                .build();
        Response response = client.newCall(request).execute();
//        获取cookie
//        Headers headers = response.headers();
//        List<String> cookies = headers.values("Set-Cookie");
//        String cookie = cookies.get(0);
//        String myCookie = cookie.substring(0,cookie.indexOf(";"));
//        System.out.println(myCookie);

        Headers headers = response.headers();
        HttpUrl httpUrl =  request.url();
        List<Cookie> cookies = Cookie.parseAll(httpUrl,headers);
        for (Cookie cookie:cookies){
            System.out.println(cookie.name()+"\n"+cookie.value());
        }
    }
    /*
    异步请求
     */

    @Test
    public String getEnqueue() throws IOException {

        OkHttpClient client = new OkHttpClient();
        String url = "http://localhost:8889/v1/getDemo?name=daine&age=26";
        Request request = new Request.Builder()
                .url(url)
                .get()
                .build();
        Call call = client.newCall(request);
//        Response response = call.execute();
//        System.out.println(response.body().string());

        call.enqueue(new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {
                System.out.println("出错啦");

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                result = response.body().string();
                System.out.println("1111");

            }
        });

        return result;






    }




}
