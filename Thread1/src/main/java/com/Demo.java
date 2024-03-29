package com;

/*
代码块详解
 */
public class Demo {
    private String name;
    private int age;

    static {
        System.out.println("这是静态代码块");
    }
    //饱汉模式线程安全单例模式
    private volatile static CloseableHttpClient client;
    
    //高并发下的计数器
    protected static LongAdder longAdder = new LongAdder();
    protected static AtomicInteger atomicInteger = new AtomicInteger(0);


    public static CloseableHttpClient getInstance(){
        if (client == null){
            synchronized (PayTest.class){
                if (client == null){
                    PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
                    client = HttpClients.custom()
                    .setConnectionManager(cm)
                    .build();
                }
            }
        }
        return client;
    }

    {
        System.out.println("这是构造代码块");
    }

    public Demo(String name, int age) {
        this.name = name;
        this.age = age;
        System.out.println("这是构造方法");
    }

    public  void run(){

        System.out.println("小明在跑步");
        {
            System.out.println("这是普通代码块");
        }
    }
    
    
    public static boolean isNumber(String content){
        String reg = "^-?[0-9]+(.[0-9]+)?$";
        return content.matches(reg);
        
    }

    public static boolean isInteger(String content){
        return content.matches("[0-9]+$");
    }
    
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
    
    public void print () {
        System.out.println("———————————┬─────——──┬─────—────────┬───────────┬────────—────┬──────────——┬");
        System.out.println("   总耗时   │  并发数  │     成功数    │   失败数    │ 平均响应时间  │    qps     │");
        System.out.println("────——————─┼─────——──┼────────—─────┼───────────┼─────────────┼───────────—┼─");

    }
    
    System.out.println("压测结果：");
        print();
        useTime = (float)(eTime -sTime)/1000;
        winNum = succ.sum();
        failNum =fail.sum();
        avgResTime = avaRespTime();
        qps = QPS();
        System.out.printf("    %.1f   │    %s    │    %s    │    %s    │    %.3f    │    %.1f    │\n"
                ,useTime,THREAD_NUM,winNum,failNum,avgResTime,qps);



    public static void main(String[] args) {
//        Demo demo = new Demo("daine",12);
//        demo.run();
//        Demo demo1 = new Demo("james",11);
//        demo1.run();
        
      



    }
    
    
    
    CloseableHttpClient client = HttpClients.custom()
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
    
    
    
    
    
    
    
    
    
    
    
    
    
    private static final String CHAT_ID = "505";
    private static final String TOKEN = "6123-0zU";

    public static void main(String[] args) {
        TelegramBot bot = new TelegramBot(TOKEN);
        bot.setUpdatesListener(updates -> UpdatesListener.CONFIRMED_UPDATES_ALL, e -> {
            if (e.response() != null) {
                e.response().errorCode();
                e.response().description();
            } else {
                e.printStackTrace();
            }
        });
        File file = new File("C:ex.html");
        SendResponse response1 = bot.execute(new SendMessage(CHAT_ID,"hello"));
        SendResponse response = bot.execute(new SendDocument(CHAT_ID,file));
        if (response.isOk()) {
            System.out.println("发送成功");
        } else {
            System.out.println("发送失败");
        }
    }
}
