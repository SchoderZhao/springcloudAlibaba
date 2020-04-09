package com.msunsoft.spt.business.oauth2.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * 登录信息
 * <p>
 * Description:
 * </p>
 *
 * @author zxl
 * @version v1.0.0
 * @date 2019-07-29 15:07:12
 * @see com.msunsoft.spt.business.oauth2.dto
 *
 */
@Data
public class LoginInfo implements Serializable {
    private String name;
    private String avatar;
    private String nickName;
}
