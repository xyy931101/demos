package com.examplexyy.demo.redis;

import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

import java.util.concurrent.TimeUnit;

/**
 * @Author: Xiongyy
 * @Date: 2021/4/15 23:42
 * .............................................
 * 佛祖保佑             永无BUG
 */
public class RedissonDemo {

    public static void main(String[] args) {
        // 1. Create config object
        Config config = new Config();
        config.useClusterServers()
                // use "rediss://" for SSL connection
                .addNodeAddress("redis://127.0.0.1:7181");

        // or read config from file
//        config = Config.fromYAML(new File("config-file.yaml"));
        // 2. Create Redisson instance

        // Sync and Async API
        RedissonClient redisson = Redisson.create(config);

        RLock lock = redisson.getLock("myLock");

        lock.lock(1, TimeUnit.SECONDS);
    }
}
