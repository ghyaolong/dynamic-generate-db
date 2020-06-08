package com.yao.dynamicgeneratedb.service.impl;

import com.yao.dynamicgeneratedb.annotation.DataSource;
import com.yao.dynamicgeneratedb.dao.UserDao;
import com.yao.dynamicgeneratedb.model.User;
import com.yao.dynamicgeneratedb.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author yaochenglong
 * @version 1.0
 * @Description: TODO
 * @date 2020/6/4 15:01
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserDao userDao;

    @DataSource
    @Override
    public User getUserById(Integer id) {
        return userDao.selectByPrimaryKey(id);
    }

    @Override
    public User getUserById1(Integer id) {
        return userDao.selectByPrimaryKey(id);
    }
}
