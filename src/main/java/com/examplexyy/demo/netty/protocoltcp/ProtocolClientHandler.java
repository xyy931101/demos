package com.examplexyy.demo.netty.protocoltcp;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;

import java.util.Date;

/**
 * @Author: Xiongyy
 * @Date: 2020/12/7 23:02
 * .............................................
 * 佛祖保佑             永无BUG
 */
public class ProtocolClientHandler extends SimpleChannelInboundHandler<MessageProtocol>{

    private int count;

    /**
     * 当通道就绪时，机会触发
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        //发送10条数据
        for (int i = 0; i < 5; i++) {
            String message = "hello xyy 2020.12.14  " + i;
            byte[] bytes = message.getBytes(CharsetUtil.UTF_8);
            int length = bytes.length;

            MessageProtocol messageProtocol = new MessageProtocol();
            messageProtocol.setContent(bytes);
            messageProtocol.setLen(length);

            ctx.writeAndFlush(messageProtocol);

            System.out.println(new Date() + "hello xyy 2020.12.14  channelActive");
            Thread.sleep(1000);
        }
    }


    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println("这里有异常~~~ " + cause.getMessage());
        ctx.close();
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, MessageProtocol messageProtocol) throws Exception {
        int len = messageProtocol.getLen();
        String s = new String(messageProtocol.getContent(), CharsetUtil.UTF_8);
        System.out.println("客户端接受到数据" + s);
        System.out.println("客户端接受到消息长度=" + len);
        System.out.println("客户端接受到消息量=" + (++this.count));
    }
}
