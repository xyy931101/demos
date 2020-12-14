package com.examplexyy.demo.netty.protocoltcp;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;

/**
 * @Author: Xiongyy
 * @Date: 2020/12/14 21:18
 * .............................................
 * 佛祖保佑             永无BUG
 */
public class ProtocolClientInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        ChannelPipeline pipeline = socketChannel.pipeline();

        //Outbound出站是反向
        pipeline.addLast(new ProtocolMessageEncoder());//加入编码器
        pipeline.addLast(new ProtocolMessageDecoder());
        pipeline.addLast(new ProtocolClientHandler());
    }
}
