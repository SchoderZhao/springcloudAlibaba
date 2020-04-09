package com.msunsoft.spt.business.oauth2.service;


import com.google.common.collect.Lists;
import com.msunsoft.spt.business.commons.utils.MapperUtils;
import com.msunsoft.spt.business.domain.user.TbPermission;
import com.msunsoft.spt.business.domain.user.TbUser;
import com.msunsoft.spt.business.oauth2.service.feign.BusinessTbUserService;
import lombok.SneakyThrows;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * 自定义认证
 * <p>
 * Description:
 * </p>
 *
 * @author zxl
 * @version v1.0.0
 * @date 2020-03-30
 * @see com.msunsoft.spt.business.oauth2.service
 *
 */
@Component
public class UserDetailsServiceImpl implements UserDetailsService {

    private static final String USERNAME = "admin";
    private static final String PASSWORD = "$2a$10$YNUV/BtCel2orbhgrxyPJeljdKVty6yTAL.Cj4v3XhwHWXBkgyPYW";

    @Resource
    BusinessTbUserService businessTbUserService;


    @SneakyThrows
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        // 查询用户
        String info = businessTbUserService.info(s);
        TbUser tbUser = MapperUtils.json2pojoByTree(info, "data", TbUser.class);


        // 默认所有用户拥有 USER权限
//        List<GrantedAuthority> grantedAuthorities = Lists.newArrayList();
//        GrantedAuthority grantedAuthority = new SimpleGrantedAuthority("USER");
//        grantedAuthorities.add(grantedAuthority);

        //权限列表
        List<GrantedAuthority> grantedAuthorities = Lists.newArrayList();

        // 用户存在
        if (tbUser != null) {
            // 获取用户授权
            String permissionInfo = businessTbUserService.permissionInfo(tbUser.getId());

            List<TbPermission> tbPermissions = MapperUtils.json2listByTree(permissionInfo,"data",TbPermission.class);

            // 声明用户授权
            tbPermissions.forEach(tbPermission -> {
                if (tbPermission != null && tbPermission.getEnname() != null) {
                    GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(tbPermission.getEnname());
                    grantedAuthorities.add(grantedAuthority);
                }
            });

            return new User(tbUser.getUsername(), tbUser.getPassword(), grantedAuthorities);
        }

        // 用户不存在
        else {
            return null;
        }

    }

}
