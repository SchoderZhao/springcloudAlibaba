package com.msunsoft.spt.business.consumer.reg.controller;

import com.msunsoft.spt.business.consumer.reg.service.ConsumerRegService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zxl
 * @create 2020/3/13 0013
 */
@RestController
@RequestMapping(value = "reg")
public class ConsumerRegController {
    @Autowired
    private ConsumerRegService consumerRegService;

    /**
     *
     * @param id
     * @return
     */
    @GetMapping(value ="consumer/{id}")
    public String reg(@PathVariable long id){
        return consumerRegService.reg(id);
    }
}
