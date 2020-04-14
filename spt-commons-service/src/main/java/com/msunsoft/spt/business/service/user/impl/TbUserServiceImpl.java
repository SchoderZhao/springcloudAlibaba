package com.msunsoft.spt.business.service.user.impl;


import com.msunsoft.spt.business.domain.user.TbUser;
import com.msunsoft.spt.business.domain.user.TbUserRole;
import com.msunsoft.spt.business.mapper.user.TbUserMapper;
import com.msunsoft.spt.business.mapper.user.TbUserRoleMapper;
import com.msunsoft.spt.business.service.common.impl.BaseCrudServiceImpl;
import com.msunsoft.spt.business.service.user.TbUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @author zxl
 * @create 2020/4/3 0003
 */
@Service
public class TbUserServiceImpl extends BaseCrudServiceImpl<TbUser, TbUserMapper> implements TbUserService {
    @Autowired
    private TbUserMapper tbUserMapper;

    @Autowired
    private TbUserRoleMapper tbUserRoleMapper;

    @Override
    public TbUser get(String username) {
        Example example = new Example(TbUser.class);
        example.createCriteria().andEqualTo("username", username);
        return tbUserMapper.selectOneByExample(example);
    }

    @Transactional
    @Override
    public int addRole(long userId, List<Long> role) {

        int i=0;
        TbUserRole tbUserRole = new TbUserRole();
        tbUserRole.setId(null);
        tbUserRole.setUserId(userId);
        for (Long aLong : role) {
            tbUserRole.setRoleId(aLong);
            i = tbUserRoleMapper.insert(tbUserRole);

        }

        return i;
    }

    @Transactional
    @Override
    public int userDelete(long id) {
        int i = 0;

        try {
            tbUserMapper.deleteByPrimaryKey(id);
            TbUserRole tbUserRole= new TbUserRole();
            tbUserRole.setUserId(id);
            tbUserRoleMapper.delete(tbUserRole);
            i=1;
        }catch (Exception e){
            return 0;
        }


        return i;
    }
}
