package com.baizhi.dao;

import com.baizhi.entity.Employ;
import com.baizhi.rediscache.RedisCache;
import org.apache.ibatis.annotations.CacheNamespace;
import tk.mybatis.mapper.common.Mapper;

/**
 * author : 张京斗
 * create_date : 2019/12/6 11:27
 * version : 1.0
 */
@CacheNamespace(implementation = RedisCache.class)
public interface EmployDao extends Mapper<Employ> {
}
