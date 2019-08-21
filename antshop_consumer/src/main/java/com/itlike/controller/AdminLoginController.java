package com.itlike.controller;

import com.itlike.pojo.AjaxRes;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author : 迟彪
 * @date : 2019/8/8
 */
@Controller
public class AdminLoginController {
    @RequestMapping("/login")
    public String login() {
        return "admin/login";
    }

    @RequestMapping("/admin/login")
    @ResponseBody
    public AjaxRes login(String username, String password) {
        // 从SecurityUtils里边创建一个 subject
        Subject subject = SecurityUtils.getSubject();
        // 在认证提交前准备 token（令牌）
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        // 执行认证登陆
        subject.login(token);
        AjaxRes ajaxRes = new AjaxRes();
        ajaxRes.setMsg("登录成功");
        ajaxRes.setSuccess(true);
        return ajaxRes;
    }

    @RequestMapping("/nopermission")
    public String nopermission() {
        return "redirect:/admin/nopermission";
    }
}
