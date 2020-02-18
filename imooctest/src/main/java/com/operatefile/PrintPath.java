package com.operatefile;

import java.io.File;

public class PrintPath {
    public static void main(String[] args) {
        String path = "C:\\Users\\ASUS\\JmeterTest\\imooctest\\src\\main\\java\\com\\operatefile\\b.json";
        File file = new File(path);
        System.out.println(file.getPath());
        System.out.println(file.getAbsolutePath());



    }
}
