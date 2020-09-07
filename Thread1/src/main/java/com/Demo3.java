package com;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Demo3 {

    private CountDownLatch countDownLatch = new CountDownLatch(20);//创建计数器
    private int num =20; //设置集合点/并发数
    public  void go() {
        ExecutorService service = Executors.newCachedThreadPool();
        for (int i=0;i<num;i++){
            service.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println("hello");
                    //每执行一次，计数器就减一
                    countDownLatch.countDown();
                }
            });
        }
        try {
            countDownLatch.await();//阻塞主线程，等待子线程创建完毕
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //关闭线程池；停止接收新任务，原来的任务继续执行
        service.shutdown();


    }

    public static void main(String[] args) {
        System.out.println(System.currentTimeMillis());
        new Demo3().go();
        System.out.println(System.currentTimeMillis());
    }



}
