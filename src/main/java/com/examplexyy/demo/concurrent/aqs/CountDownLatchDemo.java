package com.examplexyy.demo.concurrent.aqs;

import java.util.concurrent.CountDownLatch;

/**
 * @Author: Xiongyy
 * @Date: 2021/1/3 13:38
 * .............................................
 * 佛祖保佑             永无BUG
 * 线程自减计数器
 */
public class CountDownLatchDemo {


    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(6);
        for (int i = 0; i < 6; i++) {
            new Thread(() ->{
                System.out.println("执行了一个线程, 还剩:" + countDownLatch.getCount()) ;
                countDownLatch.countDown();
            }, String.valueOf(i)).start();
        }

        countDownLatch.await();

        System.out.println("没有了吗");
    }
}
