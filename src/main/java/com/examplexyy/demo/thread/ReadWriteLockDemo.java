package com.examplexyy.demo.thread;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 独占锁(写)  共享锁(读)
 * @Author: Xiongyy
 * @Date: 2021/1/3 14:07
 * .............................................
 * 佛祖保佑             永无BUG
 * 一个 ReadWriteLock保持一对一联系 locks，只读操作和书写。
 * 读的 read lock可能被多个线程同时举行的读者，只要没有作家。
 * write lock是独家的。
 */
public class ReadWriteLockDemo {

    public static void main(String[] args) {
        MyCache myCache = new MyCache();
        for (int i = 0; i < 5; i++) {
           final int temp = i;
           new Thread(() ->{
               myCache.put(String.valueOf(temp), temp);
           }).start();
        }

        for (int i = 0; i < 5; i++) {
            final int temp = i;
            new Thread(() ->{
                myCache.get(String.valueOf(temp));
            }).start();
        }
    }
}

class MyCache{

    ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    private volatile Map<String, Object> map = new HashMap<>();

    //存
    public void put(String key, Object value){
        readWriteLock.writeLock().lock();
        System.out.println(Thread.currentThread().getName() + "这是在写入：" + key);
        map.put(key, value);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + "写入OK！");

        readWriteLock.writeLock().unlock();
    }

    //取
    public void get(String key){
        readWriteLock.readLock().lock();
        System.out.println(Thread.currentThread().getName() + "这是在读！！");
        map.get(key);
        System.out.println(Thread.currentThread().getName() + "读完了！！！");
        readWriteLock.readLock().unlock();
    }
}