package com.httpclient1;


import org.apache.http.HttpResponse;
import org.apache.http.client.CookieStore;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.cookie.Cookie;

import org.apache.http.impl.client.DefaultHttpClient;

import org.apache.http.util.EntityUtils;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;


public class GetDemo {

    private CookieStore cookieStore;

    @Test
    public void getCookie() throws IOException {
        DefaultHttpClient client = new DefaultHttpClient();
        String url = "http://localhost:8889/getCookies";
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
        CookieStore cookieStore = client.getCookieStore();
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
}
