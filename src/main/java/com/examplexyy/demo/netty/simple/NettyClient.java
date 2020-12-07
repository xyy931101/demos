package com.examplexyy.demo.netty.simple;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * @Author: Xiongyy
 * @Date: 2020/12/7 22:53
 * .............................................
 * 佛祖保佑             永无BUG
 */
public class NettyClient {

    public static void main(String[] args) throws InterruptedException {
        EventLoopGroup loopGroup = new NioEventLoopGroup();

        //创建客户端启动对象(不是ServerBootStrap)
        Bootstrap bootstrap = new Bootstrap();

        try {
            bootstrap.group(loopGroup)//设置线程组
                    .channel(NioSocketChannel.class)//设置客户端通道的实现类
                    .handler(new ChannelInitializer<SocketChannel>() {

                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            socketChannel.pipeline().addLast(new NettyClientHandler());//加入自己的处理器
                        }
                    });

            System.out.println("客户端is ok~~~~");

            //启动客户端取连接服务器端(涉及到netty的异步模型)
            ChannelFuture channelFuture = bootstrap.connect("127.0.0.1", 6668).sync();
            //给关闭通道进行监听
            channelFuture.channel().close().sync();
        } finally {
            loopGroup.shutdownGracefully();
        }


    }
}
