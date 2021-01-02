package com.examplexyy.demo.spring;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Author: Xiongyy
 * @Date: 2020/12/8 22:42
 * .............................................
 * 佛祖保佑             永无BUG
 */
public class TestSpringContext {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext tx = new ClassPathXmlApplicationContext("testSpring.xml");
        BookY bean = tx.getBean(BookY.class);
        System.out.println(bean);
    }
}