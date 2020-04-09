package com.msunsoft.spt.business.oauth2.service.feign.impl;

import com.msunsoft.spt.business.commons.utils.MapperUtils;
import com.msunsoft.spt.business.dto.ResponseResult;
import com.msunsoft.spt.business.oauth2.service.feign.BusinessTbUserService;
import org.springframework.stereotype.Component;

/**
 * @author zxl
 * @create 2020/4/7 0007
 */
@Component
public class BusinessTbUserServiceFallBack implements BusinessTbUserService {

    public static final String BREAKING_MESSAGE = "请求失败了，请检查您的网络";

    @Override
    public String info(String username) {
        try {
            return MapperUtils.obj2json(new ResponseResult<Void>(ResponseResult.CodeStatus.BREAKING, BREAKING_MESSAGE));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String permissionInfo(long username) {
        try {
            return MapperUtils.obj2json(new ResponseResult<Void>(ResponseResult.CodeStatus.BREAKING, BREAKING_MESSAGE));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
