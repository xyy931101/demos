package com.examplexyy.demo.netty.groupchart;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 服务端(连接，关闭连接,发送消息的处理)
 * @Author: Xiongyy
 * @Date: 2020/12/10 23:18
 * .............................................
 * 佛祖保佑             永无BUG
 */
@ChannelHandler.Sharable
public class GroupChartServerHandler extends SimpleChannelInboundHandler<String> {

    //定义一个客户端Channel组,管理所有的客户端Channel
    private static ChannelGroup channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);//全局的事件执行器(single)

    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    //一旦连接,第一个被执行,将当前channel加入group
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        //将该客户端加入聊天室的信息推送给其他客户端(writeAndFlush会遍历所有客户端并发送消息)
        channelGroup.writeAndFlush(channel.remoteAddress() + "来 see see loser们\n");
        //将该channel加入队列
        channelGroup.add(channel);
    }

    //表示channel处于活动状态,提示xxx已上线
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println(dateFormat.format(new Date()) + "[丑逼] " + ctx.channel().remoteAddress() + " 加入聊天~~~阿西吧！！\n");
    }

    //将某某离开的信息提示给当前在线的客户端
    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        channelGroup.writeAndFlush("[大傻逼] " + channel.remoteAddress() + "终于离开了 \n");
        System.out.println(dateFormat.format(new Date()) + "当前在线人数:" + channelGroup.size());
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("[大傻逼] " + ctx.channel().remoteAddress() + "已离线！！！");
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        //获取到当前channel
        Channel channel = ctx.channel();
        //遍历所有group,当前用户不同
        channelGroup.forEach(channel1 -> {
            if (channel != channel1){//如果不是当前用户,直接转发消息
                channel1.writeAndFlush("[白痴] " + channel.remoteAddress() + "say:" + msg + "\n");
            }else{
                channel.writeAndFlush("[帅逼发送成功]");
            }
        });
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        //异常发生时,关闭通道
        ctx.close();
    }
}
