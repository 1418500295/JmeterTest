package com.operatefile;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang.text.StrBuilder;

import java.io.*;

public class ReadFile2 {
    public static void main(String[] args) throws IOException {
        String path = "C:\\Users\\ASUS\\JmeterTest\\imooctest\\src\\main\\java\\com\\operatefile\\a.txt";
        String path1 = "C:\\Users\\ASUS\\JmeterTest\\imooctest\\src\\main\\java\\com\\operatefile\\b.json";
        FileOutputStream fileOutputStream = new FileOutputStream(path,true);
        fileOutputStream.write("\n这是第一次测试".getBytes());
        fileOutputStream.close();

        FileInputStream fileInputStream = new FileInputStream(path1);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
        String sb;
        StringBuilder str = new StringBuilder();
        while ((sb = bufferedReader.readLine()) != null){
            System.out.println(sb);
            str.append(sb);
        }
        JSONArray jsonArray = JSON.parseArray(str.toString());
        System.out.println(jsonArray);
        JSONObject jsonObject = jsonArray.getJSONObject(0);
        System.out.println(jsonObject);
        String s = jsonObject.getString("name");
        System.out.println(s);


    }
}
