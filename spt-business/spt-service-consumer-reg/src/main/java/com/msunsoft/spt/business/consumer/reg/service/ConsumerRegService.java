package com.msunsoft.spt.business.consumer.reg.service;

import com.msunsoft.spt.business.consumer.reg.service.fallback.ConsumerRegServiceFallBack;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author zxl
 * @create 2020/3/13 0013
 */
@FeignClient(value = "spt-oauth2-reg", fallback = ConsumerRegServiceFallBack.class)
public interface ConsumerRegService {
    @GetMapping(value = "/reg/{id}")
    String reg(@PathVariable(name = "id") long id);

}
