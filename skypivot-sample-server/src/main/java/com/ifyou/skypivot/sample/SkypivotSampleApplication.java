package com.ifyou.skypivot.sample;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author GW00305020
 * @ClassName SkypivotSampleApplication
 * @description: TODO
 * @date 2023年09月22日
 * @version: 1.0
 */
@ComponentScan(basePackages = {"com.ifyou.skypivot.sample.**"})
@EnableFeignClients(basePackages ={"com.ifyou.skypivot.**"})
@EnableDiscoveryClient
@SpringBootApplication
@Slf4j
public class SkypivotSampleApplication {
    public static void main(String[] args) {
        log.info("获取到的系统变量: NACOS_ADDR: {}", System.getProperty("NACOS_ADDR"));
        SpringApplication.run(SkypivotSampleApplication.class);
    }
}
