package com.resttemplates;


import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

@Hello(age = "12")
public class GetDemo {
    @Hello(age = "111")
    private static String name;
    @Hello(age = "222")
    private static String sex;
    @Hello(age = "1")
    public static void run(){
        System.out.println("run1");
    }

    @Hello(age = "2")
    public static void run1(){
        System.out.println("run2");
    }

    public static void main(String[] args){
        //获取类模板
        Class a = GetDemo.class;
        //获取类注解信息
        Hello hello = (Hello) a.getAnnotation(Hello.class);
        System.out.println(hello.age());

        //获取方法注解信息
        Method[] methods = a.getDeclaredMethods();
        for (Method method : methods){
            if (method.isAnnotationPresent(Hello.class)){
                Hello hello1 = method.getAnnotation(Hello.class);
                System.out.println(hello1.age());

            }
        }

        //获取类变量注解
        Field[] fields = a.getDeclaredFields();
        for (Field field : fields){
            if (field.isAnnotationPresent(Hello.class)){
                Hello hello2 = field.getAnnotation(Hello.class);
                System.out.println(hello2.age());
            }
        }



    }


}
