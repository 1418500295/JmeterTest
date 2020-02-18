package com.imooc;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class Forth {
    public static void main(String[] args) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name","daine");
        jsonObject.put("age",12);
        jsonObject.put("sex","male");
        System.out.println(jsonObject);
        String s = jsonObject.getString("name");
        System.out.println(s);

    }
}
