package com.examplexyy.demo.algorithm.leetcode;

/**
 * 整数反转
 * @Author: Xiongyy
 * @Date: 2021/2/2 23:40
 * .............................................
 * 佛祖保佑             永无BUG
 */
public class ReverseInteger_7 {


    public static void main(String[] args) {
        System.out.println(reverse(463847412));
    }

    public static int reverse(int x) {
        int result = 0;
        while(x != 0){
            int pop = x % 10;
            if(x > Integer.MAX_VALUE / 10 || result < Integer.MIN_VALUE ){
                return 0;
            }
            result = result * 10 + pop;
            x /= 10;
        }
        return result;
    }


}
