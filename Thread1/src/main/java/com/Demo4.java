package com.tg;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.testng.annotations.Test;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ApiTest {

    protected static int num;
    protected CountDownLatch countDownLatch = new CountDownLatch(num);
    protected CountDownLatch downLatch = new CountDownLatch(1);
    protected int okNum = 0;
    protected static String methodName;
    protected SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    protected final List<Long> respTimeList = Collections.synchronizedList(new ArrayList<>());
    protected final int timeout = 3000;

    public void doIt(String methodName) {
        ExecutorService executorService  = Executors.newCachedThreadPool();
        long sTime = System.currentTimeMillis();
        System.out.println("******创建线程池******");
        for (int i = 0; i < num; i++) {
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        downLatch.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    switch (methodName) {
                        case "get请求":
                            getDemo();
                            break;
                        case "post请求":
                            postDemo();
                            break;
                        case "json请求":
                            postJson();
                            break;
                    }
                    countDownLatch.countDown();
                }

            });
        }
        downLatch.countDown();
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        executorService.shutdown();
        long eTime = System.currentTimeMillis();
        System.out.println("******线程池关闭******");
        System.out.println("开始时间: "+dateFormat.format(sTime));
        System.out.println("结束时间: "+dateFormat.format(eTime));
        System.out.println("总耗时: "+(float)(eTime -sTime)/1000+"秒");
        System.out.println("总请求数："+num);
        System.out.println("成功的请求数："+okNum);
        System.out.println("失败的请求数："+(num - okNum));
        System.out.println("最大响应时间："+maxRespTime()+"秒");
        System.out.println("最小响应时间："+minRespTime()+"秒");
        System.out.println("50%用户响应时间："+fiftyRespTime()+"秒");
        System.out.println("90%用户响应时间："+ninetyRespTime()+"秒");
        System.out.println("平均响应时间："+avaRespTime()+"秒");
        System.out.println("QPS: "+QPS());
    }

    private float minRespTime(){
        return (float)Collections.min(respTimeList)/1000;
    }

    private float maxRespTime(){
       return (float)Collections.max(respTimeList)/1000;
    }

    private float avaRespTime(){
        long sum = 0;
        synchronized (respTimeList){
            for (Long s : respTimeList) {
                sum += s;
            }
        }
        return (float) sum/respTimeList.size()/1000;
    }
    private float fiftyRespTime(){
        Collections.sort(respTimeList);
        return (float)respTimeList.get((int) (respTimeList.size() * 0.5))/1000;
    }
    private float ninetyRespTime() {
        Collections.sort(respTimeList);
        return (float)respTimeList.get((int) (respTimeList.size() * 0.9))/1000;
    }

    private float QPS(){
        return num/avaRespTime();
    }

    @Test
    public void getDemo(){
        CloseableHttpClient client = null;
        try {
            RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(timeout).setConnectTimeout(timeout)
                    .setConnectionRequestTimeout(timeout)
                    .build();
            List<NameValuePair> list = new ArrayList<>();
            client = HttpClients.createDefault();
            list.add(new BasicNameValuePair("name","lucaes"));
            list.add(new BasicNameValuePair("age","12"));
            String params = EntityUtils.toString(new UrlEncodedFormEntity(list),"utf-8");
            HttpGet get = new HttpGet("http://localhost:8889/v1/getDemo?"+params);
            get.setConfig(requestConfig);
            long sTimes = System.currentTimeMillis();
            HttpResponse response = client.execute(get);
            String result = EntityUtils.toString(response.getEntity(),"utf-8");
            System.out.println("响应："+result);
            long eTimes = System.currentTimeMillis();
            respTimeList.add(eTimes -sTimes);
            if (result.contains("\"code\":\"1\"")){
                okNum+=1;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            try {
                Objects.requireNonNull(client).close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    @Test
    private void postJson() {
        CloseableHttpClient client = null;
        try {
            client = HttpClients.createDefault();
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("name","james");
            jsonObject.put("sex","male");
            HttpPost post = new HttpPost("http://localhost:8889/v1/postJson");
            StringEntity entity = new StringEntity(jsonObject.toString(),"utf-8");
            post.setEntity(entity);
            post.setHeader("content-type","application/json");
            long sTimes = System.currentTimeMillis();
            HttpResponse response = client.execute(post);
            String result = EntityUtils.toString(response.getEntity(),"utf-8");
            System.out.println("响应："+result);
            long eTimes = System.currentTimeMillis();
            respTimeList.add(eTimes - sTimes);
            if (result.contains("\"code\":\"1\"")){
                okNum+=1;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                Objects.requireNonNull(client).close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    private void postDemo() {
        CloseableHttpClient client = null;
        try {
            List<NameValuePair> list = new ArrayList<>();
            client = HttpClients.createDefault();
            HttpPost post = new HttpPost("http://localhost:8889/v1/postDemo");
            list.add(new BasicNameValuePair("name","make"));
            list.add(new BasicNameValuePair("age","16"));
            post.setEntity(new UrlEncodedFormEntity(list));
            long sTimes = System.currentTimeMillis();
            HttpResponse response = client.execute(post);
            String result = EntityUtils.toString(response.getEntity(),"utf-8");
            System.out.println("响应："+result);
            long eTimes = System.currentTimeMillis();
            respTimeList.add(eTimes - sTimes);
            if (result.contains("\"code\":\"1\"")){
                okNum+=1;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                Objects.requireNonNull(client).close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<String> list = Arrays.asList("get请求","post请求","json请求");
        System.out.println("目前可以压测的接口列表: "+list);
        System.out.println("请输入要执行的接口名：");
        methodName = scanner.next();
        System.out.println("请输入并发数量(只可以为正整数)：");
        num = scanner.nextInt();
        new ApiTest().doIt(methodName);




}


}
