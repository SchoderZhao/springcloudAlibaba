package com.msunsoft.spt.business.service.user;


import com.msunsoft.spt.business.domain.user.TbUser;
import com.msunsoft.spt.business.service.common.BaseCrudService;


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
}