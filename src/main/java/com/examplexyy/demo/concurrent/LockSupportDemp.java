package com.examplexyy.demo.concurrent;

import java.util.concurrent.locks.LockSupport;

/**
 * @Author: Xiongyy
 * @Date: 2021/1/14 0:04
 * .............................................
 * 佛祖保佑             永无BUG
 */
public class LockSupportDemp {
    static Thread A = null;
    static Thread B = null;
    static Thread C = null;

    public static void main(String[] args) {


        A = new Thread(() ->{
            try {
                Thread.sleep(7000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            for (int i = 0; i <10; i++) {
                System.out.println("A" + i);
                LockSupport.unpark(B);
                LockSupport.park();//阻塞当前线程
            }
        });

        B = new Thread(() ->{
            for (int i = 0; i <10; i++) {
                LockSupport.park();//阻塞当前线程
                System.out.println("B" + i);
                LockSupport.unpark(C);
            }
        });
        C = new Thread(() ->{
            for (int i = 0; i <10; i++) {
                LockSupport.park();//阻塞当前线程
                System.out.println("C" + i);
                LockSupport.unpark(A);
            }
        });

        A.start();
        B.start();
        C.start();
    }
}
