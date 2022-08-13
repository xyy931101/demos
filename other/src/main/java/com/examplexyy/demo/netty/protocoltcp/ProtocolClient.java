package com.examplexyy.demo.netty.protocoltcp;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * @Author: Xiongyy
 * @Date: 2020/12/7 22:53
 * .............................................
 * 佛祖保佑             永无BUG
 */
public class ProtocolClient {

    public static void main(String[] args) throws InterruptedException {
        EventLoopGroup loopGroup = new NioEventLoopGroup();

        //创建客户端启动对象(不是ServerBootStrap)
        Bootstrap bootstrap = new Bootstrap();

        try {
            //设置客户端通道的实现类
            bootstrap.group(loopGroup)//设置线程组
                    .channel(NioSocketChannel.class)//设置客户端通道的实现类
                    .handler(new ProtocolClientInitializer());

            System.out.println("客户端is ok~~~~");

            //启动客户端取连接服务器端(涉及到netty的异步模型)
            ChannelFuture channelFuture = bootstrap.connect("127.0.0.1", 8899).sync();
            System.out.println("连接服务器:"+channelFuture.channel().remoteAddress()+",本地地址:"+channelFuture.channel().localAddress());
            channelFuture.channel().closeFuture().sync();//等待客户端关闭连接
        } finally {
            loopGroup.shutdownGracefully();
        }


    }
}
