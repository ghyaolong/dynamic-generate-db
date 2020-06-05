package com.yao.dynamicgeneratedb.model;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author yaochenglong
 * @version 1.0
 * @Description: 数据库连接池相关配置属性
 * @date 2020/6/5 16:04
 */
@Data
public class DBPollModel {
    private Integer initialSize;
    private Integer maxActive;   //必须 initialSize <maxActive
    private Integer minIdle;
    private Integer maxWait;
    private Integer timeBetweenEvictionRunsMillis;
    private Integer minEvictableIdleTimeMillis;
    private String validationQuery;
    private Boolean testWhileIdle;
    private Boolean testOnBorrow;
    private Boolean testOnReturn;
    private Boolean poolPreparedStatements;

    private Integer maxPoolPreparedStatementPerConnectionSize;
    private String filters;
    private String connectionProperties;

}
