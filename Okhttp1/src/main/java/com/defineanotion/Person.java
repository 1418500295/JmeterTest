package com.defineanotion;

import jdk.nashorn.internal.codegen.types.Type;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
/*
Target表示注解在方法、类、还是属性上
Retention中RUNTIME表示运行时注解
 */
@Target({ElementType.FIELD,ElementType.TYPE,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Person {

    String name();
}


