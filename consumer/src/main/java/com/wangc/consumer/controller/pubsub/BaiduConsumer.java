package com.wangc.consumer.controller.pubsub;

import com.rabbitmq.client.*;
import com.wangc.productor.dto.RabbitConstant;
import com.wangc.productor.utils.RabbitUtils;

import java.io.IOException;

public class BaiduConsumer {
    public static void main(String[] args) throws Exception {
        Connection connection = RabbitUtils.getConnection();
        Channel channel = connection.createChannel();

        channel.queueDeclare(RabbitConstant.QUEUE_BAIDU, false, false, false, null);

        // 创建交换机和queue得连接
        /**
         * queueBind
         * 队列名
         * 交换机名
         * route key 在路由、topic模式中使用
         */
        channel.queueBind(RabbitConstant.QUEUE_BAIDU, RabbitConstant.EXCHANGE_WEATHER, "");

        channel.basicQos(1);
        channel.basicConsume(RabbitConstant.QUEUE_BAIDU, false, new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("百度天气收到气象信息：" + new String(body));
                System.out.println("队列信息：" + envelope.toString());
                System.out.println("---------------------------------------------------");
                channel.basicAck(envelope.getDeliveryTag(), false);
            }
        });

    }
}
