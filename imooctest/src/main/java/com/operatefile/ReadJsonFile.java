package com.operatefile;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang.text.StrBuilder;

import java.io.*;

public class ReadJsonFile {

    public static void main(String[] args) throws IOException {
        String path = "C:\\Users\\ASUS\\JmeterTest\\imooctest\\src\\main\\java\\com\\operatefile\\b.json";
        String path1 = "C:\\Users\\ASUS\\JmeterTest\\imooctest\\src\\main\\resources\\c.json";
        FileInputStream fileInputStream = new FileInputStream(path1);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
        String line;
        StrBuilder str = new StrBuilder();
        while ((line = bufferedReader.readLine()) != null){
            System.out.println(line);
            str.append(line);

        }
        JSONObject jsonObject = JSON.parseObject(String.valueOf(str));
        System.out.println(jsonObject.get("name"));
        System.out.println(jsonObject);







    }
}
