package com.itlike.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.itlike.pojo.Admin;
import com.itlike.pojo.AjaxRes;
import com.itlike.pojo.PageListRes;
import com.itlike.pojo.QueryVo;
import com.itlike.service.IAdminService;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.*;


/**
 * @author : 迟彪
 * @date : 2019/8/4
 */
@RestController
@RequestMapping("/admin")
public class AdminController {
    @Reference
    private IAdminService adminService;
    @Bean(name = "adminService")
    public IAdminService adminService(){
        return this.adminService;
    }

    @RequestMapping("/adminList")
    public PageListRes adminList(QueryVo vo) {
        return adminService.adminList(vo);
    }

    @PostMapping("/addAdmin")
    public AjaxRes addAdmin(Admin admin) {
        return adminService.addAdmin(admin);
    }

    @PutMapping("/updateAdmin")
    public AjaxRes updateAdmin(Admin admin) {
        return adminService.updateAdmin(admin);
    }

    @DeleteMapping("/deleteAdmin/{id}")
    public AjaxRes deleteAdmin(@PathVariable("id")long id) {
        return adminService.deleteAdmin(id);
    }


}
