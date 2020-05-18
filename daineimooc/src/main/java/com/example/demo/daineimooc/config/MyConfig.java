package com.example.demo.daineimooc.config;

import com.example.demo.daineimooc.service.HelloService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyConfig {

    @Bean //将HelloService实例对象加载到容器，bean id就是方法名
    public HelloService helloService(){
        return new HelloService();
    }
}
