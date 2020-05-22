package com.example.demo.daineimooc.service;

import com.example.demo.daineimooc.DaineimoocApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DaineimoocApplication.class)
public class DaineTest {
    @Autowired
    ApplicationContext ioc;

    @Test
    public void test(){
        boolean x = ioc.containsBean("daine");
        System.out.println(x);


    }

}