package com.examplexyy.demo.netty.protocoltcp;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @Author: Xiongyy
 * @Date: 2020/12/7 21:50
 * .............................................
 * 佛祖保佑             永无BUG
 */
public class ProtocolServer {

    public static void main(String[] args) throws InterruptedException {
        //创建BossGroup及workerGroup
        /**
         * 1.创建两个线程组boosGroup workerGroup
         * 2.bossGroup只处理连接请求，真正的客户端处理会交给workerGroup完成
         * 3.两个都是无限循环
         */
        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class).
                    childHandler(new ProtocolServerInitializer());

            ChannelFuture channelFuture = serverBootstrap.bind(8899).sync();
            channelFuture.channel().closeFuture().sync();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}
