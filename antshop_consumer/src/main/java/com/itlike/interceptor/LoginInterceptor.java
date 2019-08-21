package com.itlike.interceptor;

import com.itlike.pojo.User;
import com.itlike.service.IUserService;
import com.itlike.utils.SpringBeanFactoryUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author : 迟彪
 * @date : 2019/8/9
 */
public class LoginInterceptor implements HandlerInterceptor {
    //拦截所有请求  看是否登录
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Cookie[] cookies = request.getCookies();
        //用于判断是否找到购物车cookie
        if (cookies != null) {//获取cookies
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("userToken")) {
                    String userToken = cookie.getValue();//获取购物车凭证
                    IUserService userService = SpringBeanFactoryUtils.getBean("userService", IUserService.class);
                    User user=userService.getUserByUserToken(userToken);
                    if(user!=null){
                        request.getSession().setAttribute("user",user);
                        System.out.println(user+"user");
                        return true;
                    }else {
                        return true;
                    }
                }
            }
        }
        return true;
    }
}
