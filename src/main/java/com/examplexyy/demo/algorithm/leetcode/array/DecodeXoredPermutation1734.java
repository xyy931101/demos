package com.examplexyy.demo.algorithm.leetcode.array;

/**
 *
 * 1734. 解码异或后的排列
 * @author xiongyayun
 * @date 2021/5/11 8:07
 */
public class DecodeXoredPermutation1734 {

    public static void main(String[] args) {
        System.out.println(7 ^ 3 ^ 4 ^ 5);
        System.out.println(1 ^ 2 ^ 3 ^ 4);
        int all = 1;
        for (int i = 2; i <= 4; i++) {
            all ^= i;
        }
        System.out.println(all);
    }


    public int[] decode(int[] encoded) {
        return null;
    }
}
