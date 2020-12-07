package com.examplexyy.demo.netty.simple;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @Author: Xiongyy
 * @Date: 2020/12/7 21:50
 * .............................................
 * 佛祖保佑             永无BUG
 */
public class NettyServer {

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
                    .option(ChannelOption.SO_BACKLOG, 128)//设置线程队列等待连接的个数
                    .childOption(ChannelOption.SO_KEEPALIVE, true)//设置保持活动连接状态
                    .childHandler(new ChannelInitializer<SocketChannel>(){//创建一个通道初始化对象(匿名对象)
                        //向pipeline设置处理器
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            socketChannel.pipeline().addLast(new NettyServerHandler());
                        }
                    });//给我们的workerGroup的EventLoop设置处理器

            System.out.println("服务器准备好了。。。");

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
