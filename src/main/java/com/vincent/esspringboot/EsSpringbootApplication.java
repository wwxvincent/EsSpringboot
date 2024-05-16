package com.vincent.esspringboot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

@SpringBootApplication
@EnableSwagger2WebMvc
//@MapperScan("com.vincent.gptspringbootmd.mapper") // 指定 Mapper 接口的扫描路径
@ComponentScan(basePackages = {"com.vincent.esspringboot.*"})
public class EsSpringbootApplication {

    static Logger logger = LoggerFactory.getLogger(EsSpringbootApplication.class);


    public static void main(String[] args) {
        if (logger.isDebugEnabled()) {
            logger.debug("启动elastic...");
        }
        SpringApplication.run(EsSpringbootApplication.class, args);
    }

}
