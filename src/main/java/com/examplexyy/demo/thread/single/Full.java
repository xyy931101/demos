package com.examplexyy.demo.thread.single;

/**
 * @Author: Xiongyy
 * @Date: 2021/1/10 23:24
 * .............................................
 * 佛祖保佑             永无BUG
 */
public class Full {

    /**
     * 使用volatile关键字
     * 1.禁止指令重排， 2.保证变量可见性
     */
    private volatile static  Full instance;

    /**
     * 构造方法私有化,保证单例
     */
    private Full(){}

    public static  Full getInstance(){
        //使用DCL(double check lock)
        if (instance == null){
            synchronized (Full.class){
               if (instance == null){
                   instance = new Full();
               }
            }
        }
        return instance;
    }
}
