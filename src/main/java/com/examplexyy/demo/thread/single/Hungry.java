package com.examplexyy.demo.thread.single;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author: Xiongyy
 * @Date: 2021/1/4 20:14
 * .............................................
 * 佛祖保佑             永无BUG
 *
 */
public class Hungry {

    private Hungry(){
        AtomicInteger atomicInteger = new AtomicInteger();
        atomicInteger.getAndIncrement();

    }

    public final static Hungry HUNGRY  = new Hungry();

}
