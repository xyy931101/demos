package com.examplexyy.demo.algorithm.leetcode.tree;

/**
 * 96. 不同的二叉搜索树
 * @author xiongyayun
 * @date 2021/5/14 23:25
 */
public class UniqueBinarySearchTrees96 {

    public int numTrees(int n) {
        int[] dp = new int[n+1];
        dp[0] = 1;
        dp[1] = 1;

        for(int i = 2; i < n + 1; i++)
            for(int j = 1; j < i + 1; j++)
                dp[i] += dp[j-1] * dp[i-j];

        return dp[n];
    }
}
