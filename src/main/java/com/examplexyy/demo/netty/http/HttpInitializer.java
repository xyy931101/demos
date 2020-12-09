package com.examplexyy.demo.netty.http;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpServerCodec;

/**
 * @Author: Xiongyy
 * @Date: 2020/12/9 21:40
 * .............................................
 * 佛祖保佑             永无BUG
 */
public class HttpInitializer extends ChannelInitializer<SocketChannel> {


    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {

        //向管道增加处理器
        ChannelPipeline pipeline = socketChannel.pipeline();

        //加入一个Netty提供的HttpServiceCodec codec -> coder  decoder
        //HttpServerCodec 是netty提供的http编码
        pipeline.addLast("myHttpServerCodec", new HttpServerCodec());
        //增加一个自定义的处理器
        pipeline.addLast("myHttpServerHandler", new HttpServerHandler());
    }
}
