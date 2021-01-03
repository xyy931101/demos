package com.examplexyy.demo.thread.pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author: Xiongyy
 * @Date: 2021/1/3 15:12
 * .............................................
 * 佛祖保佑             永无BUG
 */
public class ExecutorsDemo {
    public static void main(String[] args) {
//        ExecutorService executor1 = Executors.newSingleThreadExecutor();
//        ExecutorService executor2 = Executors.newScheduledThreadPool(5);
        ExecutorService executor = Executors.newCachedThreadPool();
        try {

            for (int i = 0; i < 10; i++) {
                executor.execute(() ->{
                    System.out.println(Thread.currentThread().getName() + "ok");
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            executor.shutdown();
        }
    }
}
