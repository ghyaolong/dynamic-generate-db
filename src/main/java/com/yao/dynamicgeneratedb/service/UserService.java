package com.yao.dynamicgeneratedb.service;

import com.yao.dynamicgeneratedb.model.User;

/**
 * @author yaochenglong
 * @version 1.0
 * @Description: TODO
 * @date 2020/6/4 15:00
 */
public interface UserService {

    User getUserById(Integer id);
    User getUserById1(Integer id);
}
