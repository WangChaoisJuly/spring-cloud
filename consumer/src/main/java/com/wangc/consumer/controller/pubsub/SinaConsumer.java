package com.wangc.consumer.controller.pubsub;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.wangc.productor.utils.RabbitUtils;

import java.io.IOException;

public class PubSubConsumer {
    public static void main(String[] args) throws Exception {
        Connection connection = RabbitUtils.getConnection();
        Channel channel = connection.createChannel();

        channel.queueDeclare()

    }
}
