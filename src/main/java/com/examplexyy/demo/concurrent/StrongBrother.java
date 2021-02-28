package com.examplexyy.demo.concurrent;

/**
 * @Author: Xiongyy
 * @Date: 2021/1/2 22:29
 * .............................................
 * 佛祖保佑             永无BUG
 */
public class StrongBrother {
    public static void main(String[] args) {


        Say say = new Say();
        new Thread(() ->{
            while (say.sayCount < 30) {
                while(say.sayCount % 3 == 0){
                    say.say();
                }
            }
        }, "A").start();

        new Thread(() ->{
            while (say.sayCount < 30){
                while(say.sayCount % 3 == 1){

                    say.say();
                }
            }
        }, "B").start();

        new Thread(() ->{
            while (say.sayCount < 30){
                while(say.sayCount % 3 == 2){

                    say.say();
                }
            }
        }, "C").start();


    }
}


class Say{
    public int sayCount = 0;

    public synchronized void say() {

        System.out.println(Thread.currentThread().getName() + " 的count:" + sayCount++);
    }
}