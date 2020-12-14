package com.examplexyy.demo.netty.protocoltcp;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;

/**
 * @Author: Xiongyy
 * @Date: 2020/12/14 20:49
 * .............................................
 * 佛祖保佑             永无BUG
 */
public class ProtocolServerInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        ChannelPipeline pipeline = socketChannel.pipeline();

        pipeline.addLast(new ProtocolMessageDecoder());//解码器
        pipeline.addLast(new ProtocolMessageEncoder());//编码器
        pipeline.addLast(new ProtocolServerHandler());

    }
}
