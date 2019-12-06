package com.baizhi.service;

import com.baizhi.entity.Deplement;

import java.util.List;

/**
 * author : 张京斗
 * create_date : 2019/12/6 11:29
 * version : 1.0
 */
public interface DeplementService {
    List<Deplement> findByPage(Integer nowpage, Integer pagesize);

    void addOne(Deplement deplement);

    void updateOne(Deplement deplement);

    void deleteOne(String deptid);
}
