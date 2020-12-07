package com.examplexyy.demo.nio;


import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;

/**
 * @Author: Xiongyy
 * @Date: 2020/12/6 13:47
 * .............................................
 * 佛祖保佑             永无BUG
 */
public class NIOServer {

    public static void main(String[] args) throws Exception {

        //打开通道
        ServerSocketChannel channel = ServerSocketChannel.open();

        //得到一个选择器
        Selector selector = Selector.open();

        //绑定监听端口
        channel.socket().bind(new InetSocketAddress(6666));

        //设置为非阻塞模式
        channel.configureBlocking(false);

        //把channel注册到选择器上面,
        channel.register(selector, SelectionKey.OP_ACCEPT);

        //循环等待客户端连接
        while (true){
            //等待一秒,如果没有连接事件,就继续
            if(selector.select(1000) == 0){ //没有任何时间发生
                System.out.println("等待了一秒,无连接");
                continue;
            }

            //如果返回的不是0,就获取到相关的selectionKeys集合
            //1.如果返回的大于0,表示已经获取到了关注的事件了
            //2.selectionKeys 返回关注事件的集合,通过selectionKeys反向获取通道
            Set<SelectionKey> selectionKeys = selector.selectedKeys();

            //遍历selectionKeys集合
            Iterator<SelectionKey> keyIterator = selectionKeys.iterator();

            while (keyIterator.hasNext()){
                //获取SelectionKey
                SelectionKey key = keyIterator.next();
                //根据key对应的通道发生的事件,做相应的处理
                if (key.isAcceptable()){//如果是OP_ACCEPT,有新的客户端连接
                    //给客户端生成一个socketChannel
                    SocketChannel socketChannel = channel.accept();

                    //将socketChannel设置为非阻塞
                    socketChannel.configureBlocking(false);
                    System.out.println("客户端连接成功,生成了一个socketChannel " + socketChannel.hashCode());

                    //将当前的socketChannel也注册到selector上面,关注事件为OP_READ,同时给socketChannel
                    //关联一个Buffer
                    socketChannel.register(selector, SelectionKey.OP_READ, ByteBuffer.allocate(1024));
                }

                if (key.isReadable()){//发生OP_READ
                    //通过key反向得到channel
                    SocketChannel channel1 = (SocketChannel)key.channel();
                    //获取该channel关联的Buffer
                    ByteBuffer buffer = (ByteBuffer)key.attachment();
                    channel1.read(buffer);
                    System.out.println("客户端发送:" + new String(buffer.array()));
                }

                //手动从集合中删除selectionKeys,防止重复
                keyIterator.remove();
            }
        }
    }
}
