package com.examplexyy.demo.algorithm.leetcode.array;

/**
 * 34. 在排序数组中查找元素的第一个和最后一个位置
 * 给定一个按照升序排列的整数数组 nums，和一个目标值 target。找出给定目标值在数组中的开始位置和结束位置。
 *
 * 如果数组中不存在目标值 target，返回 [-1, -1]。
 * @Author: Xiongyy
 * @Date: 2021/4/18 16:31
 * .............................................
 * 佛祖保佑             永无BUG
 */
public class FindFirstAndLastPositionOfElementInSortedArray34 {

    public static void main(String[] args) {
        FindFirstAndLastPositionOfElementInSortedArray34 f = new FindFirstAndLastPositionOfElementInSortedArray34();
        System.out.println(f.searchRange(new int[]{1}, 1));
    }

    public int[] searchRange(int[] nums, int target) {
        if (nums == null || nums.length < 1) {
            return new int[] {-1, -1};
        }
        if (nums[0] > target || nums[nums.length - 1] < target) {
            return new int[] {-1, -1};
        }
        if (nums.length == 1 && nums[0] == target) {
            return new int[] {0, 0};
        }

        int[] res = new int[2];
        boolean exists = false;
        for (int i = 0; i < nums.length; i++) {
           if (!exists){
               if (nums[i] == target) {
                   res[0] = 1;
                   exists = true;
               }
           }else {
               if (nums[i] != target) {
                   res[1] = i - 1;
                   return res;
               }
           }
        }
        return new int[] {-1, -1};
    }
}
