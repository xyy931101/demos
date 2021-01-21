package com.examplexyy.demo.thread.single;

import java.io.*;

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

    public static void main(String[] args) throws IllegalAccessException, InstantiationException, CloneNotSupportedException, IOException, ClassNotFoundException {
        /**
         * 多线程获取
         */
        for (int i = 0; i < 10; i++) {
            new Thread(() ->{
                Full instance = Full.getInstance();
                System.out.println(instance);
            }).start();

        }

        /**
         * 反杀获取
         */
        try{
            Class<Full> clazz = Full.class;
            Full full = clazz.newInstance();
            System.out.println(full);
        }catch (Exception e){
            System.err.println(e);
        }

        /**
         * 反射获取
         */
        try {

            Full clone = Full.getInstance().clone();
            Full clone1 = clone.clone();
            System.out.println(clone1);
        }catch (Exception e){
            System.err.println(e);
        }

        //通过序列化，反序列化获取
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        oos.writeObject(Full.getInstance());
        ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());
        ObjectInputStream ois = new ObjectInputStream(bis);
        Full serialize = (Full) ois.readObject();
        if (ois != null)    {ois.close();}
        if (bis != null) {bis.close();}
        if (oos != null) {oos.close();}
        if (bos != null) {bos.close();}
        System.out.println(serialize);


    }
}