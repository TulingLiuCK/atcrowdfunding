package com.lck.crowd.mvc.interceptor;

import com.lck.crowd.entity.Admin;
import com.lck.crowd.exception.AccessForbiddenException;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static com.lck.crowd.constant.CrowdConstant.ATTR_NAME_LOGIN_ADMIN;
import static com.lck.crowd.constant.CrowdConstant.MESSAGE_ACCESS_FORBIDENB;

/***
 #Create by LCK on 2022/11/23
 # 用法: 登录拦截器
 */
public class LoginInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //1 通过request对象虎丘Session对象
        HttpSession session = request.getSession();
        //尝试从sessino获取Admin对象
        Admin admin = (Admin) session.getAttribute(ATTR_NAME_LOGIN_ADMIN);
//        if (admin == null){
//            throw new AccessForbiddenException(MESSAGE_ACCESS_FORBIDENB);
//        }
        return true;
    }
}
