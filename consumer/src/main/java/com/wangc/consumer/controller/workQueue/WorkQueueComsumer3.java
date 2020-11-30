package com.wangc.consumer.controller.workQueue;

import com.rabbitmq.client.*;
import com.wangc.productor.dto.RabbitConstant;
import com.wangc.productor.utils.RabbitUtils;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class WorkQueueComsumer3 {
    public static void main(String[] args) throws IOException, TimeoutException {
        // 创建连接
        Connection connection = RabbitUtils.getConnection();
        // 根据连接创建channel
        Channel channel = connection.createChannel();
        // 创建、声明队列，如果队列已存在则使用这队列
        // 第一个参数：队列名称ID
        // 第二个参数：是否持久化，false对应不持久化数据，MQ停掉数据就会丢失
        // 第三个参数：是否队列私有化，false则代表所有消费者都可以访问，true代表只有第一次拥有它的消费者才能一直使用，其他消费者不让访问
        // 第四个：是否自动删除,false代表连接停掉后不自动删除掉这个队列
        // 其他额外的参数, null
        channel.queueDeclare(RabbitConstant.QUEUE_SMS, false, false, false, null);

        channel.basicQos(1);
        channel.basicConsume(RabbitConstant.QUEUE_SMS, false, new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String jsonSMS = new String(body);
                System.out.println("SMSSender3-短信发送成功:" + jsonSMS);
                System.out.println("consumerTag:" + consumerTag.toString());
                System.out.println("envelope:" + envelope.toString());
                System.out.println("properties:" + properties.toString());
                System.out.println("---------------------------------------------------------------------------------------------------------------------------");

                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                // 签收
                channel.basicAck(envelope.getDeliveryTag(), false);
            }
        });
    }
}
