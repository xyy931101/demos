package com.examplexyy.demo.nio;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @Author: Xiongyy
 * @Date: 2020/12/29 20:37
 * .............................................
 * 佛祖保佑             永无BUG
 */
public class FileOutPutDemo {

    static private final byte MESSAGE [] = {83, 111, 109, 101, 32, 98,
        121, 116, 101, 115, 46};

    public static void main(String[] args) throws IOException {
        FileOutputStream stream = new FileOutputStream("D://TEST.txt");

        FileChannel channel = stream.getChannel();

        ByteBuffer buffer = ByteBuffer.allocate(1024);

        for (int i = 0; i < MESSAGE.length; i++) {
            buffer.put(MESSAGE[i]);
        }
        buffer.flip();

        channel.write(buffer);

        stream.close();

    }

}
