package com.yao.dynamicgeneratedb.model;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.beans.BeanUtils;

/**
 * @author yaochenglong
 * @version 1.0
 * @Description: TODO
 * @date 2020/6/4 15:31
 */
@Data
@Accessors(chain = true)
public class DBModel extends DBPollModel{



    private String domain;
    private String customerNo;
    private String customerName;

    private String id;
    private String url;
    private String host;
    private String port;
    private String userName;
    private String password;
    private String dbName;

}
