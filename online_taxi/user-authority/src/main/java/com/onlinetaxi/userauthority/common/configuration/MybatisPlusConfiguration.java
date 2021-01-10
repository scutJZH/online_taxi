package com.onlinetaxi.userauthority.common.configuration;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan(basePackages = "com.onlinetaxi.userauthority.**.dao")
public class MybatisPlusConfiguration {
}
