package com.msunsoft.spt.business.oauth2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author zxl
 * @create 2020/3/19 0019
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class SptServiceOAuth2Application {

    public static void main(String[] args) {
        SpringApplication.run(SptServiceOAuth2Application.class, args);
    }

}
