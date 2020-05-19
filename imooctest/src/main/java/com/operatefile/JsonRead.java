package com.operatefile;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang.text.StrBuilder;

import java.io.*;

public class JsonRead {

    public JSONArray getJson(String file) throws IOException {
        JSONArray jsonArray = null;
        try {
            String path = "C:\\Users\\ASUS\\JmeterTest\\imooctest\\src\\main\\resources\\" + file;

            FileInputStream inputStream = new FileInputStream(path);
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            StrBuilder str = new StrBuilder();
            while ((line = reader.readLine()) != null){
                str.append(line);

            }
            jsonArray = JSON.parseArray(str.toString());
//        System.out.println(jsonArray);



        } catch (IOException e) {
            e.printStackTrace();
        }
        return jsonArray;
    }

    public static void main(String[] args) throws IOException {

        JsonRead read = new JsonRead();
        JSONArray jsonArray = read.getJson("test/c.json");
        System.out.println(jsonArray);
        JSONObject jsonObject = jsonArray.getJSONObject(0);
        System.out.println(jsonObject);

    }
}
