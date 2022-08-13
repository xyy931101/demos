package com.examplexyy.demo.netty.groupchart;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * 客户端处理器
 * @Author: Xiongyy
 * @Date: 2020/12/11 0:04
 * .............................................
 * 佛祖保佑             永无BUG
 */
public class GroupChartClientHandler extends SimpleChannelInboundHandler<String> {


    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, String msg) throws Exception {
        System.out.println(msg.trim());
    }

}
