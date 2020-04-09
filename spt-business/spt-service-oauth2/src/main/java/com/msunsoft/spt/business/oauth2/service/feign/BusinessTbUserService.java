package com.msunsoft.spt.business.oauth2.service.feign;

import com.msunsoft.spt.business.oauth2.service.feign.impl.BusinessTbUserServiceFallBack;
import com.msunsoft.spt.business.configuration.FeignRequestConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author zxl
 * @create 2020/4/7 12:11
 */
@FeignClient(value = "spt-provider-user", configuration = FeignRequestConfiguration.class, fallback = BusinessTbUserServiceFallBack.class)
public interface BusinessTbUserService {

    @GetMapping(value = "/user/info/{username}")
    String info(@PathVariable(name = "username")  String username);

    @GetMapping(value = "/permission/info/{userId}")
    String permissionInfo(@PathVariable(name = "userId")  long username);

}
