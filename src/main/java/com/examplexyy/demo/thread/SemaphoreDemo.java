package com.examplexyy.demo.thread;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @Author: Xiongyy
 * @Date: 2021/1/3 13:54
 * .............................................
 * 佛祖保佑             永无BUG
 * 计数信号量。从概念上讲，一个信号量维护一组允许。
 * 每个 acquire()块如果必要的许可证前，是可用的，然后把它。
 * 每个 release()添加许可，潜在收购方释放阻塞。
 * 然而，不使用实际允许的对象； Semaphore只是计数的数量和相应的行为。
 */
public class SemaphoreDemo {

    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(3);

        for (int i = 0; i < 6; i++) {
            new Thread(() ->{
                try {
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName() + "得到了一个线程。。。");

                    TimeUnit.SECONDS.sleep(1);

                    semaphore.release();
                } catch (InterruptedException e) {

                }


            }).start();
        }
    }
}
