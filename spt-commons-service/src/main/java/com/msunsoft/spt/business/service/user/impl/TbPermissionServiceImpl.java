package com.msunsoft.spt.business.service.user.impl;

import com.msunsoft.spt.business.domain.user.TbPermission;
import com.msunsoft.spt.business.mapper.user.TbPermissionMapper;
import com.msunsoft.spt.business.service.user.TbPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zxl
 * @create 2020/4/9 0009
 */
@Service
public class TbPermissionServiceImpl implements TbPermissionService {

    @Autowired
    private TbPermissionMapper tbPermissionMapper;

    @Override
    public List<TbPermission> selectByUserId(Long userId) {
        return tbPermissionMapper.selectByUserId(userId);
    }
}
