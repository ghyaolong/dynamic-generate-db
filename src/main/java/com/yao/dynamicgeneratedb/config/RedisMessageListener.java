package com.yao.dynamicgeneratedb.config;

import com.yao.dynamicgeneratedb.redis.ReceiverRedisMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;

import java.util.concurrent.CountDownLatch;

/**
 * @author yaochenglong
 * @version 1.0
 * @Description: TODO
 * @date 2020/6/5 17:02
 */
@Configuration
public class RedisMessageListener {

    @Value("${redis.datasourceChannel}")
    private String dataSourceChannel;

    /**
     * 创建连接工厂
     * @param connectionFactory
     * @param listenerAdapter
     * @return
     */
    @Bean
    public RedisMessageListenerContainer container(RedisConnectionFactory connectionFactory,
                                                   MessageListenerAdapter listenerAdapter){
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        //接受消息的key
        container.addMessageListener(listenerAdapter,new PatternTopic(dataSourceChannel));
        return container;
    }
    /**
     * 绑定消息监听者和接收监听的方法
     * @param receiver
     * @return
     */
    @Bean
    public MessageListenerAdapter listenerAdapter(ReceiverRedisMessage receiver){
        return new MessageListenerAdapter(receiver,"receiveMessage");
    }
    /**
     * 注册订阅者
     * @return
     */
    @Bean
    ReceiverRedisMessage receiver() {
        return new ReceiverRedisMessage();
    }
}
