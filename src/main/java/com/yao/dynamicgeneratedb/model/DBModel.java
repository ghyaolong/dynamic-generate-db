package com.yao.dynamicgeneratedb.model;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author yaochenglong
 * @version 1.0
 * @Description: TODO
 * @date 2020/6/4 15:31
 */
@Data
@Accessors(chain = true)
public class DBModel {

    private String id;
    private String url;
    private String userName;
    private String password;
    private String dbName;
    private Integer initialSize = 20;
    private Integer maxActive = 30;   //必须 initialSize <maxActive
}
