package com.wangc.productor.controller.pubsub;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.wangc.productor.dto.RabbitConstant;
import com.wangc.productor.utils.RabbitUtils;


public class PubSubProductor {
    public static void main(String[] args) throws Exception {
        Connection connection = RabbitUtils.getConnection();
        Channel channel = connection.createChannel();

        // 交换机
        // 没有指定队列名
        // 其他属性
        // 发送的消息内容
        channel.basicPublish(RabbitConstant.EXCHANGE_WEATHER,"" , null , "123456789".getBytes());
        channel.close();
        connection.close();
    }
}
