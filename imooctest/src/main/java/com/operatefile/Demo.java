package com.operatefile;

import org.apache.commons.lang.text.StrBuilder;
import org.bouncycastle.tsp.TSPUtil;

import java.io.*;

public class Demo {

    public static void main(String[] args) throws IOException {
        Demo demo = new Demo();
        int a = 3;
        //获取当前文件所在目录
        String path = demo.getClass().getResource("").getPath();
        System.out.println(path);
        //获取项目路径
        String p = System.getProperty("user.dir");
        System.out.println(p);
        String path1 = "C:\\Users\\ASUS\\JmeterTest\\imooctest\\src\\main\\java\\com\\operatefile\\a.txt";
        FileInputStream fileInputStream = new FileInputStream((path1));
        BufferedReader reader = new BufferedReader(new InputStreamReader(fileInputStream));
        String line = "";
        StrBuilder strBuilder = new StrBuilder();
        while ((line = reader.readLine())!= null){
            strBuilder.append(line);

        }
        System.out.println(strBuilder.toString());









    }
}
