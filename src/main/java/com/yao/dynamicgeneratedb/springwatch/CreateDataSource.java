package com.yao.dynamicgeneratedb.springwatch;

import com.yao.dynamicgeneratedb.config.DBPollConfig;
import com.yao.dynamicgeneratedb.model.DBModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

/**
 * @author yaochenglong
 * @version 1.0
 * @Description: TODO
 * @date 2020/6/5 13:53
 */
@Slf4j
@Component("createDataSource")
public class CreateDataSource {

    @Value("${spring.application.name}")
    private String applicationName;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private DBPollConfig dbPollConfig;

    public void createDataSource(String customerNo){
        System.out.println("createDataSource开始创建数据源");
        log.info("获取租户的唯一编码customerNo:{}",customerNo);
        Boolean userA = redisTemplate.hasKey("userA");

        String url = (String) redisTemplate.opsForHash().get("userA", "url");
        String userName = (String) redisTemplate.opsForHash().get("userA", "userName");
        String password = (String) redisTemplate.opsForHash().get("userA", "password");
        String dbName = applicationName;
        //todo 测试用kamo1，测试完之后真正的数据库名称为applicationName
        dbName = "kamo1";
        DBModel db = new DBModel();
        BeanUtils.copyProperties(dbPollConfig,db);
        db
                .setDomain("www.baidu.com")
                .setCustomerNo("00001")
                .setCustomerName("巴克队长")
                .setId("2")
                .setUrl(url)
                .setUserName(userName)
                .setPassword(password)
                .setDbName(dbName);
        log.info("获取租户的数据库连接信息[{}]",db.toString());
        System.out.println("租户A的数据库url："+url);
        System.out.println(applicationName);
        CreateDataSourceEvent event = new CreateDataSourceEvent(applicationContext,db);
        applicationContext.publishEvent(event);
    }
}
