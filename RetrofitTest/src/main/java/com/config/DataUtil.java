package com.config;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Objects;

public class DataUtil {


    public static JSONObject getTestData(String fileName, int index) throws IOException {
        JSONArray jsonArray = null;
        try {
            InputStream inputStream = DataUtil.class.getResourceAsStream("/testdata/" + fileName);
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            StringBuilder stringBuilder = new StringBuilder();
            while ((line = reader.readLine()) != null){
                stringBuilder.append(line);
            }
            jsonArray = JSON.parseArray(stringBuilder.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return Objects.requireNonNull(jsonArray).getJSONObject(index);

    }

}
