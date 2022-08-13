package com.examplexyy.demo.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * @author xiongyayun
 * @description
 * @date 2021/5/6 11:58
 */
public class SocketMultiplexingSingleThreadv1 {

    private ServerSocketChannel server = null;
    //linux 多路复用器（select poll    epoll kqueue） nginx  event{}
    private Selector selector = null;
    int port = 9090;

    public void initServer(){
        try {
            //打开socket通道
            server = ServerSocketChannel.open();
            //设置非阻塞
            server.configureBlocking(false);
            //绑定端口
            server.bind(new InetSocketAddress(port));
            //连接多路复用器
            selector = Selector.open();

            //绑定监听时间在多路复用器上面
            server.register(selector, SelectionKey.OP_ACCEPT);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void start() throws IOException {
        //初始化selector,监听端口
        initServer();
        System.out.println("服务器启动了。。。。。");
        while (true) {
            while (selector.select(100) > 0) {
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                //获取多路复用器的迭代器
                Iterator<SelectionKey> iterator = selectionKeys.iterator();
                while (iterator.hasNext()) {
                    SelectionKey key = iterator.next();
                    //因为selectionKeys是包含多个事件监听的,而一旦处理了,则就需要移除,否则同一事件会触发多次
                    //这里只是JVM级别的移除,主要是为了处理这次循环
                    iterator.remove();
                    if (key.isAcceptable()) {
                        //处理链接事件
                        acceptHandler(key);
                    }else if (key.isReadable()) {
                        readHandler(key);
                    }
                }
            }
        }

    }

    private void readHandler(SelectionKey key) throws IOException {
        System.out.println("read handler ........");
        SocketChannel client = (SocketChannel) key.channel();
        //这里是因为前面处理可读事件的时候,已经分配了buffer
        ByteBuffer buffer = (ByteBuffer) key.attachment();
        //清除buffer中的数据
        buffer.clear();
        int read = 0;
        try {
            String b = "";
            while (true) {
                //这里是从内核读到app里面的buffer
                read = client.read(buffer);
                if (read > 0) {
                    //翻转buffer
                    buffer.flip();
                    byte[] aaa = new byte[buffer.limit()];
                    buffer.get(aaa);
                    b = new String(aaa);
                }else if (read == 0) {
                    break;
                } else {
                    client.close();
                    break;
                }
            }
            System.out.println(client.socket().getPort() + " : " + b);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 处理连接事件,当有连接事件过来的时候,需要注册一个可读的监听,并且分配内存
     * @param key
     * @throws IOException
     */
    private void acceptHandler(SelectionKey key) throws IOException {
        ServerSocketChannel ssc = (ServerSocketChannel) key.channel();
        //获取到连接事件的文件描述符
        SocketChannel client = ssc.accept();
        //设置非阻塞
        client.configureBlocking(false);

        ByteBuffer buffer = ByteBuffer.allocate(8192);

        //给可读事件绑定一个内存空间的buffer
        client.register(selector, SelectionKey.OP_READ, buffer);

        System.out.println("-------------------------------------------");
        System.out.println("新客户端：" + client.getRemoteAddress());
        System.out.println("-------------------------------------------");
    }

    public static void main(String[] args) throws IOException {
        SocketMultiplexingSingleThreadv1 s = new SocketMultiplexingSingleThreadv1();
        s.start();
    }
}
