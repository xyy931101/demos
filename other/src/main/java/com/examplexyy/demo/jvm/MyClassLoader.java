package com.examplexyy.demo.jvm;

import java.net.URL;
import java.net.URLClassLoader;

/**
 * @Author: Xiongyy
 * @Date: 2021/1/18 23:25
 * .............................................
 * 佛祖保佑             永无BUG
 */
public class MyClassLoader extends URLClassLoader {

    public MyClassLoader(URL[] urls, ClassLoader parent) {
        super(urls, parent);
    }
}
