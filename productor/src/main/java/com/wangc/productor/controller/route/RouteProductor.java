package com.wangc.productor.controller.route;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.wangc.productor.dto.RabbitConstant;
import com.wangc.productor.utils.RabbitUtils;
import org.slf4j.Logger;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class RouteProductor {
    public static void main(String[] args) throws Exception {
        Map<String, String> area = new HashMap<>();
        area.put("china.hunan.changsha.20201127", "中国湖南长沙20201127天气数据");
        area.put("china.hubei.wuhan.20201127", "中国湖北武汉20201127天气数据");
        area.put("china.hunan.zhuzhou.20201127", "中国湖南株洲20201128天气数据");
        area.put("us.cal.lsj.20201127", "美国加州洛杉矶20201127天气数据");

        area.put("china.hebei.shijiazhuang.20201128", "中国河北石家庄20201128天气数据");
        area.put("china.hubei.wuhan.20201128", "中国湖北武汉20201128天气数据");
        area.put("china.henan.zhengzhou.20201128", "中国河南郑州20201128天气数据");
        area.put("us.cal.lsj.20201128", "美国加州洛杉矶20201128天气数据");

        Connection connection = RabbitUtils.getConnection();
        Channel channel = connection.createChannel();

        area.entrySet().stream().forEach(entry->{
            try {
                // 1、交换机名 2、route key 3、其他额外的属性 4、需要发送的消息内容
                channel.basicPublish(RabbitConstant.EXCHANGE_WEATHER_ROUTING,entry.getKey(),null,entry.getValue().getBytes());
            } catch (IOException e) {
                System.out.println("消息发送异常！");
            }
        });

        channel.close();
        connection.close();

        System.out.println("消息发送成功");

    }
}
