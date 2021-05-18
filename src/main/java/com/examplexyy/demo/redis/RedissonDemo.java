package com.examplexyy.demo.redis;

import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: Xiongyy
 * @Date: 2021/4/15 23:42
 * .............................................
 * 佛祖保佑             永无BUG
 */

@Configuration
public class RedissonDemo {

    @Bean
     public RedissonClient redissonClient() {
        Config config = new Config();
         config.useClusterServers()
                 .setScanInterval(2000)
                 .addNodeAddress("redis://10.0.29.30:6379", "redis://10.0.29.95:6379")
                 .addNodeAddress("redis://10.0.29.205:6379");
        RedissonClient redisson = Redisson.create(config);

         return redisson;
     }

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

        lock.lock();
            //这里是业务参数
        lock.unlock();
    }
}
