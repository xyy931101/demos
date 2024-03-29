package com.examplexyy.demo.redis;

import com.alibaba.fastjson.JSON;
import redis.clients.jedis.Jedis;

import java.util.List;
import java.util.Map;

/**
 * @Author: Xiongyy
 * @Date: 2020/4/19 23:31
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
public class JedisDemo {

    public Jedis getJedis(){
        Jedis jedis = new Jedis("106.52.223.163", 6379);
        jedis.auth("foobared");
        return jedis;
    }

    public void strTest(){
        Jedis jedis = getJedis();
        jedis.set("xyy1", "vxyy1");
        System.out.println(jedis.get("xyy1"));
        jedis.mset("xyy2", "vxyy2", "xyy3", "vxyy3");
        List<String> values = jedis.mget("xyy1", "xyy2", "xyy3");
        System.out.println(JSON.toJSONString(values));

        jedis.incr("xyy1:shuaiqizhi");
        jedis.setnx("xxx", "9999");
        Double d = 0.0;
        System.out.println(jedis.get("xyy1:shuaiqizhi"));
    }


    public void hashTest(){
        Jedis jedis = getJedis();
        System.out.println(jedis.hget("book:1", "name"));
        Map<String, String> book1 = jedis.hgetAll("book:1");
        System.out.println(JSON.toJSONString(book1));
    }

    public void listTest(){
        Jedis jedis = getJedis();
        String jobs = jedis.lpop("jobs");
        System.out.println(jobs);
    }

    public static void main(String[] args) {
        JedisDemo demo = new JedisDemo();


        demo.strTest();
        demo.hashTest();
    }
}
