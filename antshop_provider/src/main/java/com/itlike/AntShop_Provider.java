package com.itlike;

import com.alibaba.dubbo.spring.boot.annotation.EnableDubboConfiguration;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 * @author : 迟彪
 * @date : 2019/8/4
 */
@SpringBootApplication
@EnableDubboConfiguration
@MapperScan("com.itlike.mapper")
public class AntShop_Provider {
    public static void main(String[] args) {
        SpringApplication.run(AntShop_Provider.class,args);
    }
}
