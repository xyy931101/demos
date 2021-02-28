package com.examplexyy.demo.concurrent;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @Author: Xiongyy
 * @Date: 2021/1/3 13:48
 * .............................................
 * 佛祖保佑             永无BUG
 * 线程自增计数器
 */
public class CyclicBarrierDemo {

    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(7, () ->{
            System.out.println(System.currentTimeMillis() + "  集齐7颗龙珠！！！" + Thread.currentThread().getName());
        });

        for (int i = 0; i < 7; i++) {
            new Thread(() ->{
                System.out.println(Thread.currentThread().getName() + System.currentTimeMillis() + "  这是在收集啥的呢？");
                try {
                    Thread.sleep(10000);
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }).start();
        }

        System.out.println(System.currentTimeMillis() + "  这是主线程");
    }

}
