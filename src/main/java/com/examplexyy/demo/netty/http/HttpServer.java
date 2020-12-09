package com.examplexyy.demo.netty.http;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @Author: Xiongyy
 * @Date: 2020/12/9 21:38
 * .............................................
 * 佛祖保佑             永无BUG
 */
public class HttpServer {

    public static void main(String[] args) throws InterruptedException {
        // 1.创建两个线程组boosGroup workerGroup
        //2.bossGroup只处理连接请求，真正的客户端处理会交给workerGroup完成
        //3.两个都是无限循环
        EventLoopGroup boosGroup = new NioEventLoopGroup();

        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            //创建服务器端启动的对象，配置启动参数
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(boosGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new HttpInitializer());

            ChannelFuture channelFuture = bootstrap.bind(668).sync();

            channelFuture.channel().closeFuture().sync();

        }finally {
            boosGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}
