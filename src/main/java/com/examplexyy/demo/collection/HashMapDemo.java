package com.examplexyy.demo.collection;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author: Xiongyy
 * @Date: 2021/1/9 14:25
 * .............................................
 * 佛祖保佑             永无BUG
 */
public class HashMapDemo {

    public static void main(String[] args) {
        HashMap<String, String> map = new HashMap<>(8);
        map.put("1", "3");
        String oldValue = map.put("1", "2");

        System.out.println(oldValue);
        System.out.println("1000".hashCode());

        ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap<>();
    }
}
