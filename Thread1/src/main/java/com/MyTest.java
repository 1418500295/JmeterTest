package com;

import com.google.gson.internal.$Gson$Preconditions;
import org.java_websocket.drafts.Draft_6455;

import java.awt.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Base64;

public class MyTest implements Runnable {

    @Override
    public void run() {
        try {
            new Client1(new URI("ws://localhost:1423"),new Draft_6455()).doIt();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    public void start(){
        Thread t = null;
        for (int i=0;i<10;i++){
            t = new Thread(new MyTest());
            t.start();
        }
        try {
            t.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        System.out.println("开始时间"+System.currentTimeMillis());
        new MyTest().start();
        System.out.println("结束时间"+System.currentTimeMillis());
      







    }
}
