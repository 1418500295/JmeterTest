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



    public static void main(String[] args) {
//        Demo demo = new Demo("daine",12);
//        demo.run();
//        Demo demo1 = new Demo("james",11);
//        demo1.run();




    }
}
