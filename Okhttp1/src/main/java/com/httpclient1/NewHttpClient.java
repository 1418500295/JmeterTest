package com.httpclient1;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.CookieStore;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpOptions;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.cookie.Cookie;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.testng.annotations.Test;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.*;

public class NewHttpClient {

    private Cookie cookie;

    @Test
    public void getDemo() throws IOException {

        CloseableHttpClient client = HttpClients.createDefault();
        List<NameValuePair> list = new ArrayList<>();
        list.add(new BasicNameValuePair("name","daine"));
        list.add(new BasicNameValuePair("age","26"));
        String param =EntityUtils.toString(new UrlEncodedFormEntity(list));
        HttpGet get = new HttpGet("http://localhost:8889/v1/getDemo"+"?"+param);
        HttpResponse response = client.execute(get);
        String rs = EntityUtils.toString(response.getEntity(),"utf-8");
        System.out.println(rs);


    }
    @Test
    public void postSecond() throws IOException {
        CloseableHttpClient client = HttpClients.createDefault();
        List<NameValuePair> list = new ArrayList<>();
        list.add(new BasicNameValuePair("name","daine"));
        list.add(new BasicNameValuePair("sex","male"));
        HttpPost post = new HttpPost("http://localhost:8889/postSecond");
        post.setEntity(new UrlEncodedFormEntity(list));
        HttpResponse response = client.execute(post);
        String rs = EntityUtils.toString(response.getEntity(),"utf-8");
        System.out.println(rs);
    }

    @Test
    public void postJson() throws IOException {
        CloseableHttpClient client = HttpClients.createDefault();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name","james");
        jsonObject.put("age","23");
        HttpPost post = new HttpPost("http://localhost:8889/postDemo");
        StringEntity entity = new StringEntity((jsonObject.toString()),"utf-8");
        post.setEntity(entity);
        post.setHeader("content-type","application/json");
        HttpResponse response = client.execute(post);
        System.out.println(EntityUtils.toString(response.getEntity(),"utf-8"));
    }
    @Test
    public void getCookies() throws IOException {
        CookieStore cookieStore = new BasicCookieStore();
        CloseableHttpClient client = HttpClients.custom().
                setDefaultCookieStore(cookieStore).build();
        HttpGet get = new HttpGet("http://localhost:8889/getCookies");
        HttpResponse response = client.execute(get);
        List<Cookie> cookies = cookieStore.getCookies();
        for (Cookie cookie : cookies){
            System.out.println(cookie.getName()+cookie.getValue());
        }

    }

    @Test(dependsOnMethods = {"getCookies"})
    public void setCookie() throws IOException {
        CloseableHttpClient client = HttpClients.createDefault();
        HttpGet get = new HttpGet("http://localhost:8889/get/with/cookies");
        get.setHeader("Cookie","login=true");
        HttpResponse response = client.execute(get);
        String rs = EntityUtils.toString(response.getEntity(),"utf-8");
        System.out.println(rs);



    }
}
