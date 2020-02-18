package com.imooc;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class First {
    public static void main(String[] args) {

        List<String> list = new ArrayList<String>();
        list.add("diane");
        list.add("james");
        list.add("jordan");
        for (String li :list){
            System.out.println(li);
        }
        for (int i=0;i<list.size();i++){
            System.out.println(list.get(i));
        }
        Iterator<String> iterator = list.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }


    }
}
