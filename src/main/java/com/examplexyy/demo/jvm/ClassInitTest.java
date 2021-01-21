package com.examplexyy.demo.jvm;

/**
 * @Author: Xiongyy
 * @Date: 2021/1/18 22:13
 * .............................................
 * 佛祖保佑             永无BUG
 */
public class ClassInitTest {
    private static int num = 1;
    private int num_3 = 3;

    static {
        num = 1;
        number = 10;
//        num_3 = 30;
        System.out.println(num);
//        System.out.println(number);  非法的前向引用
    }
    private static int number = 20;

    public static void main(String[] args) {
        System.out.println(num);
        System.out.println(number);
    }

}
