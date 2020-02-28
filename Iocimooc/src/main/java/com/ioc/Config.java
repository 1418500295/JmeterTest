package com.ioc;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

    @Bean
    public Hello hello(){
        Hello hello = new Hello();
        hello.setName("james");
        hello.setSex("male");
        return hello;
    }
}
