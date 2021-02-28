package com.examplexyy.demo.concurrent;

/**
 * @Author: Xiongyy
 * @Date: 2021/1/2 19:55
 * .............................................
 * 佛祖保佑             永无BUG
 *
 *
 * 三个线程ABC，分别打印ABC，ABC按照顺序打印，每个线程都打印10次
 */
public class SaleTicket {

    public static void main(String[] args) throws InterruptedException {
        Ticket ticket = new Ticket();
        new Thread(() ->{
            for (int i = 0; i < 60; i++) {
                ticket.sale();;
            }
        }).start();

        new Thread(() ->{
            for (int i = 0; i < 60; i++) {
                ticket.sale();;
            }
        }).start();

        new Thread(() ->{
            for (int i = 0; i < 60; i++) {
                ticket.sale();;
            }
        }).start();

        SayName sayName = new SayName();


        new Thread(() ->{
            while (sayName.sayCount < 30){

                if((sayName.sayCount % 3) == 0){
                    sayName.sayName();
                }
            }
        }, "A").start();

       new Thread(() ->{
           while (sayName.sayCount < 30) {
               if ((sayName.sayCount % 3) == 1) {
                   sayName.sayName();
               }
           }
        }, "B").start();
       new Thread(() ->{
           while (sayName.sayCount < 30) {
               if ((sayName.sayCount % 3) == 2) {
                   sayName.sayName();
               }
           }
        }, "C").start();

    }
}

class SayName {

    public int sayCount = 0;

    public synchronized void sayName(){
            System.out.println(Thread.currentThread().getName() + sayCount++);
    }

}

class Ticket{
    private int tickCount = 50;

    public synchronized void sale(){
        if (tickCount > 0){
            System.out.println(Thread.currentThread().getName() + "卖出了:" + (tickCount--) + "剩余:" + tickCount);
        }
    }
}
