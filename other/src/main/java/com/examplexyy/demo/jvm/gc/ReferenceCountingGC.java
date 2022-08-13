package com.examplexyy.demo.jvm.gc;

/**
 * 引用计数算法测试
 * @Author: Xiongyy
 * @Date: 2021/1/26 22:22
 * .............................................
 * 佛祖保佑             永无BUG
 */
public class ReferenceCountingGC {
    private Object instance = null;
    private static final int _1MB = 1024* 1024;

    /**
     * f方便查看GC日志看清楚是否又被回收过
     */
    private byte[] bugSize = new byte[ 2 * _1MB];

    public static void main(String[] args) {

        ReferenceCountingGC objA = new ReferenceCountingGC();
        ReferenceCountingGC objB = new ReferenceCountingGC();

        objB.instance = objA;
        objA.instance = objB;

        objA = null;
        objB = null;

        System.gc();
    }

}
