package com.yao.dynamicgeneratedb.watch;

import com.google.common.collect.Lists;
import org.springframework.stereotype.Component;

import java.util.List;


/**
 * @author yaochenglong
 * @version 1.0
 * @Description: TODO
 * @date 2020/6/5 10:01
 */
//创建事件源
@Component
public class KamoDataSourceEventSource {

    //定义一个事件监听器
    private List<Listener> listListener;

    public KamoDataSourceEventSource() {
        this.listListener = Lists.newArrayList();
    }

    public void createDataSource(){
        for (Listener listener : listListener) {
            if(listener instanceof CreateDataSourceListener){
                CreateDataSourceListener createDataSourceListener = (CreateDataSourceListener)listener;
                createDataSourceListener.fireAfterEventInvoked(new CreateDataSourceEvent(this,"创建租户数据库连接"));
            }
        }
        listListener = Lists.newArrayList();
        System.out.println("请求创建租户数据源");
    }
    public void addListener(Listener listener){
        this.listListener.add(listener);
    }
}
