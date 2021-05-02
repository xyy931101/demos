package com.examplexyy.demo.netty.simple;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

import java.util.concurrent.TimeUnit;

/**
 * @Author: Xiongyy
 * @Date: 2020/12/7 22:17
 * .............................................
 * 佛祖保佑             永无BUG
 * 1.自定义一个handler需要继承netty规定好的某个HandlerAdapter
 * 2.这时我们自定义一个handler才能成为一个handler
 */
public class NettyServerHandler extends ChannelInboundHandlerAdapter {

    /**
     *
     * @param ctx 上下文对象，包含管道pipeline,通道channel,地址
     * @param msg 就是客户端发送的信息,默认是OBJ
     * @throws Exception
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("server ctx = " + ctx);

        //将msg转成一个byteBuffer
        //注意 byteBuffer不是NIO的ByteBuffer
        ByteBuf buffer = (ByteBuf) msg;

        System.out.println("客户端发送消息是:" + buffer.toString(CharsetUtil.UTF_8));
        System.out.println("客户端地址是:" + ctx.channel().remoteAddress());


        try {
            ctx.channel().eventLoop().schedule(new Runnable() {
                @Override
                public void run() {
                    ctx.writeAndFlush(Unpooled.copiedBuffer("hello 客户端,这是定时任务~~~", CharsetUtil.UTF_8));
                    System.out.println("这是一个定时任务~~~");
                }
            }, 5, TimeUnit.SECONDS);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 数据读取完毕
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.writeAndFlush(Unpooled.copiedBuffer("hello 客户端 汪汪汪", CharsetUtil.UTF_8));
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }
}
