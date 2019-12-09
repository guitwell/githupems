package com.baizhi.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * author : 张京斗
 * create_date : 2019/12/8 14:23
 * version : 1.0
 */
@Aspect
@Configuration
public class RedisAspect {

    //获得注入的RedisTemplate
    @Autowired
    private RedisTemplate redisTemplate;

    //添加缓存
    @Around("@annotation(com.baizhi.annotation.AddCache)")
    public Object addCache(ProceedingJoinPoint proceedingJoinPoint) {
        Object proceed = null;
        try {
            //方法全名为redis存储时的hash的大key
            String clazzname = proceedingJoinPoint.getSignature().toString();
            //方法名会与参数列表拼接成小key
            String name = proceedingJoinPoint.getSignature().getName();
            //获取参数列表
            Object[] args = proceedingJoinPoint.getArgs();
            //拼接出新名字
            for (Object arg : args) {
                name = name + arg;
            }
            Object o = redisTemplate.opsForHash().get(clazzname, name);
            //判断是否有该数据的缓存
            if (o != null) {
                return o;
            } else {
                proceed = proceedingJoinPoint.proceed();
                //存入redis
                redisTemplate.opsForHash().put(clazzname, name, proceed);
            }
            //继续向下执行
            return proceed;
        } catch (Throwable throwable) {
            //打印错误信息并停止执行
            throwable.printStackTrace();
            return null;
        }
    }

    //清空缓存
    @Around("@annotation(com.baizhi.annotation.DelCache)")
    public Object delCache(ProceedingJoinPoint proceedingJoinPoint) {
        try {
            Object proceed = proceedingJoinPoint.proceed();
            //方法全名为redis存储时的hash的大key
            String clazzname = proceedingJoinPoint.getSignature().toString();
            System.out.println("方法全名" + clazzname);
            //方法名会与参数列表拼接成小key
            String name = proceedingJoinPoint.getSignature().getName();
            //获取参数列表
            Object[] args = proceedingJoinPoint.getArgs();
            //拼接出新名字
            for (Object arg : args) {
                name = name + arg;
            }
            System.out.println("新名字" + name);
            System.out.println("查出来的数据" + proceed);

            Object o = redisTemplate.opsForHash().get(clazzname, name);
            //判断是否有该数据的缓存
            if (o != null) {
                //如果有就清空该部分缓存
                redisTemplate.opsForHash().delete(clazzname);
            }
            //继续向下执行
            return proceed;
        } catch (Throwable throwable) {
            //打印错误信息并停止执行
            throwable.printStackTrace();
            return null;
        }

    }
}
