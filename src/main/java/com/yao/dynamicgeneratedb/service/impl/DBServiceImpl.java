package com.yao.dynamicgeneratedb.service.impl;

import com.alibaba.druid.pool.DruidDataSource;
import com.yao.dynamicgeneratedb.dynamicdatasource.DynamicDataSource;
import com.yao.dynamicgeneratedb.model.DBModel;
import com.yao.dynamicgeneratedb.service.DBService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

/**
 * @author yaochenglong
 * @version 1.0
 * @Description: TODO
 * @date 2020/6/4 15:33
 */
@Slf4j
@Service
public class DBServiceImpl implements DBService {

    @Override
    public void dynamicCreate(DBModel dbModel) {
        Object o = DynamicDataSource.dataSourcesMap.get(dbModel.getId());
        if(o==null){
            DruidDataSource druidDataSource = new DruidDataSource();
            druidDataSource.setUrl("jdbc:mysql://localhost:3306/"+dbModel.getDbName()+"123?characterEncoding=utf-8&useSSL=false&serverTimezone=UTC&useAffectedRows=true");
            druidDataSource.setUsername(dbModel.getUserName());
            druidDataSource.setPassword(dbModel.getPassword());
            druidDataSource.setMaxActive(dbModel.getMaxActive());
            druidDataSource.setInitialSize(dbModel.getInitialSize());
            //设置true，如果druidDataSource.init();异常的情况下，不会不停地输出错误，
            druidDataSource.setBreakAfterAcquireFailure(true);
            try {
                druidDataSource.init();
            } catch (SQLException throwables) {
                log.error(throwables.getMessage());
                log.error("创建数据库连接失败",throwables);
                new Exception(throwables.getMessage());
                return;
            }
            DynamicDataSource.dataSourcesMap.put(dbModel.getId(), druidDataSource);
        }
        DynamicDataSource.setDataSource(dbModel.getId());

        //此时数据源已切换到druidDataSource ，调用自己的业务方法即可。
        //使用完后调用DynamicDataSource.clear();重置为默认数据源。
    }
}
