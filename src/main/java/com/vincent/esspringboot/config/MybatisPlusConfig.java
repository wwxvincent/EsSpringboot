package com.vincent.esspringboot.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("com.vincent.esspringboot.**.mapper")
public class MybatisPlusConfig {
}
