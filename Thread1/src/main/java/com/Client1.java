package com;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft_6455;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Objects;

public class Client1 extends WebSocketClient {
    public Client1(URI serverUri, Draft_6455 draft_6455) {
        super(serverUri,draft_6455);
    }

    @Override
    public void onOpen(ServerHandshake serverHandshake) {
        System.out.println("连接打开");

    }

    @Override
    public void onMessage(String s) {
        System.out.println("收到服务端的消息是："+s);

    }

    @Override
    public void onClose(int i, String s, boolean b) {

    }

    @Override
    public void onError(Exception e) {

    }

    public  void doIt() {
        Client1 client1 = null;
        try {
            client1  = new Client1(new URI("ws://localhost:1423"),new Draft_6455());
            //发起连接
            client1.connect();
            while (!client1.getReadyState().equals(READYSTATE.OPEN)){
                System.out.println("正在连接。。。");
                TimeUnit.SECONDS.sleep(1);
            }
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        Objects.requireNonNull(client1).send("你好啊");
        TimeUnit.SECONDS.sleep(5);
        client1.close();
    }
}


//创建线程数为num
//每条线程再执行time次
//总执行次数为num * time次
public static void main(String[] args) throws InterruptedException {
        //获取当前程序运行包含的总线程数
        Map<Thread, StackTraceElement[]> map = Thread.getAllStackTraces();
        System.out.println(map.size());
        Thread t = null;
    
        for (int i = 0; i < num; i++) {
            int finalI1 = i;
            t = new Thread(new Runnable() {
                @Override
                public void run() {
//                    doIt();
                    Client client = null;
                    try {
                        client = new Client(new URI("ws://127.0.0.1:1234"), new Draft_6455());
                        //发起连接
                        client.connect();
                        while (!client.getReadyState().equals(READYSTATE.OPEN)) {
                            System.out.println("正在连接。。。");
                            TimeUnit.SECONDS.sleep(1);
                        }
                    } catch (URISyntaxException e) {
                        e.printStackTrace();
                    }
                    int time = 0;
                    while (time < 10) {
                        Objects.requireNonNull(client).send("你好,我是第" + finalI1 + "客户端");
                        time++;
                    }
                    countDownLatch.countDown();
                }
            });
            Objects.requireNonNull(t).start();
        }
        countDownLatch.await();
    }
