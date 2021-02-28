package com.examplexyy.demo.concurrent.unsafe;

import java.util.Arrays;
import java.util.List;

/**
 * @Author: Xiongyy
 * @Date: 2021/1/3 12:48
 * .............................................
 * 佛祖保佑             永无BUG
 */
public class ListTest {


    public static void main(String[] args) {
        List<String> s = Arrays.asList("1", "2", "3");
        s.forEach(System.out::println);
    }
}
