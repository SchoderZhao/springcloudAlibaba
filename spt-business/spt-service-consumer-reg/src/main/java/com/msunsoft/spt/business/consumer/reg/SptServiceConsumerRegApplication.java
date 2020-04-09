package com.msunsoft.spt.business.consumer.reg;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author zxl
 * @create 2020/3/13 0013
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class SptServiceConsumerRegApplication {
    public static void main(String[] args) {
        SpringApplication.run(SptServiceConsumerRegApplication.class,args);
    }
}
