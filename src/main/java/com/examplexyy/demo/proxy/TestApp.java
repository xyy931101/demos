package com.examplexyy.demo.proxy;

import com.alibaba.fastjson.JSONObject;
import com.examplexyy.demo.proxy.service.SayService;
import com.examplexyy.demo.proxy.service.impl.SayServiceImpl;

import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @Author: Xiongyy
 * @Date: 2020/12/16 19:46
 * .............................................
 * 佛祖保佑             永无BUG
 */
public class TestApp {
    public static void main(String[] args) throws Exception {
        SayService service = new SayServiceImpl();

        service.sayHello("大帅B！");

        //使用反射机制
        Method sayHello = SayService.class.getMethod("sayHello", String.class);

        Object invoke = sayHello.invoke(service, "熊帅哥！");

        if(invoke instanceof Void){
            System.out.println("没礼貌！！！");
        }

        System.out.println(JSONObject.toJSONString(invoke));

//        Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), )
    }
}
