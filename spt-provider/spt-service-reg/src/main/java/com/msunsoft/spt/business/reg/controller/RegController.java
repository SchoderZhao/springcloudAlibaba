package com.msunsoft.spt.business.reg.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zxl
 * @create 2020/3/12 0012
 */
@RestController
@RequestMapping(value = "reg")
public class RegController {


    /**
     * 根据 ID 测试查询用户信息
     * @param id
     * @return
     */
    @GetMapping(value = {"{id}"})
    public long reg(@PathVariable long id) {

        return id;
    }
}
