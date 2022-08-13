package com.examplexyy.demo.concurrent;

/**
 * @Author: Xiongyy
 * @Date: 2021/1/2 17:18
 * .............................................
 * 佛祖保佑             永无BUG
 */
public class TestThread1 extends Thread {

    @Override
   public void run(){
        for (int i = 0; i < 20; i++) {
            System.out.println(Thread.currentThread().getName() + ":测试多线程~~ " + i);
        }
   }

    public static void main(String[] args) {
        TestThread1 testThread1 = new TestThread1();
        testThread1.run(); //这里还是在主线程里面
        testThread1.start(); //这里是开启一个线程,并发执行

        for (int i = 0; i < 20; i++) {
            System.out.println(Thread.currentThread().getName() + ":测试多线程~~ " + i);
        }
    }
}
