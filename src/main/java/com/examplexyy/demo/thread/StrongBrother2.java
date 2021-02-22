package com.examplexyy.demo.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author: Xiongyy
 * @Date: 2021/1/3 10:50
 * .............................................
 * 佛祖保佑             永无BUG
 * 三个线程ABC，分别打印ABC，ABC按照顺序打印，每个线程都打印10次
 */
public class StrongBrother2 {

    public static void main(String[] args) {
        Data data = new Data();
        new Thread(() ->{
            for (int i = 0; i < 10; i++) {
                data.sayA();
            }
        }, "A").start();

        new Thread(() ->{
            for (int i = 0; i < 10; i++) {
                data.sayB();
            }
        }, "B").start();

        new Thread(() ->{
            for (int i = 0; i < 10; i++) {
                data.sayC();
            }
        }, "C").start();


    }

}

/**
 * 资源类
 */
class Data{

    private Lock lock = new ReentrantLock();
    private Condition conditionA = lock.newCondition();
    private Condition conditionB = lock.newCondition();
    private Condition conditionC= lock.newCondition();
    private Integer number = 1;

    public void sayA(){
        lock.lock();
        try {
            while (number != 1){
                //等待
                conditionA.await();
            }
            System.out.print(Thread.currentThread().getName() );
            number = 2;
            conditionB.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void sayB(){
        lock.lock();
        try {
            while (number != 2){
                //等待
                conditionB.await();
            }
            System.out.print(Thread.currentThread().getName());
            number = 3;
            conditionC.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }

    public void sayC(){
        lock.lock();
        try {
            while (number != 3){
                //等待
                conditionC.await();
            }
            System.out.println(Thread.currentThread().getName());
            number = 1;
            conditionA.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

}