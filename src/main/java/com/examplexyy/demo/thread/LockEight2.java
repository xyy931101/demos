package com.examplexyy.demo.thread;

import java.util.concurrent.TimeUnit;

/**
 * @Author: Xiongyy
 * @Date: 2021/1/3 11:10
 * .............................................
 * 佛祖保佑             永无BUG
 */
public class LockEight2 {

    public static void main(String[] args) throws InterruptedException {
        Phone phone = new Phone();
        new Thread(() ->{phone.sendMsg();}, "A").start();
        TimeUnit.SECONDS.sleep(1);
        new Thread(() ->{phone.call();}, "B").start();
    }
}

class Phone2{

    //synchronized 的锁的对象是方法的调用者
    //谁先调用谁先执行
    public synchronized void sendMsg(){
        try {
            TimeUnit.SECONDS.sleep(4);
        } catch (InterruptedException e) {

        }
        System.out.println("发短信！！！");
    }

    public synchronized void call(){
        System.out.println("打电话！！！");
    }
}
