package com.examplexyy.demo.mq;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.listener.api.ChannelAwareMessageListener;

/**
 * @Author: Xiongyy
 * @Date: 2021/3/13 21:57
 * .............................................
 * 佛祖保佑             永无BUG
 */
//@Component
//@EnableRabbit
public class MqListener implements ChannelAwareMessageListener {
    //Ack手动确认时要继承该类

    @Override
    @RabbitListener(queues = "testMq")
    public void onMessage(Message message, Channel channel) throws Exception {
        System.out.println("接受消息" + message.getBody().toString());
        long deliveryTag = message.getMessageProperties().getDeliveryTag();
        try {
            //业务消息
            Thread.sleep(1000);
            channel.basicAck(deliveryTag, true);
        }catch (Exception e){
            channel.basicNack(deliveryTag, true, true);
        }
    }
}
