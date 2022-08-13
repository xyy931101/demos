package com.examplexyy.demo.netty.websocket;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.timeout.IdleStateHandler;

import java.util.concurrent.TimeUnit;

/**
 * @Author: Xiongyy
 * @Date: 2020/12/13 20:13
 * .............................................
 * 佛祖保佑             永无BUG
 */
public class WebsocketServer {

    public static void main(String[] args) throws InterruptedException {

        //创建两个线程组
        NioEventLoopGroup bossGroup = new NioEventLoopGroup(1);
        NioEventLoopGroup workerGroup = new NioEventLoopGroup(8);
        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .handler(new LoggingHandler())
                    .childHandler(new ChannelInitializer<SocketChannel>() {

                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            ChannelPipeline pipeline = socketChannel.pipeline();

                            //IdleStateHandler是处理空闲状态的处理器
                            pipeline.addLast(new IdleStateHandler(3,5, 7, TimeUnit.SECONDS));
                            //http编码处理器
                            pipeline.addLast(new HttpServerCodec());
                            //将HTTP大数据进行聚合
                            pipeline.addLast(new HttpObjectAggregator(8192));
                            //xyy对应的是浏览器的URL,
                            //WebSocketServerProtocolHandler将HTTP协议升级为WS协议,并且保持长连接
                            pipeline.addLast(new WebSocketServerProtocolHandler("/xyy"));

                            //自定义的处理器
                            pipeline.addLast(new WebSocketHandler());
                        }
                    });
            ChannelFuture channelFuture = serverBootstrap.bind(8888).sync();
            channelFuture.channel().closeFuture().sync();
        }finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }

    }
}
