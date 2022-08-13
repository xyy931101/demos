package com.examplexyy.demo.netty.groupchart;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

import java.util.Scanner;

/**
 * @Author: Xiongyy
 * @Date: 2020/12/10 23:51
 * .............................................
 * 佛祖保佑             永无BUG
 */
public class GroupChartClient {
    private static final String HOST = "127.0.0.1";
    private static final int PORT = 7000;

    public void run() throws InterruptedException {
        EventLoopGroup eventLoopGroup = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap();

        try {
            bootstrap.group(eventLoopGroup)
                    .channel(NioSocketChannel.class)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            ChannelPipeline pipeline = socketChannel.pipeline();
                            //加入编码器
                            pipeline.addLast("decoder", new StringDecoder());
                            //加入编码器
                            pipeline.addLast("encoder", new StringEncoder());
                            //加入自定义的处理器
                            pipeline.addLast(new GroupChartClientHandler());
                        }
                    });
            ChannelFuture channelFuture = bootstrap.connect(HOST, PORT).sync();
            Channel channel = channelFuture.channel();
            System.out.println("我是真的是大帅比。。。。哈哈哈。。。" + channel.localAddress());

            //创建一个扫描器
            Scanner scanner = new Scanner(System.in);
            while (scanner.hasNextLine()){
                String msg = scanner.nextLine();
                channel.writeAndFlush(msg + "\r\n");
            }
        } finally {
            eventLoopGroup.shutdownGracefully();
        }

    }


    public static void main(String[] args) throws InterruptedException {
        GroupChartClient chartClient = new GroupChartClient();
        chartClient.run();
    }
}