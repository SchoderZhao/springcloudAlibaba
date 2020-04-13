package com.msunsoft.spt.business.service.user.impl;

import com.msunsoft.spt.business.domain.user.TbPermission;
import com.msunsoft.spt.business.domain.user.TbRolePermission;
import com.msunsoft.spt.business.mapper.user.TbPermissionMapper;
import com.msunsoft.spt.business.mapper.user.TbRolePermissionMapper;
import com.msunsoft.spt.business.service.common.impl.BaseCrudServiceImpl;
import com.msunsoft.spt.business.service.user.TbPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author zxl
 * @create 2020/4/9 0009
 */
@Service
public class TbPermissionServiceImpl extends BaseCrudServiceImpl<TbPermission, TbPermissionMapper> implements TbPermissionService {

    @Autowired
    private TbPermissionMapper tbPermissionMapper;
    @Autowired
    private TbRolePermissionMapper tbRolePermissionMapper;

    @Override
    public List<TbPermission> selectByUserId(Long userId) {
        return tbPermissionMapper.selectByUserId(userId);
    }

    @Transactional
    @Override
    public int permissionDelete(long id) {
        int delete=0;
        TbRolePermission tbRolePermission =new TbRolePermission();
        tbRolePermission.setPermissionId(id);
        delete=tbPermissionMapper.deleteByPrimaryKey(id);
        delete = tbRolePermissionMapper.delete(tbRolePermission);

        return delete;
    }
}
