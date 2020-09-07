package com;

import com.google.gson.internal.$Gson$Preconditions;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Demo2 implements Runnable{
    private static CountDownLatch countDownLatch = new CountDownLatch(100);

    public void runIt() {
        Thread t = null;
        for (int i = 0;i<100;i++){
            t = new Thread(new Demo2());
            t.start();
        }
        try {
            t.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//        countDownLatch.countDown();
    }

    @Override
    public void run() {

//            countDownLatch.await();
        System.out.println("hello");


    }

    public static void main(String[] args) {
        System.out.println(System.currentTimeMillis());
        new Demo2().runIt();
        System.out.println(System.currentTimeMillis());
    }
}
