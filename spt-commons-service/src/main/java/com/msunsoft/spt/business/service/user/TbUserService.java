package com.msunsoft.spt.business.service.user;


import com.msunsoft.spt.business.domain.user.TbUser;
import com.msunsoft.spt.business.service.common.BaseCrudService;

import java.util.List;


/**
 * @author zxl
 */
public interface TbUserService extends BaseCrudService<TbUser> {

    /**
     * 获取用户
     *
     * @param username 用户名
     * @return {@link TbUser}
     */
    TbUser get(String username);

    /**
     * 用户加角色
     * @param userId
     * @param role
     * @return
     */
    int addRole(long userId, List<Long> role);

    /**
     * 删除用户关联角色
     * @param id
     * @return
     */
    int userDelete(long id);
}