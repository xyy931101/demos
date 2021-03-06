package com.examplexyy.demo.concurrent.atomic;

import lombok.Data;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * @Author: Xiongyy
 * @Date: 2021/1/13 23:34
 * .............................................
 * 佛祖保佑             永无BUG
 */
@Data
public class AtomicDemo {
    private Integer version = 0;

    public static void main(String[] args) {
        AtomicDemo demo = new AtomicDemo();
        AtomicStampedReference<AtomicDemo> atomicDemo = new AtomicStampedReference<AtomicDemo>(demo, 0);

        new Thread(() -> {
            AtomicDemo reference = atomicDemo.getReference();
            reference.setVersion(10);
        });


        AtomicInteger atomicInteger = new AtomicInteger(0);
        atomicInteger.getAndIncrement();
    }
}
