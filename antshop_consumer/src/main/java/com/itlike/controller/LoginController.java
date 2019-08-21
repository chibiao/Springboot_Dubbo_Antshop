package com.itlike.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.itlike.pojo.AjaxRes;
import com.itlike.pojo.User;
import com.itlike.service.IUserService;
import com.itlike.utils.MD5Util;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author : 迟彪
 * @date : 2019/8/9
 */
@RequestMapping("/login")
@Controller
public class LoginController {
    @Reference
    private IUserService userService;

    @RequestMapping("/login")
    @ResponseBody
    public AjaxRes login(HttpServletResponse response, HttpServletRequest request, User user, boolean remember) {
        AjaxRes ajaxRes = new AjaxRes();
        /*去查询用户是否存在*/
        User user1 = userService.getUserByUsername(user.getUsername());
        /*如果用户存在 判断密码是否相同*/
        if (user1 == null) {
            ajaxRes.setSuccess(false);
            ajaxRes.setMsg("用户名不存在");
            return ajaxRes;
        }
        if (user1 != null && !user1.getPassword().equals(MD5Util.md5(user.getPassword()))) {
            ajaxRes.setSuccess(false);
            ajaxRes.setMsg("密码不正确");
            return ajaxRes;
        }
        if (user1 != null && user1.getPassword().equals(MD5Util.md5(user.getPassword())) && !user1.getState().booleanValue() == true) {
            ajaxRes.setSuccess(false);
            ajaxRes.setMsg("账号未激活");
            return ajaxRes;
        }
        String userToken = userService.addUserToRedis(user1);
        Cookie ck4 = new Cookie("userToken", userToken);
        response.addCookie(ck4);
        request.getSession().setAttribute("user",user1);
        ajaxRes.setSuccess(true);
        ajaxRes.setMsg("登录成功");
        return ajaxRes;
    }

    @RequestMapping("/logout")
    public String logout(HttpServletResponse response, HttpServletRequest request, User user, boolean remember){
        Cookie[] cookies = request.getCookies();
        //用于判断是否找到购物车cookie
        if (cookies != null) {//获取cookies
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("userToken")) {
                    String userToken = cookie.getValue();//获取购物车凭证
                    userService.deleteUserByUserToken(userToken);
                    cookie.setMaxAge(0);
                    response.addCookie(cookie);
                    request.getSession().removeAttribute("user");
                }
                return "login";
            }
        }
        return "login";
    }
}
