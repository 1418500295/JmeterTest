package com.operatefile;

import org.apache.commons.lang.text.StrBuilder;

import java.io.*;
import java.util.Scanner;

public class ReadFile {
    public static void main(String[] args) throws IOException {
        String path = "C:\\Users\\ASUS\\JmeterTest\\imooctest\\src\\main\\java\\com\\operatefile\\a.txt";
        File file = new File(path);
        FileOutputStream fileOutputStream = new FileOutputStream(file,true);
        fileOutputStream.write("哈哈".getBytes("utf-8"));
        fileOutputStream.close();


        FileInputStream fileInputStream = new FileInputStream(path);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
        String line;
        while ((line = bufferedReader.readLine()) != null){
            System.out.println(line);


        }
        fileInputStream.close();


    }
}
