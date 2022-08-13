package com.examplexyy.demo.concurrent;

import java.util.concurrent.Phaser;
import java.util.concurrent.Semaphore;

/**
 * @Author: Xiongyy
 * @Date: 2021/1/10 14:14
 * .............................................
 * 佛祖保佑             永无BUG
 *
 * 线程A/B/C依次点打印10次
 */
public class StrongBrother3 {
    public static void main(String[] args) {
//        Unsafe.getUnsafe().compareAndSwapObject
        Semaphore aSemaphore = new Semaphore(1);
        Semaphore bSemaphore = new Semaphore(0);
        Semaphore cSemaphore = new Semaphore(0);

        new Thread(() ->{
            for (int i = 0; i < 10; i++) {
                try {
                    aSemaphore.acquire();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                }
                System.out.print(Thread.currentThread().getName());
                bSemaphore.release();
            }
        }, "A").start();

        new Thread(() ->{
            for (int i = 0; i < 10; i++) {
                try {
                    bSemaphore.acquire();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                }
                System.out.print(Thread.currentThread().getName());
                cSemaphore.release();
            }
        }, "B").start();;

        new Thread(() ->{
            for (int i = 0; i < 10; i++) {
                try {
                    cSemaphore.acquire();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                }
                System.out.println(Thread.currentThread().getName());
                aSemaphore.release();
            }
        }, "C").start();;


    }

}
