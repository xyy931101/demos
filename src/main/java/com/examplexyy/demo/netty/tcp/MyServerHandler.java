package com.examplexyy.demo.netty.tcp;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @Author: Xiongyy
 * @Date: 2020/12/7 22:17
 * .............................................
 * 佛祖保佑             永无BUG
 * 1.自定义一个handler需要继承netty规定好的某个HandlerAdapter
 * 2.这时我们自定义一个handler才能成为一个handler
 */
public class MyServerHandler extends SimpleChannelInboundHandler<ByteBuf> {

    private int count;

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println("exceptionCaught and error:" + cause);
        ctx.close();
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ByteBuf msg) throws Exception {
        byte[] bytes = new byte[msg.readableBytes()];

        msg.readBytes(bytes);

        //将buffer转成字节数组
        String s = new String(bytes, CharsetUtil.UTF_8);
        System.out.println("服务器端接受到数据" + s);
        System.out.println("服务器端接受到消息量=" + (++this.count));

        //服务器回送数据给客户端
        ctx.writeAndFlush(Unpooled.copiedBuffer(UUID.randomUUID().toString(), CharsetUtil.UTF_8));
    }


}
