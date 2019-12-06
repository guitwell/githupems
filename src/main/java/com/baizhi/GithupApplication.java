package com.baizhi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;


/**
 * author : 张京斗
 * create_date : 2019/12/6 10:59
 * version : 1.0
 */
@SpringBootApplication
@MapperScan("com.baizhi.dao")
public class GithupApplication {
    public static void main(String[] args) {
        SpringApplication.run(GithupApplication.class, args);
    }
}
