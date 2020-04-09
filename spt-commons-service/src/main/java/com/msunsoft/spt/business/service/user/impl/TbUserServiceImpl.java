package com.msunsoft.spt.business.service.user.impl;


import com.msunsoft.spt.business.domain.user.TbUser;
import com.msunsoft.spt.business.mapper.user.TbUserMapper;
import com.msunsoft.spt.business.service.common.impl.BaseCrudServiceImpl;
import com.msunsoft.spt.business.service.user.TbUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

/**
 * @author zxl
 * @create 2020/4/3 0003
 */
@Service
public class TbUserServiceImpl extends BaseCrudServiceImpl<TbUser, TbUserMapper> implements TbUserService {
    @Autowired
    private TbUserMapper tbUserMapper;

    @Override
    public TbUser get(String username) {
        Example example = new Example(TbUser.class);
        example.createCriteria().andEqualTo("username", username);
        return tbUserMapper.selectOneByExample(example);
    }
}
