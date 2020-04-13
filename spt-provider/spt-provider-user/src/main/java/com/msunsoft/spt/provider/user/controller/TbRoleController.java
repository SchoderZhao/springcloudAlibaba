package com.msunsoft.spt.provider.user.controller;

import com.github.pagehelper.PageInfo;
import com.msunsoft.spt.business.domain.user.TbRole;
import com.msunsoft.spt.business.dto.ResponseResult;
import com.msunsoft.spt.business.service.user.TbRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zxl
 * @create 2020/4/13 0013
 */
@RestController
@RequestMapping("role")
public class TbRoleController {
    @Autowired
    private TbRoleService tbRoleService;


    @PostMapping("/view/{num}/{size}")
    public ResponseResult<PageInfo<TbRole>> view(
            @PathVariable int num,
            @PathVariable int size
    ) {

        PageInfo<TbRole> page = tbRoleService.page(null, num, size);
        return new ResponseResult<PageInfo<TbRole>>(ResponseResult.CodeStatus.OK, "获取角色信息", page);

    }

    @PostMapping("/insert")
    public ResponseResult<TbRole> save(@RequestBody TbRole tbRole) {

        TbRole save = tbRoleService.save(tbRole);
        // 成功
        if (save != null) {
            return new ResponseResult<TbRole>(ResponseResult.CodeStatus.OK, "新增角色信息成功",save);
        }
        // 失败
        else {
            return new ResponseResult<TbRole>(ResponseResult.CodeStatus.FAIL, "新增角色信息失败",save);
        }


    }

    @PatchMapping("/update/{id}")
    public ResponseResult<Void> update(@RequestBody TbRole tbRole,@PathVariable long id) {

        tbRole.setId(id);
        int i = tbRoleService.updateSelective(tbRole);
        // 成功
        if (i >0) {
            return new ResponseResult<Void>(ResponseResult.CodeStatus.OK, "修改角色信息成功");
        }
        // 失败
        else {
            return new ResponseResult<Void>(ResponseResult.CodeStatus.FAIL, "修改角色信息失败");
        }


    }

    /**
     * 删除角色
     * @param id
     * @return
     */
    @DeleteMapping("/delete/{id}")
    public ResponseResult<Void> delete(@PathVariable long id) {

        int i = tbRoleService.roleDelete(id);
        // 成功
        if (i >0) {
            return new ResponseResult<Void>(ResponseResult.CodeStatus.OK, "删除角色信息成功");
        }
        // 失败
        else {
            return new ResponseResult<Void>(ResponseResult.CodeStatus.FAIL, "删除角色信息失败");
        }


    }

    /**
     * 角色添加接口
     * @param roleId
     * @param permission
     * @return
     */

    @PostMapping(value = "addPermission/{roleId}")
    public ResponseResult<Void> info(@PathVariable long roleId, @RequestBody List<Long> permission) {
        int i = tbRoleService.addTbPermission(roleId,permission);
        if(i>0){
            return new ResponseResult<Void>(ResponseResult.CodeStatus.OK, "添加接口成功");
        }else{
            return new ResponseResult<Void>(ResponseResult.CodeStatus.OK, "添加接口失败");
        }

    }

}
