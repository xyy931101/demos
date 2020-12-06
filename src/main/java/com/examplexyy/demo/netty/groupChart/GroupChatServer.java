package com.examplexyy.demo.netty.groupChart;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;

/**
 * @Author: Xiongyy
 * @Date: 2020/12/6 15:30
 * .............................................
 * 佛祖保佑             永无BUG
 */
public class GroupChatServer {

    private Selector selector;

    private ServerSocketChannel listenChannel;

    private static final int PORT = 6667;

    public GroupChatServer(){
        try {
            //得到选择器
            selector = Selector.open();

            listenChannel = ServerSocketChannel.open();

            //channel绑定端口
            listenChannel.socket().bind(new InetSocketAddress(PORT));

            listenChannel.configureBlocking(false);

            //将channel注册到选择器上面
            listenChannel.register(selector, SelectionKey.OP_ACCEPT);


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void listen(){
        try{
            while (true){
                int count = selector.select(2000);
                if (count > 0){//有事件要处理
                    Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
                    while (iterator.hasNext()){
                        //取出迭代器
                        SelectionKey key = iterator.next();

                        //如果监听到了accept事件
                        if (key.isAcceptable()){

                            SocketChannel socketChannel = listenChannel.accept();
                            socketChannel.configureBlocking(false);
                            //将该accept注册到selector
                            System.out.println(socketChannel.getRemoteAddress() + "已上线!!!");

                            socketChannel.register(selector, SelectionKey.OP_READ);

                        }
                        if(key.isReadable()){
                            //读取数据
                            readData(key);
                        }
                        //当前的selectionKey删除,防止重复
                        iterator.remove();
                    }
                }else{
                    System.out.println("小熊等待中。。。。");
                }

            }
        }catch (Exception e){
            e.printStackTrace();

        }finally {

        }

    }

    //读取管道里面的数据
    private void readData(SelectionKey selectionKey){
        //定义一个socketChannel
        SocketChannel channel = null;
        try {
            //取到关联的channel
            channel = (SocketChannel)selectionKey.channel();
            channel.configureBlocking(false);
            //创建buffer
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            //将数据读取到buffer
            int read = channel.read(buffer);
            if(read > 0 ){//确实读取到数据了
                //把缓存区转成字符串
                String message = new String(buffer.array());
                System.out.println("from 客户端" + message);

                //向其他客户端转发消息
                sendInfoToOther(message, channel);
            }
        }catch (Exception e){
            try {
                System.out.println(channel.getRemoteAddress() + "离线了。。。");
                //取消注册
                selectionKey.cancel();
                //关闭通道
                channel.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    //转发消息给其他客户端
    private void sendInfoToOther(String message, SocketChannel selfChannel) throws IOException {
        System.out.println("服务器转发消息中!");

        //遍历所有注册到selector上的channel并排除self

        for (SelectionKey key : selector.keys()) {
            //取出通道
            Channel channel = key.channel();
            //排除self
            if (channel instanceof SocketChannel && channel != selfChannel){
                //进行转发
                ByteBuffer buffer = ByteBuffer.wrap(message.getBytes());
                ((SocketChannel) channel).write(buffer);
            }
        }
    }

    public static void main(String[] args) {
        //创建一个服务器对象
        GroupChatServer server = new GroupChatServer();
        server.listen();
    }
}
