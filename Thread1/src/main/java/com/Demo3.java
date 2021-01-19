package com;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Demo3 {

    private static final int num = 500   ;  //设置集合点/并发数
    private static final CountDownLatch countDownLatch = new CountDownLatch(num); //创建计数器
    private static final CountDownLatch latch = new CountDownLatch(1); //设置发令枪
    private static final int okSum = 0; //初始化成功的请求数量
    public void go(){
        ExecutorService executorService = Executors.newCachedThreadPool();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        for (int i =0;i<num;i++){
            executorService.execute(new Runnable() {
                @SneakyThrows
                @Override
                public void run() {
                    latch.await();    //  阻塞请求
                    PayTest.send_req();   //发送请求
                    countDownLatch.countDown();  //每执行一次，计数器减一
                }
            });
        }
        //开始时间
        long sTime = System.currentTimeMillis();
        System.out.println("开始时间"+(format.format(sTime)));
        latch.countDown();  //  准备开始！！！
        try {
            countDownLatch.await();  //阻塞主线程，等待子线程创建完毕,所有线程并发请求
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //结束时间
        long eTime = System.currentTimeMillis();
        System.out.println("结束时间"+(format.format(eTime)));
        System.out.println("总耗时: "+(float)(eTime - sTime)/1000+"秒");
        executorService.shutdown(); //关闭线程池，停止接受新任务，原来的任务继续执行
    }
    public static void main(String[] args) {
        new Demo3().go();

    }



}
