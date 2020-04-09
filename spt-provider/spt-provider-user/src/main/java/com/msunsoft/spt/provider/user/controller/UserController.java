package com.msunsoft.spt.provider.user.controller;

import com.msunsoft.spt.business.domain.user.TbUser;
import com.msunsoft.spt.business.dto.ResponseResult;
import com.msunsoft.spt.business.service.user.TbUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author zxl
 * @create 2020/4/2 0002
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private TbUserService tbUserService;

    /**
     * 获取个人信息
     *
     * @param username {@code String} 用户名
     * @return {@link ResponseResult}
     */
    @GetMapping(value = "info/{username}")
    public ResponseResult<TbUser> info(@PathVariable String username) {
        TbUser tbUser = tbUserService.get(username);
        return new ResponseResult<TbUser>(ResponseResult.CodeStatus.OK, "获取个人信息", tbUser);
    }
}
