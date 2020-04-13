package com.msunsoft.spt.business.service.user;

import com.msunsoft.spt.business.domain.user.TbRole;
import com.msunsoft.spt.business.service.common.BaseCrudService;

import java.util.List;

/**
 * @author zxl
 * @create 2020/4/13 0013
 */
public interface TbRoleService extends BaseCrudService<TbRole> {

    /**
     * 角色加菜单
     * @param roleId
     * @param permission
     * @return
     */
    int addTbPermission(long roleId, List<Long> permission);

    /**
     * 删除角色关联用户角色和角色菜单
     * @param id
     * @return
     */
    int roleDelete(long id);
}
