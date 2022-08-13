package com.examplexyy.demo.interview;

import java.util.concurrent.Semaphore;

/**
 * @Author: Xiongyy
 * @Date: 2021/4/9 21:36
 * .............................................
 * 佛祖保佑             永无BUG
 */
public class Ali0410 {

    public static void main1(String[] args) throws Exception{


        Semaphore xSemaphore = new Semaphore(1);
        Semaphore ySemaphore = new Semaphore(0);
        Semaphore zSemaphore = new Semaphore(0);

        Thread XThread = new Thread(() ->{
            for(int i = 0; i < 10; i++){
                try {
                    xSemaphore.acquire();
                } catch (InterruptedException e) {
//                    throw new RuntimeException(e.getMessage());
                }
                System.out.print("X");
                ySemaphore.release();
            }
        }, "X");

        Thread YThread = new Thread(() ->{
            for(int i = 0; i < 10; i++){
                try{
                    ySemaphore.acquire();
                }catch(Exception e){

                }
                System.out.print("Y");
                zSemaphore.release();
            }
        }, "Y");

        Thread ZThread = new Thread(() ->{
            for(int i = 0; i < 10; i++){
                try{
                    zSemaphore.acquire();
                }catch(Exception e){
                    System.out.println("被打断");
                }

                System.out.println("Z");
                xSemaphore.release();
            }
        }, "Z");

        XThread.start();
        YThread.start();
        ZThread.start();

        ZThread.interrupt();


        System.out.println(process(4));
    }

    private static int process(int n){
        if(n < 1){
            return 0;
        }
        int[] res = new int[n + 1];
        res[1] = 1;
        res[2] = 2;
        res[3] = 3;
        for(int i = 4; i <= n; i++){
            res[i] = res[i -1] + res[i - 2] + res[i - 3];
        }
        return res[n];
    }


    public static void main(String[] args) {
        Semaphore xSemaphore = new Semaphore(1);
        Thread XThread = new Thread(() ->{
            try {
                xSemaphore.acquire();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("xxxxx");
        }, "X");


        XThread.start();
        XThread.interrupt();
    }
}
