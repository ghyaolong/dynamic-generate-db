//package com.yao.dynamicgeneratedb.springwatch;
//
//import com.alibaba.druid.pool.DruidDataSource;
//import com.yao.dynamicgeneratedb.dynamicdatasource.DynamicDataSource;
//import com.yao.dynamicgeneratedb.model.DBModel;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.context.ApplicationListener;
//import org.springframework.scheduling.annotation.Async;
//import org.springframework.stereotype.Component;
//
//import java.sql.SQLException;
//
///**
// * @author yaochenglong
// * @version 1.0
// * @Description: TODO
// * @date 2020/6/5 13:51
// */
//@Slf4j
//@Component
//public class CreateDataSourceListener implements ApplicationListener<CreateDataSourceEvent> {
//
//    //@Async
//    @Override
//    public void onApplicationEvent(CreateDataSourceEvent event) {
//        DBModel dbModel = event.getDbModel();
//        //动态创建数据源
//        dynamicCreate(dbModel);
//        log.info("给租户{}创建了数据源",dbModel.getCustomerName());
//    }
//
//    private void dynamicCreate(DBModel dbModel) {
//        Object o = DynamicDataSource.dataSourcesMap.get(dbModel.getId());
//        if(o==null){
//            DruidDataSource druidDataSource = new DruidDataSource();
//            druidDataSource.setUrl("jdbc:mysql://localhost:3306/"+dbModel.getDbName()+" ?characterEncoding=utf-8&useSSL=false&serverTimezone=UTC&useAffectedRows=true");
//            druidDataSource.setUsername(dbModel.getUserName());
//            druidDataSource.setPassword(dbModel.getPassword());
//            druidDataSource.setMaxActive(dbModel.getMaxActive());
//            druidDataSource.setInitialSize(dbModel.getInitialSize());
//            //设置true，如果druidDataSource.init();异常的情况下，不会不停地输出错误，
//            druidDataSource.setBreakAfterAcquireFailure(true);
//            try {
//                druidDataSource.init();
//            } catch (SQLException throwables) {
//                log.error(throwables.getMessage());
//                log.error("创建数据库连接失败",throwables);
//                new Exception(throwables.getMessage());
//                return;
//            }
//            DynamicDataSource.dataSourcesMap.put(dbModel.getId(), druidDataSource);
//        }
//        DynamicDataSource.setDataSource(dbModel.getId());
//
//        //此时数据源已切换到druidDataSource ，调用自己的业务方法即可。
//        //使用完后调用DynamicDataSource.clear();重置为默认数据源。
//    }
//}
