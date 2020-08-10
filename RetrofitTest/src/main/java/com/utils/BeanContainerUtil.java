package com.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BeanContainerUtil {

    @Autowired
    static ApplicationContext applicationContext;

    static {
        applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
    }

    public static <T> T getBean(Class<T> tClass){
        return applicationContext.getBean(tClass);

    }

    public static Object getBean(String className){
        return applicationContext.getBean(className);
    }
}
