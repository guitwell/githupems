package com.baizhi.rediscache;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * author : 张京斗
 * create_date : 2019/12/8 10:15
 * version : 1.0
 */
//将这个类交由工厂创建
@Component
public class ApplicationContextUtils implements ApplicationContextAware {

    private static ApplicationContext context;

    //实现核心方法
    @Override
    //参数为已经创建好的工厂对象
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        //将这个对象赋给自己定义的参数
        this.context = applicationContext;
    }

    //工具方法
    //根据id获取对应的对象
    public static Object getBean(String id) {
        //调用自己从工厂获取的方法来获取自己需要的对象
        return context.getBean(id);
    }

    //根据类型获取工厂中的对象(直接作为重载)
    public static Object getBean(Class clazz) {
        return context.getBean(clazz);
    }

    //根据方法名和方法类型获取工厂中的对象(再重载)
    public static Object getBean(String id, Class clazz) {
        return context.getBean(id, clazz);
    }
}
