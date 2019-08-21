package com.itlike;

import com.alibaba.dubbo.spring.boot.annotation.EnableDubboConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author : 迟彪
 * @date : 2019/8/4
 */
@SpringBootApplication
@EnableDubboConfiguration
public class AntShop_Consumer {
    public static void main(String[] args) {
        SpringApplication.run(AntShop_Consumer.class,args);
    }
}
