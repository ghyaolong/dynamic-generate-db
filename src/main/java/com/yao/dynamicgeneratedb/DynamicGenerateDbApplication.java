package com.yao.dynamicgeneratedb;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.yao.dynamicgeneratedb.dao")
@SpringBootApplication
public class DynamicGenerateDbApplication {

    public static void main(String[] args) {
        SpringApplication.run(DynamicGenerateDbApplication.class, args);
    }

}
