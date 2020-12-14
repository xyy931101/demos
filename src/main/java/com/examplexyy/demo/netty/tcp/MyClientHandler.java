package com.examplexyy.demo.netty.tcp;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;

import java.nio.channels.SocketChannel;

/**
 * @Author: Xiongyy
 * @Date: 2020/12/7 23:02
 * .............................................
 * 佛祖保佑             永无BUG
 */
public class MyClientHandler extends SimpleChannelInboundHandler<ByteBuf>{

    private int count;

    /**
     * 当通道就绪时，机会触发
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        //发送10条数据
        for (int i = 0; i < 10; i++) {
            ByteBuf byteBuf = Unpooled.copiedBuffer("hello xyy~~~" + i, CharsetUtil.UTF_8);
            ctx.writeAndFlush(byteBuf);
        }
    }


    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ByteBuf msg) throws Exception {
        byte[] bytes = new byte[msg.readableBytes()];
        msg.readBytes(bytes);
        System.out.println("客户端接受数据:" + new String(bytes, CharsetUtil.UTF_8));
        System.out.println("客户端接受数据量:" + (++this.count));
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println("这里有异常~~~ " + cause.getMessage());

        ctx.close();
    }
}
