package com.examplexyy.demo.Util;

import org.apache.commons.codec.binary.Base64;

/**
 * @Author: Xiongyy
 * @Date: 2020/4/4 12:31
 * .............................................
 * 佛祖保佑             永无BUG
 * 佛曰:
 * 写字楼里写字间，写字间里程序员；
 * 程序人员写程序，又拿程序换酒钱。
 * 酒醒只在网上坐，酒醉还来网下眠；
 * 酒醉酒醒日复日，网上网下年复年。
 * 但愿老死电脑间，不愿鞠躬老板前；
 * 奔驰宝马贵者趣，公交自行程序员。
 * 别人笑我忒疯癫，我笑自己命太贱；
 * 不见满街漂亮妹，哪个归得程序员？
 */
public class Base64Utile {

    public static String EncodeBase64(byte[] data) {
        Base64 en = new Base64();
        String str = en.encodeBase64String(data);
        return str;
    }

    /*
     *解密过程
     */
    public static String DecodeBase64(String str) {
        Base64 de = new Base64();
        byte[] ResultBase = de.decodeBase64(str);
        String str2 = new String(ResultBase);
        return str2;
    }
}
