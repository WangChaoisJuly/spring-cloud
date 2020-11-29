package com.wangc.productor.controller;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.wangc.productor.dto.RabbitConstant;
import com.wangc.productor.utils.RabbitUtils;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class MsgPubulish {
    public static void main(String[] args) throws IOException, TimeoutException {

        // 消息生产者发送消息流程 1-5 基础设置 6根据不同工作模式有不同调用方法
        // 1、创建连接工厂 ConnectionFactory
        // 2、在ConnectionFactory设置连接配置
        // 3、根据ConnectionFactory创建connection
        // 4、根据connection创建channel
        // 5、根据channle声明队列 queueDeclare
        // 6、发送消息 basicPublish
        Channel channel = null;
        Connection connection = null;
        try {
            connection = RabbitUtils.getConnection();
            channel = connection.createChannel();
            //创建队列,声明并创建一个队列，如果队列已存在，则使用这个队列
            //第一个参数：队列名称ID
            //第二个参数：是否持久化，false对应不持久化数据，MQ停掉数据就会丢失
            //第三个参数：是否队列私有化，false则代表所有消费者都可以访问，true代表只有第一次拥有它的消费者才能一直使用，其他消费者不让访问
            //第四个：是否自动删除,false代表连接停掉后不自动删除掉这个队列
            //其他额外的参数, null
            channel.queueDeclare(RabbitConstant.QUEUE_SMS, false, false, false, null);
            String message = "hello白起666";

            //使用对应的工作模式进行消息发送
            //四个参数
            //exchange 交换机，暂时用不到，在后面进行发布订阅时才会用到
            //队列名称
            //额外的设置属性
            //最后一个参数是要传递的消息字节数组
            channel.basicPublish("", RabbitConstant.QUEUE_SMS, null, message.getBytes());

        } finally {
            channel.close();
            connection.close();
        }
    }
}
