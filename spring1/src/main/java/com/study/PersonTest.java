package com.study;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;
import org.testng.annotations.Test;

@Component
public class PersonTest {

    @Autowired
    Person person;

    @Test
    public void test(){
        person.run();
    }


}
