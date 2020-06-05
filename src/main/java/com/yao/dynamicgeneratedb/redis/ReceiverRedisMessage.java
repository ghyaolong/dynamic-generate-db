package com.yao.dynamicgeneratedb.redis;

import com.yao.dynamicgeneratedb.springwatch.CreateDataSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author yaochenglong
 * @version 1.0
 * @Description: TODO
 * @date 2020/6/5 17:04
 */
@Component
@Slf4j
public class ReceiverRedisMessage {

    @Autowired
    private CreateDataSource createDataSource;


    /**
     * 队列消息接收方法
     *
     * @param customerNo 传递的用户唯一编码，用于获取该用户的数据库连接信息
     * @param channel 通道名称
     */
    public void receiveMessage(String customerNo,String channel) {
//        log.info("[开始消费REDIS消息队列phone数据...]");
//        try {
//            System.out.println(jsonMsg);
//            log.info("[消费REDIS消息队列phone数据成功.]");
//        } catch (Exception e) {
//            log.error("[消费REDIS消息队列phone数据失败，失败信息:{}]", e.getMessage());
//        }
        createDataSource.dynamicCreateDataSource(customerNo);
    }

    /**
     * 队列消息接收方法
     *
     * @param jsonMsg
     */
    public void receiveMessage2(String jsonMsg) {
        log.info("[开始消费REDIS消息队列phoneTest2数据...]");
        try {
            System.out.println(jsonMsg);
            /**
             *  此处执行自己代码逻辑 例如 插入 删除操作数据库等
             */

            log.info("[消费REDIS消息队列phoneTest2数据成功.]");
        } catch (Exception e) {
            log.error("[消费REDIS消息队列phoneTest2数据失败，失败信息:{}]", e.getMessage());
        }
    }




}
