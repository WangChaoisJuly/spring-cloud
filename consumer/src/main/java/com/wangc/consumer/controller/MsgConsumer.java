package com.wangc.consumer.controller;

import com.rabbitmq.client.*;
import com.wangc.productor.dto.RabbitConstant;
import com.wangc.productor.utils.RabbitUtils;

import java.io.IOException;

public class MsgConsumer {
    public static void main(String[] args) throws Exception {
        // 1、创建连接工厂 ConnectionFactory
        // 2、在ConnectionFactory设置连接配置
        // 3、根据ConnectionFactory创建connection
        Connection connection = RabbitUtils.getConnection();
        // 4、根据connection创建channel
        Channel channel = connection.createChannel();
        // 5、根据channle声明队列 queueDeclare
        channel.queueDeclare(RabbitConstant.QUEUE_HELLOWORLD, false, false, false, null);
        // 6、根据不同工作模式调用不同的消费方式(basicConsumer重载实现不同工作模式的调用)

        // 当消费成功一条后才会进行下一条消息的消费
        channel.basicQos(1);
        // 队列名
        // 手动编程确认消息消费
        // 消费者
        channel.basicConsume(RabbitConstant.QUEUE_HELLOWORLD,false,new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String message = new String(body);
                System.out.println("消费者接收到的消息："+message);

                System.out.println("消息的TagId："+envelope.getDeliveryTag());
                //false只确认签收当前的消息，设置为true的时候则代表签收该消费者所有未签收的消息
                channel.basicAck(envelope.getDeliveryTag(), false);
            }
        });
    }
}
