package com.tg;

import com.alibaba.fastjson.JSONObject;
import okhttp3.*;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.jetbrains.annotations.NotNull;
import org.testng.annotations.Test;

import java.io.IOException;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;

public class ApiTest {

    protected static int THREAD_NUM;  //总请求数
    protected CountDownLatch countDownLatch = new CountDownLatch(THREAD_NUM);
    protected CountDownLatch downLatch = new CountDownLatch(1);
    protected ExecutorService executorService = Executors.newCachedThreadPool();
    protected static String methodName;
    protected static int SUCCESS_NUM = 0;
    protected static final List<Long> respTimeList = Collections.synchronizedList(new ArrayList<>());
    protected static final int TIMEOUT = 3000;
    protected static Long sTime;
    protected static Long eTime;
    protected static OkHttpClient okHttpClient = new OkHttpClient.Builder()
            .connectTimeout(TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(TIMEOUT,TimeUnit.SECONDS)
            .writeTimeout(TIMEOUT,TimeUnit.SECONDS)
            .callTimeout(TIMEOUT,TimeUnit.SECONDS)
            .pingInterval(TIMEOUT,TimeUnit.SECONDS)
            .build();
    //保证多线程安全
    private static final ThreadLocal<DateFormat> dateFormatThreadLocal =
            ThreadLocal.withInitial(() -> new SimpleDateFormat("yyy-MM-dd HH:mm:ss"));

    public void doIt(String methodName) {
        sTime = CurrentTimeMillisClock.millisClock().now();
        System.out.println("******创建线程池******");
        for (int i = 0; i < THREAD_NUM; i++) {
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
        System.out.println("******线程池关闭******");
        eTime = CurrentTimeMillisClock.millisClock().now();
        System.out.println("开始时间: "+dateFormatThreadLocal.get().format(sTime));
        System.out.println("结束时间: "+dateFormatThreadLocal.get().format(eTime));
        System.out.println("总耗时: "+(float)(eTime -sTime)/1000+"秒");
        System.out.println("最大响应时间："+maxRespTime()+"秒");
        System.out.println("最小响应时间："+minRespTime()+"秒");
        System.out.println("50%用户响应时间："+fiftyRespTime()+"秒");
        System.out.println("90%用户响应时间："+ninetyRespTime()+"秒");
        System.out.println("平均响应时间："+avaRespTime()+"秒");
        System.out.println("总请求数："+THREAD_NUM);
        System.out.println("成功请求数："+SUCCESS_NUM);
        System.out.println("失败的请求数："+(THREAD_NUM -SUCCESS_NUM));
        System.out.println("QPS: "+QPS());

    }

    private  float minRespTime(){

        return (float)Collections.min(respTimeList)/1000;
    }

    private  float maxRespTime(){
       return (float)Collections.max(respTimeList)/1000;
    }

    private  float avaRespTime(){
        long sum = 0;
        synchronized (respTimeList){
            for (Long s : respTimeList) {
                sum += s;
            }
        }

        return (float) sum/respTimeList.size()/1000;
    }
    private  float fiftyRespTime(){
        Collections.sort(respTimeList);
        return (float)respTimeList.get((int) (respTimeList.size() * 0.5))/1000;
    }
    private  float ninetyRespTime() {
        Collections.sort(respTimeList);
        return (float)respTimeList.get((int) (respTimeList.size() * 0.9))/1000;
    }

    private  float QPS(){
        return THREAD_NUM/avaRespTime();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<String> list = Arrays.asList("get请求","post请求","json请求");
        System.out.println("目前可以压测的接口列表: "+list);
        System.out.println("请输入要执行的接口名：");
        methodName = scanner.next();
        System.out.println("请输入并发数量(只可以为正整数)：");
        THREAD_NUM = scanner.nextInt();
        new ApiTest().doIt(methodName);

    }

    @Test
    public void getDemo() {
        try {
            Request request = new Request.Builder()
                    .url("http://localhost:8889/v1/getDemo?name=lucaes&age=12")
                    .build();
            long sTimes = CurrentTimeMillisClock.millisClock().now();
            Response response = okHttpClient.newCall(request).execute();
            long eTimes = CurrentTimeMillisClock.millisClock().now();
            String result = Objects.requireNonNull(response.body()).string();
            System.out.println("响应："+result);
            if (result.contains("\"code\":\"1\"")){
                SUCCESS_NUM+=1;
            }
            respTimeList.add(eTimes - sTimes);
        } catch (Exception e) {
            e.printStackTrace();
        }

//        CloseableHttpClient client = null;
//        try {
////        设置超时时间
//
//            RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(TIMEOUT).setConnectTimeout(TIMEOUT)
//                    .setConnectionRequestTimeout(TIMEOUT)
//                    .build();
//            List<NameValuePair> list = new ArrayList<>();
//            client = HttpClients.createDefault();
//            list.add(new BasicNameValuePair("name","lucaes"));
//            list.add(new BasicNameValuePair("age","12"));
//            String params = EntityUtils.toString(new UrlEncodedFormEntity(list),"utf-8");
//            HttpGet get = new HttpGet("http://localhost:8889/v1/getDemo?"+params);
//            get.setConfig(requestConfig);
//            long sTimes = CurrentTimeMillisClock.millisClock().now();
//            HttpResponse response = client.execute(get);
//            long eTimes = CurrentTimeMillisClock.millisClock().now();
//            String result = EntityUtils.toString(response.getEntity(),"utf-8");
//            System.out.println("响应："+result);
//            respTimeList.add(eTimes -sTimes);
//            if (result.contains("\"code\":\"1\"")){
//                SUCCESS_NUM+=1;
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        finally {
//            try {
//                Objects.requireNonNull(client).close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//
//        }
    }

    @Test
    private void postJson() {
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("name","james");
            jsonObject.put("sex","male");
            RequestBody requestBody = RequestBody.Companion.create(jsonObject.toString()
                    ,MediaType.Companion.parse("application/json"));
            Request request = new Request.Builder()
                    .post(requestBody)
                    .url("http://localhost:8889/v1/postJson")
                    .build();
            long sTimes = CurrentTimeMillisClock.millisClock().now();
            Response response = okHttpClient.newCall(request).execute();
            long eTimes = CurrentTimeMillisClock.millisClock().now();
            String result = Objects.requireNonNull(response.body()).string();
            System.out.println("响应："+result);
            if (result.contains("\"code\":\"1\"")) {
                SUCCESS_NUM+=1;
            }
            respTimeList.add(eTimes - sTimes);

        } catch (IOException e) {
            e.printStackTrace();
        }

//        CloseableHttpClient client = null;
//        try {
//            client = HttpClients.createDefault();
//            JSONObject jsonObject = new JSONObject();
//            jsonObject.put("name","james");
//            jsonObject.put("sex","male");
//            HttpPost post = new HttpPost("http://localhost:8889/v1/postJson");
//            StringEntity entity = new StringEntity(jsonObject.toString(),"utf-8");
//            post.setEntity(entity);
//            post.setHeader("content-type","application/json");
//            long sTimes = System.currentTimeMillis();
//            HttpResponse response = client.execute(post);
//            String result = EntityUtils.toString(response.getEntity(),"utf-8");
//            System.out.println("响应："+result);
//            long eTimes = System.currentTimeMillis();
//            respTimeList.add(eTimes - sTimes);
//            if (result.contains("\"code\":\"1\"")){
//                okNum+=1;
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }finally {
//            try {
//                Objects.requireNonNull(client).close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
    }

    @Test
    private void postDemo() {

        try {
            FormBody formBody = new FormBody.Builder()
                    .addEncoded("name","make")
                    .addEncoded("age","16")
                    .build();
            Request request = new Request.Builder()
                    .post(formBody)
                    .url("http://localhost:8889/v1/postDemo")
                    .build();
            long sTimes = CurrentTimeMillisClock.millisClock().now();
            Response response = okHttpClient.newCall(request).execute();
            long eTimes = CurrentTimeMillisClock.millisClock().now();
            String result = Objects.requireNonNull(response.body()).string();
            System.out.println("响应："+result);
            if (result.contains("\"code\":\"1\"")) {
                SUCCESS_NUM+=1;
            }
            respTimeList.add(eTimes - sTimes);

        } catch (IOException e) {
            e.printStackTrace();
        }
//        CloseableHttpClient client = null;
//        try {
//            List<NameValuePair> list = new ArrayList<>();
//            client = HttpClients.createDefault();
//            HttpPost post = new HttpPost("http://localhost:8889/v1/postDemo");
//            list.add(new BasicNameValuePair("name","make"));
//            list.add(new BasicNameValuePair("age","16"));
//            post.setEntity(new UrlEncodedFormEntity(list));
//            long sTimes = System.currentTimeMillis();
//            HttpResponse response = client.execute(post);
//            String result = EntityUtils.toString(response.getEntity(),"utf-8");
//            System.out.println("响应："+result);
//            long eTimes = System.currentTimeMillis();
//            respTimeList.add(eTimes - sTimes);
//            if (result.contains("\"code\":\"1\"")){
//                okNum+=1;
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }finally {
//            try {
//                Objects.requireNonNull(client).close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
    }



}
