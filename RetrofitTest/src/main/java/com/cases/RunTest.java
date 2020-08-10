package com.cases;

import com.google.gson.internal.$Gson$Preconditions;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.testng.annotations.Test;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class RunTest implements Runnable {

    private int okNum = 0;

    @Test
    public String getDemo() {
        String result = null;
        try {
            CloseableHttpClient client = HttpClients.createDefault();
            List<NameValuePair> list = new ArrayList<>();
            list.add(new BasicNameValuePair("name", "daine"));
            list.add(new BasicNameValuePair("age", "26"));
            String params = EntityUtils.toString(new UrlEncodedFormEntity(list));
            HttpGet get = new HttpGet("http://localhost:8889/v1/getDemo" + "?" + params);
            HttpResponse response = client.execute(get);
            result = EntityUtils.toString(response.getEntity(), "utf-8");
            System.out.println(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;

    }

    public static void main(String[] args) {
//        ExecutorService executorService = Executors.newFixedThreadPool(3);
//        for (int i=0;i<10;i++){
//            executorService.execute(new Runnable() {
//                @Override
//                public void run() {
////                    new RunTest().getDemo();
//                    System.out.println(Thread.currentThread().getName()+"正在运行");
//                }
//            });
//        }
//        executorService.shutdown();
//        SimpleDateFormat dateFormat = new SimpleDateFormat("yy-MM-dd HH:mm:ss");
//        System.out.println(dateFormat.format(new Date()));
//        int i = 0;
//        while (i < 100){
//            new Thread(new RunTest()).start();
//            i++;
//        }
//        Calendar calendar = Calendar.getInstance();
//        System.out.println(dateFormat.format(calendar.getTime()));
//        System.out.println(new RunTest().okNum);
        System.out.println(new Date().getTime());
        System.out.println(System.currentTimeMillis());


    }

    @Override
    public void run() {

        this.getDemo();
        if (this.getDemo().contains("\"info\":\"success\"")){
            okNum += 1;

        }
        System.out.println(Thread.currentThread().getName()+"正在运行");

    }
}
