package com.examplexyy.demo.netty.protocoltcp;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ReplayingDecoder;

import java.util.List;

/**
 * @Author: Xiongyy
 * @Date: 2020/12/14 21:28
 * .............................................
 * 佛祖保佑             永无BUG
 */
public class ProtocolMessageDecoder extends ReplayingDecoder<Void> {

    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf in, List<Object> out) throws Exception {
        System.out.println("ProtocolMessageDecoder decode被调用~~~");

        //将得到的二进制字节码 -> MessageProtocol
        int length = in.readInt();

        byte[] content = new byte[length];
        in.readBytes(content);

        MessageProtocol messageProtocol = new MessageProtocol();
        messageProtocol.setLen(length);
        messageProtocol.setContent(content);

        out.add(messageProtocol);
    }
}
