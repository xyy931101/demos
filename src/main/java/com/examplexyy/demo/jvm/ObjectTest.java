package com.examplexyy.demo.jvm;

import org.openjdk.jol.info.ClassLayout;

import java.util.Collections;

/**
 * @Author: Xiongyy
 * @Date: 2021/1/24 22:05
 * .............................................
 * 佛祖保佑             永无BUG
 */
public class ObjectTest {

    public static void main(String[] args) {
        Object obj = new Object();
        System.out.println(ClassLayout.parseInstance(obj).toPrintable());

        synchronized (obj){
            System.out.println(ClassLayout.parseInstance(obj).toPrintable());

        }
    }
}
