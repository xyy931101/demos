package com.examplexyy.demo.netty.protocoltcp;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * @Author: Xiongyy
 * @Date: 2020/12/14 21:24
 * .............................................
 * 佛祖保佑             永无BUG
 */
public class ProtocolMessageEncoder extends MessageToByteEncoder<MessageProtocol> {
    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, MessageProtocol msg, ByteBuf out) throws Exception {
        System.out.println("ProtocolMessageEncoder encode被调用~~~~");
        out.writeInt(msg.getLen());
        out.writeBytes(msg.getContent());
    }
}
