package com.yao.dynamicgeneratedb.controller;

import com.yao.dynamicgeneratedb.dynamicdatasource.DynamicDataSource;
import com.yao.dynamicgeneratedb.model.DBModel;
import com.yao.dynamicgeneratedb.model.User;
import com.yao.dynamicgeneratedb.service.DBService;
import com.yao.dynamicgeneratedb.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;

/**
 * @author yaochenglong
 * @version 1.0
 * @Description: TODO
 * @date 2020/6/4 15:02
 */
@Slf4j
@RestController
public class UserController {



    @Autowired
    private UserService userService;

    @Autowired
    private DBService dbService;

    @Autowired
    private RedisTemplate redisTemplate;

    @GetMapping("/user/get/{id}")
    public void getUser(@PathVariable("id") Integer id) {
        User userById = userService.getUserById(1);
        log.info("返回数据结果：{}", userById);
    }

    @GetMapping("/user/get/{id}/{dbName}")
    public void switchDB(@PathVariable("id") Integer id, @PathVariable("dbName") String dbName) throws SQLException {
        //动态创建数据源
        DBModel dbModel = new DBModel();
        dbModel.setDbName(dbName)
                .setId("2")
                .setUserName("root")
                .setPassword("123456")
                .setUrl("jdbc:mysql://localhost:3306/kamo1?useUnicode=true&characterEncoding=UTF-8&autoReconnect=true&failOverReadOnly=false");

        dbService.dynamicCreate(dbModel);
        User userById = userService.getUserById(1);
        log.info("返回数据结果：{}", userById);
        DynamicDataSource.setDataSource("defaultDataSource");
        User userById2 = userService.getUserById(1);
        log.info("返回数据结果：{}", userById2);
    }

    @GetMapping("/user/syncEvent")
    public void testEvent(){
        //createDataSource.createDataSource("userA");
        System.out.println("创建完了");
        log.info("测试数据源");
        User userById2 = userService.getUserById(1);
        log.info("返回数据结果：{}", userById2);
    }

    @GetMapping("/user/pub/{id}")
    public String pubMsg(@PathVariable String id){
        redisTemplate.convertAndSend("DruidDataSource","userA");
        return "success";
    }
}
