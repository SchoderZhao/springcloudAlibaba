package com.msunsoft.spt.provider.user.controller;

import com.msunsoft.spt.business.domain.user.TbPermission;
import com.msunsoft.spt.business.domain.user.TbUser;
import com.msunsoft.spt.business.dto.ResponseResult;
import com.msunsoft.spt.business.service.user.TbPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author zxl
 * @create 2020/4/9 0009
 */
@RestController
@RequestMapping("/permission")
public class TbPermissionController {
    @Autowired
    private TbPermissionService tbPermissionService;

    @GetMapping("info/{userId}")
    public ResponseResult<List<TbPermission>> info(@PathVariable Long userId){

        List<TbPermission> tbPermissions = tbPermissionService.selectByUserId(userId);

        return new ResponseResult<List<TbPermission>>(ResponseResult.CodeStatus.OK, "获取个人信息", tbPermissions);


    }
}
