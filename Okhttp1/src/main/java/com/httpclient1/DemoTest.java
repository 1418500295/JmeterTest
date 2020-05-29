package com.httpclient1;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.CookieStore;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.testng.annotations.Test;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.NavigableMap;

public class DemoTest {
    private CookieStore cookieStore;

    @Test
    public void getCookie() throws IOException {
        DefaultHttpClient client = new DefaultHttpClient();
        String url = "http://192.168.254.157:8889/getCookies";
        HttpGet get = new HttpGet(url);
        HttpResponse response = client.execute(get);
        CookieStore store = client.getCookieStore();
        List<Cookie> cookies = store.getCookies();
        for (Cookie cookie : cookies) {
            System.out.println(cookie.getName() + "\n" + cookie.getValue());
        }

    }

    @Test
    public void getCookies() throws IOException {
        DefaultHttpClient client = new DefaultHttpClient();
        String url = "http://localhost:8889/getCookies";
        HttpGet get = new HttpGet(url);
        HttpResponse response = client.execute(get);
        String result = EntityUtils.toString(response.getEntity(),"utf-8");
        System.out.println(result);
        cookieStore = client.getCookieStore();
        List<Cookie> cookies = cookieStore.getCookies();
        for (Cookie cookie:cookies){
            System.out.println(cookie.getName()+"\n"+cookie.getValue());
        }


    }

    @Test
    public void setCookie() throws IOException {
        DefaultHttpClient client = new DefaultHttpClient();
        String url = "http://localhost:8889/get/with/cookies";
        HttpGet get = new HttpGet(url);
//        CookieStore cookieStore = new BasicCookieStore();
//        BasicClientCookie cookie = new BasicClientCookie("login","true");
//        //设置域
//        cookie.setDomain("localhost");
//        cookie.setPath("/");
//        cookieStore.addCookie(cookie);
//        client.setCookieStore(cookieStore);
        get.setHeader("Cookie","login=true;base=localhost");
        HttpResponse response = client.execute(get);
        String result = EntityUtils.toString(response.getEntity(),"utf-8");
        System.out.println(result);

    }

    @Test
    public void jsonGet() throws IOException {
        DefaultHttpClient defaultHttpClient = new DefaultHttpClient();
        List<NameValuePair> list = new ArrayList<>();
        list.add(new BasicNameValuePair("weight","174"));
        list.add(new BasicNameValuePair("height","185"));
        String params = EntityUtils.toString(new UrlEncodedFormEntity(list),"utf-8");
        HttpGet get = new HttpGet("http://localhost:8889/v1/getFirst"+"?"+params);
        get.setHeader("content-type","application/json");
        HttpResponse response = defaultHttpClient.execute(get);
        String result = EntityUtils.toString(response.getEntity(),"utf-8");
        System.out.println(result);

    }

    public static void main(String[] args) throws IOException {
        DemoTest demoTest = new DemoTest();
        demoTest.getCookie();


    }
}
