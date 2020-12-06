package com.examplexyy.demo.netty;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * @Author: Xiongyy
 * @Date: 2020/12/6 14:24
 * .............................................
 * 佛祖保佑             永无BUG
 */
public class NIOClient {

    public static void main(String[] args) throws Exception{

        //得到一个网络通道
        SocketChannel socketChannel = SocketChannel.open();

        //设置非阻塞
        socketChannel.configureBlocking(false);

        InetSocketAddress inetSocketAddress = new InetSocketAddress("127.0.0.1", 6666);

        //连接服务器
        if (!socketChannel.connect(inetSocketAddress)){
            while (!socketChannel.finishConnect()){
                System.out.println("网络连接中....");
            }
        }
        //如果连接成功,就发送数据
        String str = "hello xyy NIO";
        ByteBuffer buffer = ByteBuffer.wrap(str.getBytes());
        //发送数据,将buffer写入channel
        socketChannel.write(buffer);

        System.in.read();
    }
}
