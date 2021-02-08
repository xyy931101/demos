package com.examplexyy.demo.algorithm.leetcode.tree;

/**
 * 二叉树结构
 * @Author: Xiongyy
 * @Date: 2021/2/7 23:07
 * .............................................
 * 佛祖保佑             永无BUG
 */
public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}
