package com.examplexyy.demo.netty.protocoltcp;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;

import java.nio.charset.StandardCharsets;
import java.util.UUID;

/**
 * @Author: Xiongyy
 * @Date: 2020/12/7 22:17
 * .............................................
 * 佛祖保佑             永无BUG
 * 1.自定义一个handler需要继承netty规定好的某个HandlerAdapter
 * 2.这时我们自定义一个handler才能成为一个handler
 */
public class ProtocolServerHandler extends SimpleChannelInboundHandler<MessageProtocol> {

    private int count;

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println("ProtocolServerHandler出现异常" +  cause);
        cause.printStackTrace();
        ctx.close();
    }


    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, MessageProtocol msg) throws Exception {
        int len = msg.getLen();
        byte[] content = msg.getContent();

        //将buffer转成字节数组
        String s = new String(content, CharsetUtil.UTF_8);
        System.out.println("服务器端接受到数据" + s);
        System.out.println("服务器端接受到消息长度=" + len);
        System.out.println("服务器端接受到消息量=" + (++this.count));

        String uuid = UUID.randomUUID().toString();
        byte[] bytes = uuid.getBytes(CharsetUtil.UTF_8);
        MessageProtocol messageProtocol = new MessageProtocol();
        messageProtocol.setLen(bytes.length);
        messageProtocol.setContent(bytes);

        channelHandlerContext.writeAndFlush(messageProtocol);
    }
}
