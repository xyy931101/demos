package com.examplexyy.demo.netty.tcp;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @Author: Xiongyy
 * @Date: 2020/12/7 21:50
 * .............................................
 * 佛祖保佑             永无BUG
 */
public class MyServer {

    public static void main(String[] args) throws InterruptedException {
        //创建BossGroup及workerGroup
        /**
         * 1.创建两个线程组boosGroup workerGroup
         * 2.bossGroup只处理连接请求，真正的客户端处理会交给workerGroup完成
         * 3.两个都是无限循环
         */
        EventLoopGroup boosGroup = new NioEventLoopGroup();

        EventLoopGroup workerGroup = new NioEventLoopGroup();

        //创建服务器端启动的对象，配置启动参数
        ServerBootstrap bootstrap = new ServerBootstrap();
        //使用链式编程进行设置
        try {
            bootstrap.group(boosGroup, workerGroup)//设置两个线程组
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new MyServerInitializer());//给我们的workerGroup的EventLoop设置处理器

            //绑定一个端口,并且生成了一个channelFuture对象
            ChannelFuture channelFuture = bootstrap.bind(6668).sync();

            //对关闭通道进行监听
            channelFuture.channel().closeFuture().sync();
        } finally {
            boosGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}
