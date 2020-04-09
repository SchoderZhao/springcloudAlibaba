package com.msunsoft.spt.business.service.user;

import com.msunsoft.spt.business.domain.user.TbPermission;
import com.msunsoft.spt.business.service.common.BaseCrudService;

import java.util.List;

/**
 * @author zxl
 * @create 2020/4/9 0009
 */
public interface TbPermissionService extends BaseCrudService<TbPermission> {

    List<TbPermission> selectByUserId(Long userId);

}
