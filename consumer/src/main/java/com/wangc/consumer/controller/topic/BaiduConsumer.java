package com.wangc.consumer.controller.topic;

import com.rabbitmq.client.*;
import com.wangc.productor.dto.RabbitConstant;
import com.wangc.productor.utils.RabbitUtils;

import java.io.IOException;

public class BaiduConsumer {
    public static void main(String[] args) throws Exception {
        Connection connection = RabbitUtils.getConnection();
        Channel channel = connection.createChannel();

        channel.queueDeclare(RabbitConstant.QUEUE_BAIDU,false,false,false,null);

        // topic模式相当于对route模式的模糊查询实现
        // #代表一个或者多个单词、字母
        // *代表一个单词、字母
        channel.queueBind(RabbitConstant.QUEUE_BAIDU,RabbitConstant.EXCHANGE_WEATHER_TOPIC,"#.20201127");

        channel.basicQos(1);

        channel.basicConsume(RabbitConstant.QUEUE_BAIDU,false,new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("百度天气收到气象信息：" + new String(body));
                channel.basicAck(envelope.getDeliveryTag() , false);
            }
        });
    }
}
