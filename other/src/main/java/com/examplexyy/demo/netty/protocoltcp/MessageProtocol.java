package com.examplexyy.demo.netty.protocoltcp;

import lombok.Data;

/**
 * 协议包
 * @Author: Xiongyy
 * @Date: 2020/12/14 21:16
 * .............................................
 * 佛祖保佑             永无BUG
 */

public class MessageProtocol {
    private int len;
    private byte[] content;

    public int getLen() {
        return len;
    }

    public void setLen(int len) {
        this.len = len;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }
}
