package com.msunsoft.spt.business.service.user.impl;

import com.msunsoft.spt.business.domain.user.TbRole;
import com.msunsoft.spt.business.domain.user.TbRolePermission;
import com.msunsoft.spt.business.domain.user.TbUserRole;
import com.msunsoft.spt.business.mapper.user.TbRoleMapper;
import com.msunsoft.spt.business.mapper.user.TbRolePermissionMapper;
import com.msunsoft.spt.business.mapper.user.TbUserRoleMapper;
import com.msunsoft.spt.business.service.common.impl.BaseCrudServiceImpl;
import com.msunsoft.spt.business.service.user.TbRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zxl
 * @create 2020/4/13 0013
 */
@Service
public class TbRoleServiceImpl extends BaseCrudServiceImpl<TbRole, TbRoleMapper> implements TbRoleService {
    @Autowired
    private TbRolePermissionMapper tbRolePermissionMapper;

    @Autowired
    private TbUserRoleMapper tbUserRoleMapper;

    @Autowired
    private TbRoleMapper tbRoleMapper;

    @Transactional
    @Override
    public int addTbPermission(long roleId, List<Long> permission) {
        int i=0;
        TbRolePermission tbRolePermission = new TbRolePermission();
        tbRolePermission.setRoleId(roleId);
        tbRolePermissionMapper.delete(tbRolePermission);
        for (Long aLong : permission) {
            tbRolePermission.setId(null);
            tbRolePermission.setPermissionId(aLong);
            i = tbRolePermissionMapper.insert(tbRolePermission);

        }

        return i;
    }

    @Transactional
    @Override
    public int roleDelete(long id) {
        int i = 0;

        TbRolePermission tbRolePermission= new TbRolePermission();
        TbUserRole tbUserRole=new TbUserRole();
        tbRolePermission.setRoleId(id);
        tbUserRole.setRoleId(id);
        try {
            tbRolePermissionMapper.delete(tbRolePermission);
            tbUserRoleMapper.delete(tbUserRole);
            tbRoleMapper.deleteByPrimaryKey(id);
            i=1;
        }catch (Exception e){
            return 0;
        }

        return i;
    }


}
