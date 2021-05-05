package com.examplexyy.demo.concurrent.aqs;

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

            for (int i = 0; i <10; i++) {
                System.out.print("A");
                LockSupport.unpark(B);
                LockSupport.park();//阻塞当前线程
            }
        });

        B = new Thread(() ->{
            for (int i = 0; i <10; i++) {
                LockSupport.park();//阻塞当前线程
                System.out.print("B");
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

    public void testLock(){

    }
}
