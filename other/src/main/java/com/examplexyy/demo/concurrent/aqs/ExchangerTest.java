package com.examplexyy.demo.concurrent.aqs;

import java.util.concurrent.Exchanger;

/**
 * @Author: Xiongyy
 * @Date: 2021/2/17 20:33
 * .............................................
 * 佛祖保佑             永无BUG
 */
class ExchangerTest extends Thread{

    private Exchanger<String> exchanger;
    private String string;
    private String threadName;

    public ExchangerTest(Exchanger<String> exchanger, String string,
                         String threadName) {
        this.exchanger = exchanger;
        this.string = string;
        this.threadName = threadName;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < 10; i++) {
//                while (threadName.equals(string)){
                    System.out.println(threadName + ": " + exchanger.exchange(string));
//                }
            }
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Exchanger<String> exchanger = new Exchanger<>();
        ExchangerTest A = new ExchangerTest(exchanger, "B",
                "A");
        ExchangerTest B = new ExchangerTest(exchanger, "C",
                "B");

        ExchangerTest C = new ExchangerTest(exchanger, "A",
                "C");
        A.start();
        B.start();
        C.start();
    }
}

