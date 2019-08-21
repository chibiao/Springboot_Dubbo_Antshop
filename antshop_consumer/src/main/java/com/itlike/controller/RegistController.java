package com.itlike.controller;

import com.alibaba.fastjson.JSON;
import com.itlike.pojo.AjaxRes;
import com.itlike.pojo.User;
import com.itlike.service.IUserService;
import com.itlike.utils.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @author : 迟彪
 * @date : 2019/8/9
 */
@Controller
public class RegistController {
    @Autowired
    private IUserService userService;
    @RequestMapping("/regist")
    @ResponseBody
    public AjaxRes regist(String code, HttpServletRequest request, User user){
        AjaxRes ajaxRes = new AjaxRes();
        /*获取生成的验证码
         * */
        String word = (String)request.getSession().getAttribute("checkCode");
        System.out.println(word);
        System.out.println(code);
        // System.out.println(word);

        // 判断验证码
        if (code.equals(word)) {
            User checkUser = null;
            try {
                //判断用户名是否存在
                checkUser = userService.getUserByUsername(user.getUsername());
                if (checkUser == null) {
                    /*添加用户*/
                    user.setState(false);
                    user.setPassword(MD5Util.md5(user.getPassword()));
                    userService.addUser(user);
                    ajaxRes.setSuccess(true);
                    ajaxRes.setMsg("注册成功");
                    //跳转到登录页面
                    return ajaxRes;
                } else {
                    ajaxRes.setMsg("用户名已存在");
                    ajaxRes.setSuccess(false);
                    return ajaxRes;
                }
            } catch (Exception e) {
                ajaxRes.setMsg("服务器异常");
                ajaxRes.setSuccess(false);
                return ajaxRes;
            }
        } else {
            ajaxRes.setSuccess(false);
            ajaxRes.setMsg("验证码错误");
            //跳转到登录页面
            return ajaxRes;
        }
    }

    private void sendEmail(String email, String username) {

    }
}
