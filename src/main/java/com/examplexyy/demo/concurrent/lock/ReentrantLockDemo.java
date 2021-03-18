package com.examplexyy.demo.concurrent.lock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * AQS
 * @Author: Xiongyy
 * @Date: 2021/3/14 10:29
 * .............................................
 * 佛祖保佑             永无BUG
 */
public class ReentrantLockDemo {

    public static void main(String[] args) {
        ReentrantLock lock = new ReentrantLock();


        lock.lock();

    }
}
