package com.yao.dynamicgeneratedb.watch;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * @author yaochenglong
 * @version 1.0
 * @Description: TODO
 * @date 2020/6/5 10:07
 */
//事件监听器
public class CreateDataSourceListener implements Listener{

    @Autowired
    private RedisTemplate redisTemplate;

    private String userKey;

    public CreateDataSourceListener(String userKey) {
        this.userKey = userKey;
    }

    @Override
    public void fireAfterEventInvoked(Event event) {
        CreateDataSourceEvent createDataSourceEvent = (CreateDataSourceEvent) event;
        //todo 创建数据源可能会耗时较长，考虑是同步回调还是异步回调，要不要给返回值
        Object abc = redisTemplate.opsForHash().get("userId","databaseUrl");
        System.out.println("创建数据源,"+createDataSourceEvent.getEventName());
    }
}
