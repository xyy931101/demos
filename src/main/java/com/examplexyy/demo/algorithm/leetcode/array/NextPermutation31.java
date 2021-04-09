package com.examplexyy.demo.algorithm.leetcode.array;

/**
 * 31. 下一个排列
 * 实现获取 下一个排列 的函数，算法需要将给定数字序列重新排列成字典序中下一个更大的排列。
 *
 * 如果不存在下一个更大的排列，则将数字重新排列成最小的排列（即升序排列）。
 *
 * 必须 原地 修改，只允许使用额外常数空间。
 * @Author: Xiongyy
 * @Date: 2021/4/8 23:06
 * .............................................
 * 佛祖保佑             永无BUG
 */
public class NextPermutation31 {

    public void nextPermutation(int[] nums) {
        if (nums == null || nums.length < 2) {
            return;
        }
        int right = nums.length - 1;
        while (right > 0){
            if (nums[right] > nums[right - 1]) {
                swap(nums, right - 1, right);
                return;
            }
            right--;
        }

        reverse(nums, 0);
    }

    public void swap(int[] nums, int left, int right){
        int k = nums[left];
        nums[left] = nums[right];
        nums[right] = k;
    }

    public void reverse(int[] nums, int start) {
        int left = start, right = nums.length - 1;
        while (left < right) {
            swap(nums, left, right);
            left++;
            right--;
        }
    }

}
