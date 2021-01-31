package com.examplexyy.demo.algorithm;

import com.alibaba.fastjson.JSONObject;

/**
 * @Author: Xiongyy
 * @Date: 2021/1/30 22:07
 * .............................................
 * 佛祖保佑             永无BUG
 */
public class QuickSort {

    public static void main(String[] args) {
        int[] arr = new int[]{7, 3, 2, 8, 1, 6, 5, 4, 9, 6, 20, 19, 0};
        sort(arr, 0, arr.length - 1);
        System.out.println(JSONObject.toJSONString(arr));
    }

    private static int partition(int[] arr, int leftBound, int rightBound) {
        int pivot = arr[rightBound];
        int left = leftBound;
        int right = rightBound - 1;
        while (left < right) {
            while (left <= right && arr[left] <= pivot) {
                left++;
            }
            while (left < right && arr[right] > pivot) {
                right--;
            }
            if (left < right) {
                swap(arr, left, right);
            }

            System.out.println("left:" + left + " right:" + right);

        }
        swap(arr, left, rightBound);

        return left;
    }

    private static void sort(int[] arr, int leftBound, int rightBound) {
        if (leftBound >= rightBound) {
            return;
        }
        int mid = partition(arr, leftBound, rightBound);
        sort(arr, leftBound, mid - 1);
        sort(arr, mid+ 1, rightBound);
    }

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

}
