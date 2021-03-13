package com.examplexyy.demo.concurrent.pool;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author: Xiongyy
 * @Date: 2021/1/3 15:12
 * .............................................
 * 佛祖保佑             永无BUG
 */
public class ExecutorsDemo {


    public static void main(String[] args) {

        ExecutorService executorService = Executors.newFixedThreadPool(12);
        ExecutorService executor1 = Executors.newSingleThreadExecutor();
        ExecutorService executor2 = Executors.newScheduledThreadPool(5);
        ExecutorService executor = Executors.newCachedThreadPool();
        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(2, 3, 1, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(3), new MyThreadFactory(), new ThreadPoolExecutor.AbortPolicy());
        try {
            poolExecutor.submit(() -> System.out.println(Thread.currentThread().getName() + "ok"));
            for (int i = 0; i < 5; i++) {
                poolExecutor.execute(() ->{
                    System.out.println(poolExecutor.getTaskCount());
                    System.out.println(Thread.currentThread().getName() + "ok");
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            poolExecutor.shutdown();
        }
    }
}

class MyThreadFactory implements ThreadFactory{
    private static final AtomicInteger poolNumber = new AtomicInteger(1);

    @Override
    public Thread newThread(Runnable r) {
        Thread t = new Thread(r, "xyy的线程工程" + poolNumber.getAndIncrement());
        return t;
    }
}
