package com.imooc;

import java.util.*;

public class Second {
    public static void main(String[] args) {
        Map<String,String> map = new HashMap<String, String>();
        map.put("name","daine");
        map.put("age","12");
        map.put("sex","male");
        System.out.println(map);
        for (Map.Entry<String,String> entry:map.entrySet()){
            System.out.println("key:"+entry.getKey()+", value:"+entry.getValue());
        }
        Iterator<Map.Entry<String,String>> entryIterator = map.entrySet().iterator();
        while (entryIterator.hasNext()){
            Map.Entry<String,String> entry = entryIterator.next();
            System.out.println("key="+entry.getKey()+", value="+entry.getValue());

        }
        for (String key:map.keySet()){
            String value = map.get(key);
            System.out.println("key="+key+", value="+value);
        }



    }

}
