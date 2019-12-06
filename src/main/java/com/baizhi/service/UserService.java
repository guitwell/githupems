package com.baizhi.service;

import com.baizhi.entity.User;

/**
 * author : 张京斗
 * create_date : 2019/12/6 11:29
 * version : 1.0
 */
public interface UserService {
    User findOne(String username);
}
