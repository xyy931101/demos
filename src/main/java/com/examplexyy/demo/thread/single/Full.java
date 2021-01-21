package com.examplexyy.demo.thread.single;

import java.io.Serializable;

/**
 * @Author: Xiongyy
 * @Date: 2021/1/10 23:24
 * .............................................
 * 佛祖保佑             永无BUG
 */
public class Full implements Serializable,Cloneable{

    /**
     * 使用volatile关键字
     * 1.禁止指令重排， 2.保证变量可见性
     */
    private volatile static  Full INSTANCE;
    /**
     * 默认是第一次创建
     */
    private static boolean isFirstCreate = true;

    /**
     * 构造方法私有化,保证单例
     */
    private Full() {
        if (isFirstCreate){
            synchronized (Full.class) {
                if (isFirstCreate) {
                    isFirstCreate = false;
                }
            }
        }else{
            throw new RuntimeException("已经实例化过了！！！");
        }
    }

    public static  Full getInstance(){
        //使用DCL(double check lock)
        if (INSTANCE == null){
            synchronized (Full.class){
               if (INSTANCE == null){
                   INSTANCE = new Full();
               }
            }
        }
        return INSTANCE;
    }

    @Override
    protected Full clone() throws CloneNotSupportedException {
        return INSTANCE;
    }

    protected Object readResolve() {
        return INSTANCE;
    }
}