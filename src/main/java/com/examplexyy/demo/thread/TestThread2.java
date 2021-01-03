package com.examplexyy.demo.thread;

/**
 * 多线程下载文件
 * @Author: Xiongyy
 * @Date: 2021/1/2 17:28
 * .............................................
 * 佛祖保佑             永无BUG
 */
public class TestThread2 implements Runnable {


    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {
            System.out.println("Runnable 的run方法。。。。" + i);
        }
    }

    public static void main(String[] args) {
        Thread thread = new Thread(new TestThread2());
        thread.start();

        for (int i = 0; i < 20; i++) {
            System.out.println("main 的run方法。。。。" + i);
        }
    }
}


