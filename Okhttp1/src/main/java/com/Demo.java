import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

import static java.lang.String.*;

public class Demo {
    public static void main(String[] args) throws IOException {
//        String path = "C:\\Users\\ASUS\\JmeterTest\\Okhttp1\\src\\main\\java\\a.json";
        String path = System.getProperty("user.dir") + "/Okhttp1/src/main/java/a.json";
        FileInputStream inputStream = new FileInputStream(path);
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder stringBuilder = new StringBuilder();
        String line = "";
        while ((line = reader.readLine()) != null){
            stringBuilder.append(line);
        }
        Gson gson = new Gson();
        //将json数组转换为jsonArray对象
        JsonArray jsonElements = gson.fromJson(stringBuilder.toString(),JsonArray.class);
        JsonObject jsonObject = jsonElements.get(0).getAsJsonObject();
        //将jsonobject转换为string
        String c = gson.toJson(jsonObject);
        System.out.println(c.getClass());

        JsonObject jsonObject1 = new JsonObject();
        jsonObject1.addProperty("sex","male");
        jsonObject1.addProperty("sala","1000");
        System.out.println(jsonObject1);

        Map<String,String> map = new HashMap<>();
        map.put("name","james");
        map.put("age","11");









    }

}
