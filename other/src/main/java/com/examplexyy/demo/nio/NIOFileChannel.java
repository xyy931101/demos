package com.examplexyy.demo.nio;

import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class NIOFileChannel {

	public static void main(String[] args) throws Exception {
		String str = "文件的IO";
		//创建一个输出流->channel
		FileOutputStream fileOutputStream = new FileOutputStream("D:\\data\\NIOFile.txt");
		//获取对应的文件channel(真实类型是FileChannelImpl)
		FileChannel channel = fileOutputStream.getChannel();
		
		//创建一个缓冲区
		ByteBuffer buffer = ByteBuffer.allocate(1024 * 2);
		//将str写入到buffer
		buffer.put(str.getBytes());
		
		//对buffer进行flip
		buffer.flip();
		
		channel.write(buffer);
		
		fileOutputStream.close();
	}
}
