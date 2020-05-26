package com.example.demo.daineimooc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com")
public class DaineimoocApplication {

    public static void main(String[] args) {
        SpringApplication.run(DaineimoocApplication.class, args);
    }

}
