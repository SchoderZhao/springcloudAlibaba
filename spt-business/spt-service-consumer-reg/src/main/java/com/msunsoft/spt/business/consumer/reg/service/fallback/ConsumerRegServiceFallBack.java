package com.msunsoft.spt.business.consumer.reg.service.fallback;

import com.msunsoft.spt.business.consumer.reg.service.ConsumerRegService;
import org.springframework.stereotype.Component;

/**
 * @author zxl
 * @create 2020/3/13 0013
 */
@Component
public class ConsumerRegServiceFallBack implements ConsumerRegService {

    @Override
    public String reg(long id) {
        return "请稍后再试";
    }
}
