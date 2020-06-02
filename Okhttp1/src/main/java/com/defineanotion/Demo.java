package com.defineanotion;


import jdk.nashorn.internal.objects.annotations.Property;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

@Person(name = "哈哈")
public class Demo {
    @Person(name = "daine")
    private String name;

    @Person(name = "11")
    private String age;

    @Person(name = "james")
    public static void run(){
        System.out.println("hello");

    }

    public static void main(String[] args) throws NoSuchMethodException {
        //获取类模板
        Class c = Demo.class;

        //获取所有的字段
        Field[] f = c.getDeclaredFields();
        for (Field field: f){
            field.setAccessible(true);
            //判断这个字段是否有这个注解
            if (field.isAnnotationPresent(Person.class)){
                Person person = field.getAnnotation(Person.class);
                System.out.println(field.getName()+":"+person.name());
            }
        }
        //获取类注解信息
        Person person = (Person) c.getAnnotation(Person.class);
        System.out.println(person.name());

        //获取所有方法的注解信息
        Method[] methods = c.getDeclaredMethods();
        for (Method method :methods){
            if (method.isAnnotationPresent(Person.class)){
                Person person1 = method.getAnnotation(Person.class);
                System.out.println(method.getName()+":"+person1.name());
            }
        }

        //获取指定方法的注解信息
        Method method1 = c.getDeclaredMethod("run");
        Person person2 = method1.getAnnotation(Person.class);
        System.out.println(method1.getName()+":"+person2.name());



    }
}
