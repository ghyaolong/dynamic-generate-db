package com.yao.dynamicgeneratedb.watch;

/**
 * @author yaochenglong
 * @version 1.0
 * @Description: TODO
 * @date 2020/6/5 11:19
 */
public class Test {
    public static void main(String[] args) {
        KamoDataSourceEventSource kamoDataSourceEventSource = new KamoDataSourceEventSource();
        for (int i = 0; i < 10; i++) {
            kamoDataSourceEventSource.addListener(new CreateDataSourceListener("userKey"));
        }
        kamoDataSourceEventSource.createDataSource();
    }
}
