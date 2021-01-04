package com.examplexyy.demo.thread.callable;

import java.util.concurrent.*;

/**
 * @Author: Xiongyy
 * @Date: 2021/1/3 15:42
 * .............................................
 * 佛祖保佑             永无BUG
 */
public class ThreadPoolExecutorDemo {

    public static void main(String[] args) {
        //电脑的CPU的数量(CPU密集型)
        int processors = Runtime.getRuntime().availableProcessors();

        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(2,
                processors,
                2,
                TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(3),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.CallerRunsPolicy());
        for (int i = 0; i < 9; i++) {
            poolExecutor.execute(() ->{
                System.out.println("这是执行一个任务线程。。。" + Thread.currentThread().getName());
            });
        }

    }

}
