package com.examplexyy.demo.spring;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * @Author: Xiongyy
 * @Date: 2020/12/22 22:56
 * .............................................
 * 佛祖保佑             永无BUG
 */
@Component
public class BookY {
    private BookX x;

    public BookX getX() {
        return x;
    }

    public void setX(BookX x) {
        this.x = x;
    }

    @Async
    public void get() {
        System.out.println(Thread.currentThread().getName());
        this.x = x;
    }
}
