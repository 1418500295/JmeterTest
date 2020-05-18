package com.example.demo.daineimooc;

import com.example.demo.daineimooc.config.MyConfig;
import com.example.demo.daineimooc.service.HelloService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.testng.annotations.Test;

import javax.annotation.Resource;

public class Demo {
    @Resource
    HelloService helloService;

    @Test
    public void test(){
        ApplicationContext context = new AnnotationConfigApplicationContext(MyConfig.class);
        System.out.println(context.getBean(HelloService.class));
        System.out.println(context.getBeansOfType(HelloService.class));





    }
}
