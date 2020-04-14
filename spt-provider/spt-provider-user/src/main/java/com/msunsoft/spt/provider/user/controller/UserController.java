package com.msunsoft.spt.provider.user.controller;

import com.msunsoft.spt.business.domain.user.TbUser;
import com.msunsoft.spt.business.dto.ResponseResult;
import com.msunsoft.spt.business.service.user.TbUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    /**
     * 用户添加角色
     * @param userId
     * @param role
     * @return
     */
    @PostMapping(value = "addrole/{userId}")
    public ResponseResult<Void> info(@PathVariable long userId, @RequestBody List<Long> role) {
        int i = tbUserService.addRole(userId, role);
        if(i>0){
            return new ResponseResult<Void>(ResponseResult.CodeStatus.OK, "添加角色成功");
        }else{
            return new ResponseResult<Void>(ResponseResult.CodeStatus.OK, "添加角色失败");
        }

    }

    /**
     * 删除用户
     * @param id
     * @return
     */

    @DeleteMapping("delete/{id}")
    public ResponseResult<Void> delete(@PathVariable long id) {

        int i = tbUserService.userDelete(id);
        // 成功
        if (i >0) {
            return new ResponseResult<Void>(ResponseResult.CodeStatus.OK, "删除角色信息成功");
        }
        // 失败
        else {
            return new ResponseResult<Void>(ResponseResult.CodeStatus.FAIL, "删除角色信息失败");
        }


    }

}
