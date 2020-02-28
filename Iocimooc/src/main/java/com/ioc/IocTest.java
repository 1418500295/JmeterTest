package com.ioc;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.awt.*;

public class IocTest {

    public static void main(String[] args) {

        AnnotationConfigApplicationContext ioc = new
                AnnotationConfigApplicationContext(Config.class);
        Hello hello = (Hello) ioc.getBean("hello");
        System.out.println(hello.getSex());


    }


}
