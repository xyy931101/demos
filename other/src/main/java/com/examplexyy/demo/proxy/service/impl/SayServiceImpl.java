package com.examplexyy.demo.proxy.service.impl;

import com.examplexyy.demo.proxy.service.SayService;

/**
 * @Author: Xiongyy
 * @Date: 2020/12/16 19:46
 * .............................................
 * 佛祖保佑             永无BUG
 */
public class SayServiceImpl implements SayService {
    @Override
    public String sayHello(String msg) {
        System.out.println("你好啊:" + msg);
        return "i am xyy and you ?";
    }
}
