package com;

import java.io.File;

public class One {
    public static void main(String[] args) {
        String path = "C:\\Users\\ASUS\\JmeterTest\\Thread1\\src\\main\\java\\com\\a.txt";
        File file  = new File(path);
        //判断文件是否存在
        System.out.println(file.exists());
        //获取文件绝对路径
        System.out.println(file.getAbsolutePath());
        System.out.println(file.getPath());
        //获取文件名称
        System.out.println(file.getName());
        //获取文件大小
        System.out.println(file.length());

        System.out.println(file.getAbsoluteFile());
        //判断是否是个目录
        System.out.println(file.isDirectory());
        //获取其父目录的路径
        System.out.println(file.getParent());
        System.out.println(String.format("年龄是%d",12));
        System.out.printf("收入是%s",12);
        System.out.format("%s","哈哈");







    }

}
