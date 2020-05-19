package com;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.testng.annotations.Test;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
/*
http接口压测
 */
public class HttpTest implements Runnable {

    private static int num = 0;
    private static int ok = 0;

    public static void main(String[] args) {
        Runnable runnable = new HttpTest();
        Thread t = new Thread(runnable);
        t.start();
    }

    public void run() {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        System.out.println(df.format(new Date()));// new Date()为获取当前系统时间
        while (num < 7000) {
            try {
                this.request();
            } catch (IOException e) {
                e.printStackTrace();
            }
            num++;
            //打印成功次数
        }
        SimpleDateFormat e = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        System.out.println(e.format(new Date()));// new Date()为获取当前系统时间
        System.out.println(ok);

    }
    public void request() throws IOException {
        DefaultHttpClient client = new DefaultHttpClient();
        HttpGet get = new HttpGet("http://localhost:8889/v1/getDemo?name=daine&age=26");
        HttpResponse response = client.execute(get);
        String result = EntityUtils.toString(response.getEntity(),"utf-8");
        Gson gson = new Gson();
        JsonObject jsonObject = gson.fromJson(result,JsonObject.class);
        System.out.println(jsonObject);
        if (jsonObject.get("info").getAsString().equals("success")){
            ok+=1;
        }

    }
}