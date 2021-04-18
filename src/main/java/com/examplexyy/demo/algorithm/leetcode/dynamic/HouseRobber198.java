package com.examplexyy.demo.algorithm.leetcode.dynamic;

/**
 * 198. 打家劫舍
 * 你是一个专业的小偷，计划偷窃沿街的房屋。每间房内都藏有一定的现金，
 * 影响你偷窃的唯一制约因素就是相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。
 *
 * 给定一个代表每个房屋存放金额的非负整数数组，计算你 不触动警报装置的情况下 ，一夜之内能够偷窃到的最高金额。
 * @Author: Xiongyy
 * @Date: 2021/4/15 22:15
 * .............................................
 * 佛祖保佑             永无BUG
 */
public class HouseRobber198 {

    public int rob(int[] nums) {
        if (nums == null || nums.length < 1) {
            return 0;
        }
        if (nums.length == 1) {
            return nums[0];
        }

        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);
        for (int i = 2; i < nums.length; i++) {
            //每个都有偷与不偷的。如果偷，则最大值是前两个加上本身，不偷则是前一个
            dp[i] = Math.max(dp[i - 1], dp[i -2] + nums[i]);
        }
        return dp[nums.length - 1];
    }
}
