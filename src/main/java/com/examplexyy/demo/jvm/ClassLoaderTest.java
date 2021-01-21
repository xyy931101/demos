package com.examplexyy.demo.jvm;

import java.net.URLClassLoader;

/**
 * @Author: Xiongyy
 * @Date: 2021/1/18 22:47
 * .............................................
 * 佛祖保佑             永无BUG
 * 类加载是是组合关系,不是父子关系
 */
public class ClassLoaderTest {
    public static void main(String[] args) {

        //系统类加载器
        ClassLoader systemClassLoader = ClassLoader.getSystemClassLoader();
        System.out.println(systemClassLoader);

        //拓展类加载器
        ClassLoader extClassLoader = systemClassLoader.getParent();
        System.out.println(extClassLoader);

        //bootStrap(引导类加载器)加载器,最顶层,获取不到
        ClassLoader bootStrap = extClassLoader.getParent();
        System.out.println(bootStrap);

        //对于用户自定义来说,使用系统类加载器进行加载
        ClassLoader classLoader = ClassLoaderTest.class.getClassLoader();
        System.out.println(classLoader);

        ClassLoader stringClassLoader = String.class.getClassLoader();
        System.out.println(stringClassLoader);


    }
}
