package com.examplexyy.demo.concurrent.callable;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @Author: Xiongyy
 * @Date: 2021/1/3 13:12
 * .............................................
 * 佛祖保佑             永无BUG
 */
public class CallableTest {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        MyCallable myCallable = new MyCallable();
        FutureTask futureTask = new FutureTask(myCallable);
        new Thread(futureTask, "A").start();

        System.out.println(futureTask.get());//get方法可能会产生异常
    }
}

class MyCallable implements Callable<String>{

    @Override
    public String call() throws Exception {
        System.out.println("这是在调用call");
        return "xyy的callable";
    }
}