package com.msunsoft.spt.business.reg;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @author zxl
 * @create 2020/3/12 0012
 */
@SpringBootApplication(scanBasePackages = "com.msunsoft.spt")
@EnableDiscoveryClient
@MapperScan(basePackages = "com.msunsoft.spt.business.mapper.user")

public class SptServiceRegApplication {
    public static void main(String[] args) {
        SpringApplication.run(SptServiceRegApplication.class, args);
    }
}
