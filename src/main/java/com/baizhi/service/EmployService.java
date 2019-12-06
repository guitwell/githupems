package com.baizhi.service;

import com.baizhi.entity.Employ;

import java.util.List;

/**
 * author : 张京斗
 * create_date : 2019/12/6 11:29
 * version : 1.0
 */
public interface EmployService {
    List<Employ> findByPage(Integer nowpage, Integer pagesize, String deptid);

    void addOne(Employ employ);

    void updateOne(Employ employ);

    void deleteOne(Employ employ);

    Integer getCount();
}
