package com.examplexyy.demo.zookeeper;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.util.concurrent.CountDownLatch;

/**
 * Zookeeper的一些demo
 * @Author: Xiongyy
 * @Date: 2021/2/17 10:32
 * .............................................
 * 佛祖保佑             永无BUG
 */
public class ZookeeperDemos {

    public static void main(String[] args) {
        try {
            CountDownLatch countDownLatch = new CountDownLatch(1);

            ZooKeeper zooKeeper = new ZooKeeper("8.129.216.97:2181", 5000, watchedEvent -> {
                System.out.println(watchedEvent.getPath() + ":" +  watchedEvent.getWrapper());
                if(watchedEvent.getState().equals(Watcher.Event.KeeperState.SyncConnected)){
                    countDownLatch.countDown();
                    System.out.println("连接创建成功");
                }
            });

            //主线程等待连接对象创建成功
            countDownLatch.await();

            System.out.println(zooKeeper.getSessionId());

            //释放资源
            zooKeeper.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
