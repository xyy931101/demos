package com.examplexyy.demo.algorithm.swordFingerOffer;

/**
 * 剑指offer第六题
 * 从尾到头打印数组
 * @Author: Xiongyy
 * @Date: 2021/2/8 23:11
 * .............................................
 * 佛祖保佑             永无BUG
 */
public class SixPrintTree {

    public static void main(String[] args) {
        ListNode head = ListNode.getHead();
        printListReverse(head);
    }

    private static void printListReverse(ListNode head){
        if(head.next != null){
            printListReverse(head.next);
        }
        System.out.println(head.value);
    }
}
