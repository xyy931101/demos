package com.examplexyy.demo.limit.google;

import com.google.common.util.concurrent.RateLimiter;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @Author: Xiongyy
 * @Date: 2021/3/10 22:41
 * .............................................
 * 佛祖保佑             永无BUG
 */
public class RateLimiterDemo {
    static RateLimiter rateLimiter = RateLimiter.create(5.0);


    public static void main(String[] args) {


        rateLimiter.tryAcquire();

        Semaphore semaphore = new Semaphore(3);
        try {
            boolean b = semaphore.tryAcquire(3, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();

        }
    }
}
