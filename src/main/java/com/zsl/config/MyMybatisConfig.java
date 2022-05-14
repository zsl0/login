package com.zsl.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @Author zsl
 * @Date 2021/12/29 11:34
 */
@Configuration
@MapperScan(basePackages = "com.zsl.mapper")
public class MyMybatisConfig {

}
