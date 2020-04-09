package com.msunsoft.spt.business.oauth2.controller;

import com.msunsoft.spt.business.oauth2.BusinessException;
import com.msunsoft.spt.business.oauth2.BusinessStatus;
import com.msunsoft.spt.business.domain.user.TbUser;
import com.msunsoft.spt.business.oauth2.dto.LoginInfo;
import com.msunsoft.spt.business.oauth2.dto.LoginParam;
import com.msunsoft.spt.business.dto.ResponseResult;
import com.msunsoft.spt.business.commons.utils.MapperUtils;
import com.msunsoft.spt.business.commons.utils.OkHttpClientUtil;
import com.google.common.collect.Maps;
import com.msunsoft.spt.business.oauth2.service.feign.BusinessTbUserService;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.Objects;

/**
 * 登录管理
 * <p>
 * Description:
 * </p>
 *
 * @author zxl
 * @version v1.0.0
 * @date 2019-07-29 11:14:58
 * @see com.msunsoft.spt.business.oauth2.controller
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class LoginController {

    private static final String URL_OAUTH_TOKEN = "http://localhost:9001/oauth/token";

    @Value("${business.oauth2.grant_type}")
    public String oauth2GrantType;

    @Value("${business.oauth2.client_id}")
    public String oauth2ClientId;

    @Value("${business.oauth2.client_secret}")
    public String oauth2ClientSecret;

    @Resource(name = "userDetailsServiceBean")
    public UserDetailsService userDetailsService;

    @Resource
    public BCryptPasswordEncoder passwordEncoder;

    @Resource
    public TokenStore tokenStore;

    @Resource
    private BusinessTbUserService businessTbUserService;

//    日志
//    @Reference(version = "1.0.0")
//    private MessageService messageService;

    /**
     * 登录
     *
     * @param loginParam 登录参数
     * @return {@link ResponseResult}
     */
    @PostMapping(value = "/user/login")
    public ResponseResult<Map<String, Object>> login(@RequestBody LoginParam loginParam, HttpServletRequest request) throws Exception {
        // 封装返回的结果集
        Map<String, Object> result = Maps.newHashMap();

        // 验证密码是否正确
        UserDetails userDetails = userDetailsService.loadUserByUsername(loginParam.getUsername());
        if (userDetails == null || !passwordEncoder.matches(loginParam.getPassword(), userDetails.getPassword())) {
            throw new BusinessException(BusinessStatus.ADMIN_PASSWORD);
        }

        // 通过 HTTP 客户端请求登录接口
        Map<String, String> params = Maps.newHashMap();
        params.put("username", loginParam.getUsername());
        params.put("password", loginParam.getPassword());
        params.put("grant_type", oauth2GrantType);
        params.put("client_id", oauth2ClientId);
        params.put("client_secret", oauth2ClientSecret);

        try {
            // 解析响应结果封装并返回
            Response response = OkHttpClientUtil.getInstance().postData(URL_OAUTH_TOKEN, params);
            String jsonString = Objects.requireNonNull(response.body()).string();
            Map<String, Object> jsonMap = MapperUtils.json2map(jsonString);
            String token = String.valueOf(jsonMap.get("access_token"));
            result.put("token", token);

            // 发送登录日志
            //sendAdminLoginLog(userDetails.getUsername(), request);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new ResponseResult<Map<String, Object>>(ResponseResult.CodeStatus.OK, "登录成功", result);
    }

    /**
     * 获取用户信息
     *
     * @return {@link ResponseResult}
     */
    @PreAuthorize("hasAuthority('USER')")
    @GetMapping(value = "/user/info")
    public ResponseResult<LoginInfo> info() throws Exception {
        // 获取认证信息
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // 获取个人信息
        String jsonString = businessTbUserService.info(authentication.getName());
        TbUser tbUser = MapperUtils.json2pojoByTree(jsonString, "data", TbUser.class);

        // 如果触发熔断则返回熔断结果
        if (tbUser == null) {
            return MapperUtils.json2pojo(jsonString, ResponseResult.class);
        }

        // 封装并返回结果
        LoginInfo loginInfo = new LoginInfo();
        loginInfo.setName(tbUser.getUsername());
//        loginInfo.setAvatar(tbUser.getIcon());
//        loginInfo.setNickName(tbUser.getNickName());
        return new ResponseResult<LoginInfo>(ResponseResult.CodeStatus.OK, "获取用户信息", loginInfo);
    }

    /**
     * 注销
     *
     * @return {@link ResponseResult}
     */
    @PreAuthorize("hasAuthority('USER')")
    @PostMapping(value = "/user/logout")
    public ResponseResult<Void> logout(HttpServletRequest request) {
        // 获取 token
        String token = request.getParameter("access_token");
        // 删除 token 以注销
        OAuth2AccessToken oAuth2AccessToken = tokenStore.readAccessToken(token);
        tokenStore.removeAccessToken(oAuth2AccessToken);
        return new ResponseResult<Void>(ResponseResult.CodeStatus.OK, "用户已注销");
    }

    /**
     * 发送登录日志
     *
     * @param request {@link HttpServletRequest}
     */
    private void sendAdminLoginLog(String username, HttpServletRequest request) throws Exception {


        TbUser tbUser = MapperUtils.json2pojoByTree(businessTbUserService.info(username), "data", TbUser.class);


        if (tbUser != null) {
            // 获取请求的用户代理信息
//            Browser browser = UserAgentUtils.getBrowser(request);
//            String ip = UserAgentUtils.getIpAddr(request);
//            String address = UserAgentUtils.getIpInfo(ip).getCity();
//
//            UmsAdminLoginLogDTO dto = new UmsAdminLoginLogDTO();
//            dto.setAdminId(umsAdmin.getId());
//            dto.setCreateTime(new Date());
//            dto.setIp(ip);
//            dto.setAddress(address);
//            dto.setUserAgent(browser.getName());
//
//            messageService.sendAdminLoginLog(dto);
        }

    }
}
