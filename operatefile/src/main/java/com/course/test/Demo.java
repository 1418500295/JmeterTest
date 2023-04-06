package com.course.test;

import com.sun.imageio.plugins.common.ReaderUtil;
import sun.reflect.misc.FieldUtil;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class Demo {

    public static void main(String[] args) throws IOException {
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(simpleDateFormat.format(new Date()));
        long time = System.currentTimeMillis();
        System.out.println(time);
        System.out.println(new Date().getTime());
        Random random = new Random();
        System.out.println(random.nextInt(100));

        String str = "  我的名字是daine";
        String s = str.substring(3,5);
        System.out.println(s);
        String rs = str.trim();
        System.out.println(rs);
        String path = "C:\\Users\\ASUS\\JmeterTest\\operatefile\\src\\main\\java\\com\\course\\test\\a.txt";
        File file = new File(path);
        FileOutputStream fileOutputStream = new FileOutputStream(file,true);
        fileOutputStream.write("我是一只鸟".getBytes());
        fileOutputStream.close();
        FileInputStream fileInputStream = new FileInputStream(file);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
        String line = null;
        while ((line = bufferedReader.readLine() ) != null){
            System.out.println(line);
        }
        
        
        File file = new File("C:\\Users\\14185\\IdeaProjects\\myautitest\\a.txt");
        FileInputStream fileInputStream = new FileInputStream(file);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
        String line = null;
        StringBuilder stringBuilder = new StringBuilder();
        while ((line = bufferedReader.readLine() ) != null){
            stringBuilder.append(line);
            String[] s = line.split(",");
            System.out.println(s[0]);

        }
//        for (int i = 0; i < 5; i++) {
//            String s;
//            String c = String.format("%s%s",i,"1");
//            s = "name="+i+",age="+c+"\n";
//            FileOutputStream fileOutputStream = new FileOutputStream(file,true);
//            fileOutputStream.write(s.getBytes());
//            fileOutputStream.close();





    }
    }

