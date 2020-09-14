package com.mot.config.rabbitmq;


import com.mot.common.constant.RabbitMqConstant;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

import java.util.HashMap;
import java.util.Map;

@Configuration
@Lazy
public class TopicRabbitConfig {

    @Bean("queue")
    public Queue queue() {
        // durable:是否持久化,默认是false,持久化队列：会被存储在磁盘上，当消息代理重启时仍然存在，暂存队列：当前连接有效
        // exclusive:默认也是false，只能被当前创建的连接使用，而且当连接关闭后队列即被删除。此参考优先级高于durable
        // autoDelete:是否自动删除，当没有生产者或者消费者使用此队列，该队列会自动删除。
        Map<String,Object> map=new HashMap<>();
        map.put("x-message-ttl", 600000);//message在该队列queue的存活时间最大为600秒
        String key = RabbitMqConstant.routerKey;
        return new Queue(key,true,false,false,map);
    }

    @Bean("exchange")
    TopicExchange exchange() {
        return new TopicExchange(RabbitMqConstant.exchangeName);
    }

    /**
     *  发布订阅
     *  由通配符#，符合该规则的消息都会进入此队列
     * @param queue
     * @param exchange
     * @return
     */
    @Bean
    Binding bindingExchangeMessage(Queue queue,TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(RabbitMqConstant.routerKeyPrefix+".#");
    }

}
