package com.yao.dynamicgeneratedb.service;

import com.yao.dynamicgeneratedb.model.DBModel;

import java.sql.SQLException;

/**
 * @author yaochenglong
 * @version 1.0
 * @Description: TODO
 * @date 2020/6/4 15:31
 */
public interface DBService {

    void dynamicCreate(DBModel dbModel) throws SQLException;

}
