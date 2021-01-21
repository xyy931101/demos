package com.examplexyy.demo.thread.pool;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Author: Xiongyy
 * @Date: 2021/1/17 11:41
 * .............................................
 * 佛祖保佑             永无BUG
 */
public class ScheduledTest {
    private static ScheduledExecutorService executor = new ScheduledThreadPoolExecutor(2);

    public static void main(String[] args) {
        executor.schedule(() -> System.out.println("线程名:" + Thread.currentThread().getName()), 1, TimeUnit.SECONDS);
        executor.scheduleAtFixedRate(() -> System.out.println("线程名:" + Thread.currentThread().getName()), 1,1, TimeUnit.SECONDS);
    }
}
