package com.wangc.consumer.controller.route;

import com.rabbitmq.client.*;
import com.wangc.productor.dto.RabbitConstant;
import com.wangc.productor.utils.RabbitUtils;

import java.io.IOException;

/**
 * route 消费者，需要交换机
 */
public class SinaConsumer {
    public static void main(String[] args) throws Exception {
        Connection connection = RabbitUtils.getConnection();
        Channel channel = connection.createChannel();

        channel.queueDeclare(RabbitConstant.QUEUE_SINA, false, false, false, null);
        // 需要交换机的工作模式就需要队列与交换机进行绑定
        // 1、队列名 2、交换机名 3、route key（在发送消息的时候定义的）
        channel.queueBind(RabbitConstant.QUEUE_SINA,RabbitConstant.EXCHANGE_WEATHER_ROUTING,"china.hebei.shijiazhuang.20201128");

        channel.basicQos(1);
        channel.basicConsume(RabbitConstant.QUEUE_SINA,false,new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("新浪天气收到气象信息：" + new String(body));
                channel.basicAck(envelope.getDeliveryTag() , false);
            }
        });
    }
}
