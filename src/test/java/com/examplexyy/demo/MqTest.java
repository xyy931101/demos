package com.examplexyy.demo;

import org.junit.Test;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * @Author: Xiongyy
 * @Date: 2021/3/13 21:46
 * .............................................
 * 佛祖保佑             永无BUG
 */
public class MqTest {

    @Autowired
    RabbitTemplate rabbitTemplate;

    @Test
    public void testConfirmCallback(){
        //设置消息确认模式
        rabbitTemplate.setConfirmCallback(new RabbitTemplate.ConfirmCallback() {
            @Override
            public void confirm(CorrelationData correlationData, boolean b, String s) {
                System.out.println("消息确认模式。。。。");
            }
        });

        rabbitTemplate.convertAndSend("test", "消息确认模式的test");
    }

    @Test
    public void testReturnMessage(){
        //设置手工确认
        rabbitTemplate.setMandatory(true);

        rabbitTemplate.setReturnCallback(new RabbitTemplate.ReturnCallback() {
            @Override
            public void returnedMessage(Message message, int i, String s, String s1, String s2) {
                System.out.println("回退机制。。。。");
            }
        });
        rabbitTemplate.convertAndSend("test", "消息回退机制的test");
    }
}
