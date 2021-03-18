package com.examplexyy.demo;

import com.alibaba.fastjson.JSONObject;
import com.examplexyy.demo.algorithm.MathUtil;

import java.util.Arrays;

/**
 * @Author: Xiongyy
 * @Date: 2020/4/7 22:43
 * .............................................
 * 佛祖保佑             永无BUG
 * 佛曰:
 * 写字楼里写字间，写字间里程序员；
 * 程序人员写程序，又拿程序换酒钱。
 * 酒醒只在网上坐，酒醉还来网下眠；
 * 酒醉酒醒日复日，网上网下年复年。
 * 但愿老死电脑间，不愿鞠躬老板前；
 * 奔驰宝马贵者趣，公交自行程序员。
 * 别人笑我忒疯癫，我笑自己命太贱；
 * 不见满街漂亮妹，哪个归得程序员？
 */
public class Hi {
    public static void main(String[] args) {
        System.out.println("hi");
        int[] nums = MathUtil.generateRandomArray(100, 100);
//        int[] nums = new int[]{87,38,88,69,4,81,45,49,65,23};
//        int[] nums = new int[]{38,69,87,81,45,49,65,88};
        int[] copyOf = Arrays.copyOf(nums, nums.length);

        System.out.println("my..................");
        System.out.println(JSONObject.toJSONString(nums));
        quickSort(nums);
        System.out.println(JSONObject.toJSONString(nums));
        Arrays.sort(copyOf);

        for (int i = 0; i < nums.length; i++) {
            if (copyOf[i] != nums[i]){
                System.out.println("fuck.....");
                break;
            }
        }

        System.out.println("nice!!!!!");
    }

    public static int[] quickSort(int[] nums) {
        quickSelect(nums, 0, nums.length - 1);
        return nums;
    }

    private static void quickSelect(int[] nums, int leftIndex, int rightIndex) {
        //如果左下标比右下标小,证明已经排序完了,直接返回
        if(leftIndex >= rightIndex){
            return;
        }

        //找到比中轴pivot大和小的数，然后做交换
        int left = leftIndex, right = rightIndex, pivot = nums[leftIndex];
        while (left < right){
            while (left < right && nums[right] >= pivot){
                right -- ;
            }

            if(left < right){
                nums[left] = nums[right];
            }

            while (left< right && nums[left] < pivot){
                left++;
            }

            nums[right] = nums[left];
        }
        nums[left] = pivot;
        quickSelect(nums, leftIndex, left - 1);
        quickSelect(nums, left+ 1, rightIndex);

    }


    public static int[] findKthLargest(int[] nums, int k){
        int kth = getKth(nums.length + 1, nums, 0, nums.length - 1);
        int[] copyOf = Arrays.copyOf(nums, nums.length);
        System.out.println();
        return nums;
    }

    public static int getKth(int k, int[] nums, int start, int end) {

        int pivot = nums[end];

        int left = start;
        int right = end;

        while (left < right) {

            while (left < right && nums [left] < pivot) {
                left++;
            }
            if (left < right){
                nums[end] = nums[left];
            }
            while (left < right && nums[right] >= pivot) {
                right--;
            }
            nums[right] = nums[left];

        }
        nums[left] = pivot;
        if (k == left + 1) {
            return pivot;
        } else if (k < left + 1) {
            return getKth(k, nums, start, left - 1);
        } else {
            return getKth(k, nums, left + 1, end);
        }
    }

}
