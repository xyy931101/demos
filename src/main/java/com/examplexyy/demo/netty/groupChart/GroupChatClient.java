package com.examplexyy.demo.netty.groupChart;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Scanner;

/**
 * @Author: Xiongyy
 * @Date: 2020/12/6 16:11
 * .............................................
 * 佛祖保佑             永无BUG
 */
public class GroupChatClient {

    private final String HOST = "127.0.0.1";//服务器的ID
    private final int PORT = 6667;
    private Selector selector;
    private SocketChannel socketChannel;
    private String username;

    //构造器，初始化
    public GroupChatClient() throws Exception {
        //
        selector = Selector.open();
        //连接服务器
        socketChannel = SocketChannel.open(new InetSocketAddress(HOST, PORT));
        //设置非阻塞
        socketChannel.configureBlocking(false);

        //将channel注册到selector
        socketChannel.register(selector, SelectionKey.OP_READ);

        username = socketChannel.getLocalAddress().toString().substring(1);
        System.out.println(username + "is ok!!!");
    }

    public void  sendInfo(String info){
        info = username + " say: " + info;
        try{
            socketChannel.write(ByteBuffer.wrap(info.getBytes()));
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void readInfo(){
        try {
            int readChannels = selector.select();

            if (readChannels > 0){ //有可用的通道
                Iterator<SelectionKey> selectionKeys = selector.selectedKeys().iterator();
                while (selectionKeys.hasNext()){
                    SelectionKey key = selectionKeys.next();
                    if(key.isReadable()){
                        SocketChannel socketChannel = (SocketChannel)key.channel();
                        socketChannel.configureBlocking(false);
                        //得到一个buffer
                        ByteBuffer buffer = ByteBuffer.allocate(1024);
                        socketChannel.read(buffer);
                        //把读到的缓冲区的数据,转成字符串
                        String s = new String(buffer.array());
                        System.out.println(s);
                    }
                    //移除当前的selectionKey,防止重复操作
                    selectionKeys.remove();
                }
            }else{
                System.out.println("没有可用的通道。。。");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {
        //启动客户端
        GroupChatClient client = new GroupChatClient();

        //每隔3秒读取从服务器端发送的数据
        new Thread(){
            @Override
            public void run() {
                while (true){
                    client.readInfo();
                    try{
                        Thread.currentThread().sleep(3000);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }
        }.start();

        //发送数据给服务端
        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNextLine()){
            String s = scanner.nextLine();
            client.sendInfo(s);
        }
    }
}
