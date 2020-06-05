package com.yao.dynamicgeneratedb;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@MapperScan("com.yao.dynamicgeneratedb.dao")
@SpringBootApplication
@EnableAsync
public class DynamicGenerateDbApplication {

    public static void main(String[] args) {
        SpringApplication.run(DynamicGenerateDbApplication.class, args);
    }

}
