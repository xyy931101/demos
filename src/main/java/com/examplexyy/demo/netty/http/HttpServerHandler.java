package com.examplexyy.demo.netty.http;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;

import static io.netty.handler.codec.http.HttpHeaderNames.*;
import static io.netty.handler.codec.http.HttpResponseStatus.OK;
import static io.netty.handler.codec.http.HttpVersion.*;

/**
 * HttpObject 客户端与服务端互相通讯的数据
 * @Author: Xiongyy
 * @Date: 2020/12/9 21:39
 * .............................................
 * 佛祖保佑             永无BUG
 */
public class HttpServerHandler extends SimpleChannelInboundHandler<HttpObject> {

    /**
     * 读取客户端数据
     * @param channelHandlerContext
     * @param msg
     * @throws Exception
     */
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, HttpObject msg) throws Exception {
        //判断msg是不是httpRequest请求
        if(msg instanceof HttpRequest){
            System.out.println("msg的类型:" + msg.getClass());
            System.out.println("客户端的地址:" + channelHandlerContext.channel().remoteAddress());

            //回复信息给浏览器[http协议]
            ByteBuf byteBuf = Unpooled.copiedBuffer("hello 我是客户端", CharsetUtil.UTF_8);

            //构造一个http响应,即httpResponse
            DefaultFullHttpResponse httpResponse = new DefaultFullHttpResponse(HTTP_1_0, OK, byteBuf);

            httpResponse.headers().set(CONTENT_TYPE, "text/plain;charset=utf-8");
            httpResponse.headers().set(CONTENT_LENGTH, byteBuf.readableBytes());

            channelHandlerContext.writeAndFlush(httpResponse);

        }
    }
}
