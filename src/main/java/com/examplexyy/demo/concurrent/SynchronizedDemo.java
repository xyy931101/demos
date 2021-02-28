package com.examplexyy.demo.concurrent;

import java.util.concurrent.TimeUnit;

/**
 * @Author: Xiongyy
 * @Date: 2021/2/23 23:02
 * .............................................
 * 佛祖保佑             永无BUG
 */
public class SynchronizedDemo {

    synchronized void say(){
        int count = 0;
        System.out.println(Thread.currentThread().getName() + "==========");
        while (true){
            count ++;
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if(count > 5){
                int i = 1/0;
                System.out.println(i);
            }
        }
    }

    synchronized void say1(){
        System.out.println(Thread.currentThread().getName() + "==============");
    }

    public static void main(String[] args) {
        SynchronizedDemo demo = new SynchronizedDemo();
        new Thread(() ->{
           demo.say();
        }, "say   say").start();

        new Thread(() ->{
           demo.say1();
        }, "say1").start();;
    }
}
