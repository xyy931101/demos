package com.examplexyy.demo.concurrent;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @Author: Xiongyy
 * @Date: 2021/1/4 22:20
 * .............................................
 * 佛祖保佑             永无BUG
 */
public class ReentrantLockDemo {


    public static void main(String[] args) throws InterruptedException {
        Semaphore semaphore = new Semaphore(3);
        semaphore.acquire();
        PhoneLock phoneLock = new PhoneLock();

        new Thread(() ->{
            phoneLock.sendMsg();
        }, "A").start();

        new Thread(() ->{
            phoneLock.call();
        }, "B").start();
    }
}


class PhoneLock{


    public synchronized void sendMsg(){
        System.out.println("send msg..." + Thread.currentThread().getName());
        try {
            TimeUnit.SECONDS.sleep(2);
            call();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public synchronized void call(){
        System.out.println("call ..." + Thread.currentThread().getName());
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
