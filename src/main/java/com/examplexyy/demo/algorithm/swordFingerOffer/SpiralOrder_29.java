package com.examplexyy.demo.algorithm.swordFingerOffer;

import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 *
 输入一个矩阵，按照从外向里以顺时针的顺序依次打印出每一个数字。
 * @Author: Xiongyy
 * @Date: 2021/2/19 23:17
 * .............................................
 * 佛祖保佑             永无BUG
 */
public class SpiralOrder_29 {

    // 1  2  3  4
    // 5  6  7  8
    // 9  10 11 12
    public static void main(String[] args) {
        int[][] board = {{1,2,3,4},{5, 6, 7, 8},{9, 10, 11, 12}};
        System.out.println(JSONObject.toJSONString(spiralOrder(board)));
    }

    public static List<Integer> spiralOrder(int[][] matrix) {
        if(matrix == null || matrix.length == 0){
            return null;
        }
        List<Integer> spiral = new ArrayList<>(matrix.length * matrix[0].length);
        int start = 0;
        while (matrix.length > start  * 2 && matrix[0].length > start * 2){
            addToSpiral(matrix, start, spiral);
            start++;
        }
        return spiral;
    }

    private static void addToSpiral(int[][] matrix, int start, List<Integer> spiral) {
        int rows = matrix.length;
        int cells = matrix[0].length;
        //先加上面一行
        for (int i = start; i < cells - start; i++){
            spiral.add(matrix[start][i]);
        }
        //打印右边列
        for (int i = start + 1; i < rows -start; i++) {
            spiral.add(matrix[i][cells - start -1]);
        }

        //打印下面列
        for (int i = cells - start -2; i >= start; i--) {
            spiral.add(matrix[rows - start -1][i]);
        }

        //添加左边列
        for (int i = rows - start - 2 ; i > start ; i--) {
            spiral.add(matrix[i][start]);
        }
    }

}
