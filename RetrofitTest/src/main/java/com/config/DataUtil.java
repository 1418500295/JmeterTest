package com.config;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.JsonObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class DataUtil {

    private static DataUtil dataUtil = new DataUtil();

    public static JSONObject getTestData(String fileName, int index) throws IOException {
        InputStream inputStream = dataUtil.getClass().getResourceAsStream("/testdata/" + fileName);
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        String line;
        StringBuilder stringBuilder = new StringBuilder();
        while ((line = reader.readLine()) != null){
            stringBuilder.append(line);
        }
        JSONArray jsonArray = JSON.parseArray(stringBuilder.toString());
        return jsonArray.getJSONObject(index);

    }

}
