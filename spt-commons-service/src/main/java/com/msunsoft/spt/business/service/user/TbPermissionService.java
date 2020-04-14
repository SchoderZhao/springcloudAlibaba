package com.msunsoft.spt.business.service.user;

import com.msunsoft.spt.business.domain.user.TbPermission;
import com.msunsoft.spt.business.service.common.BaseCrudService;

import java.util.List;
import java.util.Map;

/**
 * @author zxl
 * @create 2020/4/9 0009
 */
public interface TbPermissionService extends BaseCrudService<TbPermission> {

    List<TbPermission> selectByUserId(Long userId);

    /**
     * 删除惨淡关联角色菜单
     * @param id
     * @return
     */
    int permissionDelete(long id);

    List<Map<String, Object>> loadPermissionList(long roleId);

}
