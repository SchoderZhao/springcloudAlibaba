package com.msunsoft.spt.provider.user.controller;

import com.github.pagehelper.PageInfo;
import com.msunsoft.spt.business.domain.user.TbPermission;
import com.msunsoft.spt.business.dto.ResponseResult;
import com.msunsoft.spt.business.service.user.TbPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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

        return new ResponseResult<List<TbPermission>>(ResponseResult.CodeStatus.OK, "获取菜单信息", tbPermissions);


    }

    

    @PostMapping("/view/{num}/{size}")
    public ResponseResult<PageInfo<TbPermission>> view(
            @PathVariable int num,
            @PathVariable int size
    ) {

        PageInfo<TbPermission> page = tbPermissionService.page(null, num, size);
        return new ResponseResult<PageInfo<TbPermission>>(ResponseResult.CodeStatus.OK, "获取菜单信息", page);

    }

    @PostMapping("/insert")
    public ResponseResult<TbPermission> save(@RequestBody TbPermission tbPermission) {

        TbPermission save = tbPermissionService.save(tbPermission);
        // 成功
        if (save != null) {
            return new ResponseResult<TbPermission>(ResponseResult.CodeStatus.OK, "新增菜单信息成功",save);
        }
        // 失败
        else {
            return new ResponseResult<TbPermission>(ResponseResult.CodeStatus.FAIL, "新增菜单信息失败",save);
        }


    }

    @PatchMapping("/update/{id}")
    public ResponseResult<Void> update(@RequestBody TbPermission tbPermission,@PathVariable long id) {

        tbPermission.setId(id);
        int i = tbPermissionService.updateSelective(tbPermission);
        // 成功
        if (i >0) {
            return new ResponseResult<Void>(ResponseResult.CodeStatus.OK, "修改菜单信息成功");
        }
        // 失败
        else {
            return new ResponseResult<Void>(ResponseResult.CodeStatus.FAIL, "修改菜单信息失败");
        }


    }

    @DeleteMapping("/delete/{id}")
    public ResponseResult<Void> delete(@PathVariable long id) {

        int i = tbPermissionService.permissionDelete(id);
        // 成功
        if (i >0) {
            return new ResponseResult<Void>(ResponseResult.CodeStatus.OK, "删除菜单信息成功");
        }
        // 失败
        else {
            return new ResponseResult<Void>(ResponseResult.CodeStatus.FAIL, "删除菜单信息失败");
        }


    }

    /**
     * 根据角色id获取菜单树
     *
     * @param roleId
     * @return
     */
    @GetMapping(value = "loadPermissionList/{roleId}")
    public ResponseResult<List<Map<String, Object>>> loadResourceList(@PathVariable long roleId) {

        List<Map<String, Object>> maps = tbPermissionService.loadPermissionList(roleId);
        return new ResponseResult<List<Map<String, Object>>>(ResponseResult.CodeStatus.OK, "根据角色id获取菜单树",maps);

    }
}
