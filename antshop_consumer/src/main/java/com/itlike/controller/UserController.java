package com.itlike.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.itlike.pojo.AjaxRes;
import com.itlike.pojo.PageListRes;
import com.itlike.pojo.QueryVo;
import com.itlike.pojo.User;
import com.itlike.service.IAdminService;
import com.itlike.service.IUserService;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author : 迟彪
 * @date : 2019/8/5
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Reference
    private IUserService userService;
    @Bean(name = "userService")
    public IUserService userService(){
        return this.userService;
    }

    @RequestMapping("/userList")
    public PageListRes userList(QueryVo vo) {
        return userService.userList(vo);
    }

    @PostMapping("/addUser")
    public AjaxRes addUser(User user){
        return userService.addUser(user);
    }

    @PutMapping("/updateUser")
    public AjaxRes updateUser(User user){
        return userService.updateUser(user);
    }

    @PutMapping("/updateUserState/{id}")
    public AjaxRes updateUserState(@PathVariable("id") long id){
        return userService.updateUserState(id);
    }
}
