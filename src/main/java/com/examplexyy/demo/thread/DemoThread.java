package com.examplexyy.demo.thread;

/**
 * @author xiongyayun
 * @date 2021/5/15 16:23
 */
public class DemoThread extends Thread{

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + "my thread.....");
    }


    public static void main(String[] args) {
        new DemoThread().start();
    }
}
