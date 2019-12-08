package com.baizhi.rediscache;

import org.apache.ibatis.cache.Cache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * author : 张京斗
 * create_date : 2019/12/8 10:05
 * version : 1.0
 */
/*
    1. 给一个有参构造及一个id属性
    2. 实现getId()方法
 */
public class RedisCache implements Cache {

    private String id;

    //获得注入的RedisTemplate
    @Autowired
    private RedisTemplate redisTemplate;

    public RedisCache(String id) {
        System.out.println("id为" + id);
        this.id = id;
    }

    @Override
    public String getId() {
        return this.id;
    }

    //放入缓存 //redisTemplate StringRedisTemplate   RedisTemplate
    @Override
    public void putObject(Object key, Object value) {
        //调用工厂类获取对象
        RedisTemplate redisTemplate = (RedisTemplate) ApplicationContextUtils.getBean("redisTemplate");
        //存储缓存数据
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        //设置hashkey序列化
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        //hashValue非常复杂不要序列化
        //hash模型
        redisTemplate.opsForHash().put(id.toString(), key.toString(), value);
        System.out.println("加入缓存的hash为:id" + id + ";key为：" + key.toString() + ";value为:" + value);
    }

    //从缓存中获取对象
    @Override
    public Object getObject(Object key) {
        //获取对象
        RedisTemplate redisTemplate = (RedisTemplate) ApplicationContextUtils.getBean("redisTemplate");
        //存储缓存数据
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        //设置hashkey序列化
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        System.out.println("从缓存中获取的hash为:id" + id + ";key为：" + key.toString() + ";value为:" + redisTemplate.opsForHash().get(id.toString(), key.toString()));
        //将获取的数据返回去
        return redisTemplate.opsForHash().get(id.toString(), key.toString());
    }

    //根据key删除缓存中的数据（未实现）
    //一般直接清空
    @Override
    public Object removeObject(Object o) {
        return null;
    }

    //执行写操作时根据key清空指定缓存
    @Override
    public void clear() {
        RedisTemplate redisTemplate = (RedisTemplate) ApplicationContextUtils.getBean("redisTemplate");
        System.out.println("执行了写操作清空" + id.toString() + "的缓存");
        //存储缓存数据
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        //清空当前namespace对应map
        redisTemplate.delete(id.toString());
    }

    //缓存命中率计算
    @Override
    public int getSize() {
        RedisTemplate redisTemplate = (RedisTemplate) ApplicationContextUtils.getBean("redisTemplate");
        //key的序列化
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        System.out.println("当前查询的命中率为" + redisTemplate.opsForHash().size(id.toString()).intValue());
        //获取当前namespace中缓存数据
        return redisTemplate.opsForHash().size(id.toString()).intValue();
    }

    //读写锁   ReadWriteLock   写写互斥  读写不互斥 读读不互斥  synchronized  读 读 互斥  读写 互斥
    @Override
    public ReadWriteLock getReadWriteLock() {
        return new ReentrantReadWriteLock();
    }
}
