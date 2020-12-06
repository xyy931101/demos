package com.examplexyy.demo.netty;

import java.io.File;
import java.io.FileInputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class NIOFileChannel02 {
	
	public static void main(String[] args) throws Exception {
		
		//将文件内容读入流
		File file = new File("D:\\data\\NIOFile.txt");
		FileInputStream stream = new FileInputStream(file);
		
		//通过流 获取channel
		FileChannel channel = stream.getChannel();
		
		ByteBuffer buffer = ByteBuffer.allocate((int)file.length());
		
		channel.read(buffer);
		
		//将字节转成字符串
		String string = new String(buffer.array());
		
		System.out.println(string);
		
		stream.close();

		System.out.println(2 << 2);
	}

}
