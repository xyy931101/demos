package com.examplexyy.demo.algorithm.leetcode.tree;

import com.examplexyy.demo.algorithm.bilibili.Node;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 *
 * 给定一个二叉树，检查它是否是镜像对称的。
 * 例如，二叉树 [1,2,2,3,4,4,3] 是对称的。
 *
 *     1
 *    / \
 *   2   2
 *  / \ / \
 * 3  4 4  3
 *  
 *
 * 但是下面这个 [1,2,2,null,3,null,3] 则不是镜像对称的:
 *
 *     1
 *    / \
 *   2   2
 *    \   \
 *    3    3
 * @Author: Xiongyy
 * @Date: 2021/2/7 23:05
 * .............................................
 * 佛祖保佑             永无BUG
 */
public class IsSymmetric {

//    [1,2,2,3,4,4,3]
    public static void main(String[] args) {
        TreeNode one = new TreeNode(1);
        TreeNode two = new TreeNode(2);
        TreeNode three = new TreeNode(2);
        one.left = two;
        one.right = three;
        TreeNode four = new TreeNode(3);
        TreeNode five = new TreeNode(4);
        two.left = four;
        two.right = five;
        TreeNode six = new TreeNode(4);
        TreeNode se = new TreeNode(3);
        three.left = six;
        three.right = se;

        System.out.println(solution(one));

    }

    public static boolean solution(TreeNode root){
        return check(root, root);
    }

    public static boolean check(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        }
        if (p == null || q == null) {
            return false;
        }
        return p.val == q.val && check(p.left, q.right) && check(p.right, q.left);
    }
}
