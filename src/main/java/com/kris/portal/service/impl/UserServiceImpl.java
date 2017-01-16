package com.kris.portal.service.impl;

import com.kris.portal.pojo.TaotaoResult;
import com.kris.portal.pojo.TbUser;
import com.kris.portal.service.UserService;
import com.kris.portal.utils.CookieUtils;
import com.kris.portal.utils.HttpClientUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author kris
 * @create 2017-01-16 15:40
 */
@Service
public class UserServiceImpl implements UserService {

    @Value("${SSO_BASE_URL}")
    private String SSO_BASE_URL;
    @Value("${SSO_USER_TOKEN_SERVICE}")
    private String SSO_USER_TOKEN_SERVICE;

    @Override
    public TbUser getUserByToken(HttpServletRequest request, HttpServletResponse response) {
        try {
            //从cookie中取token
            String token = CookieUtils.getCookieValue(request, "MARKET_TOKEN");
            //判断token是否有值
            if (StringUtils.isBlank(token)) {
                return null;
            }
            //调用sso的服务查询用户信息
            String json = HttpClientUtil.doGet(SSO_BASE_URL + SSO_USER_TOKEN_SERVICE + token);
            //把json转换成java对象
            TaotaoResult result = TaotaoResult.format(json);
            if (result.getStatus() != 200) {
                return null;
            }
            //取用户对象
            result=TaotaoResult.formatToPojo(json, TbUser.class);
            TbUser user = (TbUser) result.getData();

            return user;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
