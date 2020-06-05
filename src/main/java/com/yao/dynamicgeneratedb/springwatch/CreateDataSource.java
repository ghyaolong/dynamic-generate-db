package com.yao.dynamicgeneratedb.springwatch;

import com.alibaba.druid.pool.DruidDataSource;
import com.yao.dynamicgeneratedb.config.DBPollConfig;
import com.yao.dynamicgeneratedb.dynamicdatasource.DynamicDataSource;
import com.yao.dynamicgeneratedb.model.DBModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.sql.SQLException;

/**
 * @author yaochenglong
 * @version 1.0
 * @Description: TODO
 * @date 2020/6/5 13:53
 */
@Slf4j
@Component
public class CreateDataSource {

    @Value("${spring.application.name}")
    private String applicationName;

    @Autowired
    private RedisTemplate redisTemplate;


    @Autowired
    private DBPollConfig dbPollConfig;

    @Async
    public void dynamicCreateDataSource(String customerNo){
        System.out.println("createDataSource开始创建数据源");
        log.info("获取租户的唯一编码customerNo:{}",customerNo);

        //todo 去掉多余的双引号
        customerNo = customerNo.replaceAll("\"","");

        Boolean useKey = redisTemplate.hasKey(customerNo);

        String url = (String) redisTemplate.opsForHash().get(customerNo, "url");
        String userName = (String) redisTemplate.opsForHash().get(customerNo, "userName");
        String password = (String) redisTemplate.opsForHash().get(customerNo, "password");
        String host = (String) redisTemplate.opsForHash().get(customerNo, "host");
        String port = (String) redisTemplate.opsForHash().get(customerNo, "port");
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
                .setHost(host)
                .setPort(port)
                .setUserName(userName)
                .setPassword(password)
                .setDbName(dbName);
        log.info("获取租户的数据库连接信息[{}]",db.toString());
        System.out.println("租户A的数据库url："+url);
        System.out.println(applicationName);
        createDataSource(db);

    }
    private void createDataSource(DBModel dbModel) {
        Object o = DynamicDataSource.dataSourcesMap.get(dbModel.getId());
        if(o==null){
            DruidDataSource druidDataSource = new DruidDataSource();
            druidDataSource.setUrl("jdbc:mysql://"+dbModel.getHost()+":"+dbModel.getPort()+"/"+dbModel.getDbName()+"?characterEncoding=utf-8&useSSL=false&serverTimezone=UTC&useAffectedRows=true");
            druidDataSource.setUsername(dbModel.getUserName());
            druidDataSource.setPassword(dbModel.getPassword());
            druidDataSource.setMaxActive(dbModel.getMaxActive());
            druidDataSource.setInitialSize(dbModel.getInitialSize());
            //设置true，如果druidDataSource.init();异常的情况下，不会不停地输出错误，
            druidDataSource.setBreakAfterAcquireFailure(true);
            try {
                druidDataSource.init();
                log.info("租户{}的数据源创建完毕，数据源信息{}",dbModel.getCustomerNo(),druidDataSource.toString());
            } catch (SQLException throwables) {
                log.error(throwables.getMessage());
                log.error("创建数据库连接失败",throwables);
                new Exception(throwables.getMessage());
                return;
            }
            DynamicDataSource.dataSourcesMap.put(dbModel.getId(), druidDataSource);
            log.info("添加租户{}的数据源到map中",dbModel.getCustomerNo());
        }
        DynamicDataSource.setDataSource(dbModel.getId());
        log.info("切换租户{}的数据源成功");

        //此时数据源已切换到druidDataSource ，调用自己的业务方法即可。
        //使用完后调用DynamicDataSource.clear();重置为默认数据源。
    }
}
