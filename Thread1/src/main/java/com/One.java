package com;

import java.io.File;

public class One {
    package com.ok.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Splitter;
import com.ok.common.CurrentTimeMillisClock;
import com.ok.common.PrintTable;
import com.sun.istack.internal.NotNull;
import lombok.NonNull;
import okhttp3.ConnectionPool;
import okhttp3.OkHttpClient;
import org.apache.http.HeaderElement;
import org.apache.http.HeaderElementIterator;
import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ConnectionKeepAliveStrategy;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicHeaderElementIterator;
import org.apache.http.protocol.HTTP;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;

import javax.naming.NamingEnumeration;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.LongAdder;

public class ApiTest {

    protected static int THREAD_NUM;  //总线程数
    protected CountDownLatch countDownLatch = new CountDownLatch(THREAD_NUM);
    protected  CyclicBarrier cyclicBarrier = new CyclicBarrier(THREAD_NUM);
    protected CountDownLatch downLatch = new CountDownLatch(1);
    protected ExecutorService executorService = Executors.newCachedThreadPool();
    protected static String methodName;
    protected static LongAdder SUCCESS_NUM = new LongAdder();
    protected static LongAdder FAIL_NUM = new LongAdder();
    protected static final List<Long> respTimeList = Collections.synchronizedList(new ArrayList<>());
//    protected static final List<Long> respTimeList = new ArrayList<>();
    protected static final int TIMEOUT = 3000;
    protected static Long sTime;
    protected static Long eTime;
    protected static final int KEEP_ALIVE_DURATION = 5;

    protected static int execTime;
    protected static final OkHttpClient okHttpClient = new OkHttpClient.Builder()
            .connectionPool(new ConnectionPool(THREAD_NUM,KEEP_ALIVE_DURATION,TimeUnit.MINUTES))
            .connectTimeout(TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(TIMEOUT,TimeUnit.SECONDS)
            .writeTimeout(TIMEOUT,TimeUnit.SECONDS)
//            .callTimeout(TIMEOUT,TimeUnit.SECONDS)
            .pingInterval(TIMEOUT,TimeUnit.SECONDS)
            .build();
    protected RequestConfig requestConfig = RequestConfig.custom()
            .setConnectTimeout(30000)
            .setConnectionRequestTimeout(30000)
            .setSocketTimeout(30000)
            .build();
    private static final ThreadLocal<DateFormat> dateFormatThreadLocal =
            ThreadLocal.withInitial(() -> new SimpleDateFormat("yyy-MM-dd HH:mm:ss"));

    public void executeInterfaceList(String methodName) throws IOException {
        switch (methodName){
            case "开工列表":
                startWork(execTime);
                break;
            case "开工记录":
                break;
            default:
                System.out.println("接口不存在");
        }
    }
    protected static float useTime;
    protected static long winNum;
    protected static long failNum;
    protected static float avgResTime;
    protected static float qps;
    protected static float fiftyResTime;
    protected static float nintyResTime;
    protected static float maxResTime;
    protected static float minResTime;

    public void doIt(String methodName) {
        sTime = CurrentTimeMillisClock.millisClock().now();
//        System.out.println("******创建线程池******");
        for (int i = 0; i < THREAD_NUM; i++) {
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        downLatch.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    try {
                        executeInterfaceList(methodName);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    try {
                        cyclicBarrier.await();
                    } catch (InterruptedException | BrokenBarrierException e) {
                        throw new RuntimeException(e);
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
        eTime = CurrentTimeMillisClock.millisClock().now();
//        System.out.println("******线程池关闭******");
        useTime = (float)(eTime -sTime)/1000;
        winNum = SUCCESS_NUM.sum();
        failNum = FAIL_NUM.sum();
        maxResTime = maxRespTime();
        minResTime = minRespTime();
        fiftyResTime = fiftyRespTime();
        nintyResTime = ninetyRespTime();
        avgResTime = avaRespTime();
        qps = qps();
        System.out.println("压测结果：");
        printInfo();
//        System.out.println("开始时间: "+dateFormatThreadLocal.get().format(sTime));
//        System.out.println("结束时间: "+dateFormatThreadLocal.get().format(eTime));
//        System.out.println("总耗时: "+(float)(eTime -sTime)/1000+"秒");
//        System.out.println("最大响应时间："+maxRespTime()+"秒");
//        System.out.println("最小响应时间："+minRespTime()+"秒");
//        System.out.println("50%用户响应时间："+fiftyRespTime()+"秒");
//        System.out.println("90%用户响应时间："+ninetyRespTime()+"秒");
//        System.out.printf("平均响应时间：%.3f秒\n",avaRespTime());
//        System.out.println("总并发线程数："+THREAD_NUM);
//        System.out.printf("总请求数：%d\n",SUCCESS_NUM.sum()+FAIL_NUM.sum());
//        System.out.println("成功的请求数："+SUCCESS_NUM.sum());
//        System.out.println("失败的请求数："+(FAIL_NUM.sum()));
//        System.out.printf("qps: %.3f\n",qps());

    }
    public static void printTable(PrintTable printTable1) {
        printTable1.addHeader("总耗时"
                , "并发线程数"
                , "总请求数"
                ,"成功请求数"
                , "失败请求数"
                ,"平均响应时间"
                ,"50%响应时间"
                ,"90%响应时间"
                ,"失败率"
                ,"qps");
        printTable1.addBody(String.valueOf(String.format("%.1f",useTime))
                ,String.valueOf(THREAD_NUM)
                ,String.valueOf(winNum+failNum)
                ,String.valueOf(winNum)
                ,String.valueOf(failNum)
                ,String.valueOf(String.format("%.3f",avgResTime))
                ,String.valueOf(String.format("%.3f",fiftyResTime))
                ,String.valueOf(String.format("%.3f",nintyResTime))
                ,String.valueOf(String.format("%.2f",(float)failNum/(winNum+failNum)*100)+"%")
                ,String.valueOf(String.format("%.1f",qps)));

    }

    public static void printInfo() {
        PrintTable printTable1 = PrintTable.create();
        PrintTable printTable2 = PrintTable.create();

        printTable1.setSbcMode(true);
        printTable(printTable1);

//        System.out.println("====全角模式效果=====");
        printTable1.print();

//        printTable2.setSbcMode(false);
//        printTable(printTable2);
//
//        System.out.println("====半角模式效果=====");
//        printTable2.print();
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

    private  float qps(){
        return (THREAD_NUM)/avaRespTime();
    }

    public static boolean isNumber(String input){
        String reg = "^-?[0-9]+(.[0-9]+)?$";
        return input.matches(reg);

    }
    public static boolean isInteger(String input){
        return input.matches("[0-9]+$");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<String> list = Collections.singletonList("开工列表");
        System.out.println("目前可以压测的接口列表: "+list);
        System.out.println("请输入要执行的接口名：");
        methodName = scanner.next();
        while (true) {
            if (!(list.contains(methodName))) {
                System.out.println("请输入正确的接口");
                methodName = scanner.next();
                if (list.contains(methodName)) {
                    break;
                }
            } else {
                break;
            }
        }
        System.out.println("请输入并发数量(只可以为正整数)：");
        String input = scanner.next();
        while (true){
            if (!(isNumber(input) && Integer.parseInt(input)>0) && isInteger(input)){
                System.out.println("并发数必须是正整数");
                input = scanner.next();
                if ((isNumber(input) && Integer.parseInt(input)>0 && isInteger(input))){
                    THREAD_NUM = Integer.parseInt(input);
                    break;
                }
            }else {
                THREAD_NUM = Integer.parseInt(input);
                break;
            }
        }
        System.out.println("请输入执行时长：");
        execTime = scanner.nextInt();
        new ApiTest().doIt(methodName);

    }
    private static final String url = "https:s/start/work/list";
    private static final String token = "eyJhbGciOivkWDD7s";

    private long s;
    private long e;
    
    public CloseableHttpClient client = HttpClients
            .custom()
            .setKeepAliveStrategy(getMyStrategy())
            .setConnectionManager(getConnectionManager())
            .build();
    public ConnectionKeepAliveStrategy getMyStrategy(){
        ConnectionKeepAliveStrategy myStrategy = new ConnectionKeepAliveStrategy() {
            @Override
            public long getKeepAliveDuration(HttpResponse response, HttpContext context) {
                HeaderElementIterator it = new BasicHeaderElementIterator
                        (response.headerIterator(HTTP.CONN_KEEP_ALIVE));
                while (it.hasNext()) {
                    HeaderElement he = it.nextElement();
                    String param = he.getName();
                    String value = he.getValue();
                    if (value != null && param.equalsIgnoreCase
                            ("timeout")) {
                        return Long.parseLong(value) * 1000;
                    }
                }
                return 60 * 1000;//如果没有约定，则默认定义时长为60s
            }
        };
        return myStrategy;
    }
    public static PoolingHttpClientConnectionManager getConnectionManager(){
        PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager();
        connectionManager.setMaxTotal(1000);
        connectionManager.setDefaultMaxPerRoute(500);
        return connectionManager;
    }

    public JSONObject doJsonPost(@NotNull String url, @NotNull JSONObject params){
        String result = null;
        try {
            HttpPost post = new HttpPost(url);
            post.setConfig(requestConfig);
            StringEntity entity = new StringEntity(params.toString(),"utf-8");
            post.setEntity(entity);
            post.setHeader("content-type","application/json");
            post.setHeader("t",token);
            HttpResponse response = null;
            try {
                s = CurrentTimeMillisClock.millisClock().now();
                response = client.execute(post);
                e = CurrentTimeMillisClock.millisClock().now();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            respTimeList.add(e-s);
            try {
                result = EntityUtils.toString(response.getEntity(),"utf-8");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
        return JSON.parseObject(result);

    }
    long startT;
    long endT;
    protected static JSONObject params = new JSONObject();
    static {
        params.put("1",1);
        
    }
    public void startWork(int times) {
        startT = CurrentTimeMillisClock.millisClock().now();
        do{
            JSONObject res = doJsonPost(url,params);
            if (res.getIntValue("code") ==  && res.getString("msg").equals("")) {
                SUCCESS_NUM.add(1);
            }else {
                FAIL_NUM.add(1);
            }
            endT = CurrentTimeMillisClock.millisClock().now();
        }while ((endT - sTime) <= times * 1000L);
    }

























}

//     public static void main(String[] args) {
//         String path = "C:\\Users\\ASUS\\JmeterTest\\Thread1\\src\\main\\java\\com\\a.txt";
//         File file  = new File(path);
//         //判断文件是否存在
//         System.out.println(file.exists());
//         //获取文件绝对路径
//         System.out.println(file.getAbsolutePath());
//         System.out.println(file.getPath());
//         //获取文件名称
//         System.out.println(file.getName());
//         //获取文件大小
//         System.out.println(file.length());

//         System.out.println(file.getAbsoluteFile());
//         //判断是否是个目录
//         System.out.println(file.isDirectory());
//         //获取其父目录的路径
//         System.out.println(file.getParent());
//         System.out.println(String.format("年龄是%d",12));
//         System.out.printf("收入是%s",12);
//         System.out.format("%s","哈哈");







//     }

}
