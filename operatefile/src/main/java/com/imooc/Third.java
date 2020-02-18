package com.imooc;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.logging.Logger;

public class Third {

    public static void main(String[] args) {
        Set<String> set = new HashSet<String>();
        set.add("苹果");
        set.add("香蕉");
        set.add("哈密瓜");
        System.out.println(set);

        Iterator<String> iterator = set.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }

        for (String s: set){
            System.out.println(s);
        }

    }

}
