package com.examplexyy.demo.thread.callable;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.LockSupport;

/**
 * @Author: Xiongyy
 * @Date: 2021/2/19 20:04
 * .............................................
 * 佛祖保佑             永无BUG
 */
public class SemaphoreDemo {

    private static Semaphore A = new Semaphore(1);
    private static Semaphore B = new Semaphore(0);
    private static Semaphore C = new Semaphore(0);

    static class ThreadA extends Thread {

        @Override
        public void run() {
            try {
                for (int i = 0; i < 10; i++) {
                    A.acquire();
                    System.out.print("A");
                    B.release();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    static class ThreadB extends Thread {

        @Override
        public void run() {
            try {
                for (int i = 0; i < 10; i++) {
                    B.acquire();
                    System.out.print("B");
                    C.release();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    static class ThreadC extends Thread {

        @Override
        public void run() {
            try {
                for (int i = 0; i < 10; i++) {
                    C.acquire();
                    System.out.println("C");
                    A.release();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    public static void main(String[] args) throws InterruptedException {
        new ThreadA().start();
        new ThreadB().start();
        new ThreadC().start();
    }

}
