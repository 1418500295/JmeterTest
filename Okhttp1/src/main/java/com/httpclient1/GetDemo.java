package com.httpclient1;

import org.apache.http.HttpResponse;
import org.apache.http.client.CookieStore;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.DefaultHttpClient;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;

public class GetDemo {

    @Test
    public void getCookie() throws IOException {
        DefaultHttpClient client = new DefaultHttpClient();
        String url = "http://localhost:8889/getCookies";
        HttpGet get = new HttpGet(url);
        HttpResponse response = client.execute(get);
        CookieStore store = client.getCookieStore();
        List<Cookie> cookies = store.getCookies();
        for (Cookie cookie:cookies){
            System.out.println(cookie.getName()+"\n"+cookie.getValue());
        }

    }
}
