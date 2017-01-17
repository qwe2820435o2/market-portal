package com.kris.portal.Interceptor;

import com.kris.portal.pojo.TbUser;
import com.kris.portal.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 登录拦截器
 *
 * @author kris
 * @create 2017-01-16 15:54
 */
public class LoginInterceptor implements HandlerInterceptor{

    @Autowired
    private UserService mUserService;
    @Value("${SSO_LOGIN_URL}")
    private String SSO_LOGIN_URL;

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        //拦截请求url
        //从cookie中取token
        //如果没有token，跳转到登录页面
        //取到token，需要调用sso系统的服务查询用户信息
        TbUser user = mUserService.getUserByToken(httpServletRequest, httpServletResponse);
        //如果用户session已经过期，跳转到登录界面
        if (user == null) {
            httpServletResponse.sendRedirect(SSO_LOGIN_URL+"?redirectURL="+httpServletRequest.getRequestURI());
            return false;
        }
        //把用户放入request中
        httpServletRequest.setAttribute("user",user);
        //如果没有过期，放行
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
