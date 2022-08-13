package com.examplexyy.demo.netty.websocket;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import org.joda.time.DateTime;

/**
 * TextWebSocketFrame表示是一个文本帧(frame)
 * @Author: Xiongyy
 * @Date: 2020/12/13 20:58
 * .............................................
 * 佛祖保佑             永无BUG
 */
public class WebSocketHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {


    @Override
    protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame msg) throws Exception {
        System.out.println("收到浏览器的消息了:" + msg.text());

        ctx.writeAndFlush(new TextWebSocketFrame("哎呀呀呀,知道了知道了傻逼!!!" + DateTime.now().toString("yyyy-MM-dd HH:mm:ss")));
    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        //输出连接的ID
        System.out.println("浏览器进行了连接：" + ctx.channel().id().asLongText());
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println("这里有异常啊:" + cause.getMessage());
        ctx.close();
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        System.out.println("浏览器关闭了连接：" + ctx.channel().id().asLongText());
    }
}
