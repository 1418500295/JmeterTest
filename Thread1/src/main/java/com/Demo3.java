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
        System.out.println("开始时间" + sTime);
        latch.countDown();
        countDownLatch.await();
        long eTime = System.currentTimeMillis();
        System.out.println("结束时间: " + eTime);
//        System.out.println("总耗时: " + (float) (eTime - sTime) / 1000 + "秒");
        System.out.println("最小响应时间: "+(Float.parseFloat(Collections.min(PayTest.list1)))/1000+"秒");
        System.out.println("最大响应时间: "+ (Float.parseFloat(Collections.max(PayTest.list1)))/1000+"秒");
        int sum = 0;
        for (int i = 0;i<PayTest.list1.size();i++){
            sum+=Integer.parseInt(PayTest.list1.get(i));
        }
        System.out.println("平均响应时间: "+(float)(sum/PayTest.list1.size())/1000+"秒");
        System.out.println("QPS: "+(num/((float)(sum/PayTest.list1.size())/1000)));
        System.out.println("成功的数量: "+PayTest.success);
        System.out.println("失败的数量: "+(num-PayTest.success));
        executorService.shutdown(); //关闭线程池，停止接受新任务，原来的任务继续执行
    }
    public static void main(String[] args) {
        new Demo3().go();

    }



}
