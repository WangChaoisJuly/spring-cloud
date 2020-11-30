package com.wangc.productor.controller.workQueue;

import com.google.gson.Gson;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.wangc.productor.dto.RabbitConstant;
import com.wangc.productor.dto.SMS;
import com.wangc.productor.utils.RabbitUtils;

public class WorkQueuePublish {
    public static void main(String[] args) throws Exception {
        Connection connection = RabbitUtils.getConnection();
        Channel channel = connection.createChannel();

        /**
         * 队列名
         * 是否持久化
         * 是否私有化，false表示非私有化所有队列均可使用。true表示只有第一个或其他的消费者可以使用
         * 是否自动删除，false表示tcp长连接停掉后不自动删除这个queue
         */
        channel.queueDeclare(RabbitConstant.QUEUE_SMS, false, false, false, null);

        for (int i = 1; i <= 100; i++) {
            SMS sms = new SMS("乘客" + i, "13900000" + i, "您的车票已预订成功");
            String jsonSMS = new Gson().toJson(sms);
            /**
             * exchange 交换机
             * 队列名
             * 额外设置的属性
             * 传递消息的byte[]
             */
            channel.basicPublish("", RabbitConstant.QUEUE_SMS, null, jsonSMS.getBytes());
        }

        channel.close();
        connection.close();
    }
}
