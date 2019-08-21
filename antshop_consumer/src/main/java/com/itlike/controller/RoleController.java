package com.itlike.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.itlike.pojo.AjaxRes;
import com.itlike.pojo.PageListRes;
import com.itlike.pojo.QueryVo;
import com.itlike.pojo.Role;
import com.itlike.service.RoleService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RestController
@RequestMapping("/role")
public class RoleController {
    @Reference
    private RoleService roleService;

    @RequestMapping("/getRoles")
    public PageListRes getRoles(QueryVo vo){
        PageListRes roles = roleService.getRoles(vo);
        return roles;
    }
    /*保存角色*/
    @PostMapping("/saveRole")
    public AjaxRes saveRole(Role role){
        AjaxRes ajaxRes = new AjaxRes();
        try {
            roleService.saveRole(role);
            ajaxRes.setMsg("保存成功!");
            ajaxRes.setSuccess(true);
        } catch (Exception e) {
            ajaxRes.setMsg("保存失败!");
            ajaxRes.setSuccess(false);
        }
        return ajaxRes;
    }
    /*更新角色*/
    @PutMapping("/updateRole")
    public AjaxRes updateRole(Role role){
        AjaxRes ajaxRes = new AjaxRes();
        try {
            roleService.updateRole(role);
            ajaxRes.setMsg("更新角色成功!");
            ajaxRes.setSuccess(true);
        } catch (Exception e) {
            ajaxRes.setMsg("更新角色失败!");
            ajaxRes.setSuccess(false);
        }
        return ajaxRes;
    }
    /*删除角色*/
    @DeleteMapping("/deleteRole/{rid}")
    public AjaxRes deleteRole(@PathVariable("rid") long rid){
        AjaxRes ajaxRes = new AjaxRes();
        try {
            roleService.deleteRole(rid);
            ajaxRes.setMsg("删除角色成功!");
            ajaxRes.setSuccess(true);
        } catch (Exception e) {
            ajaxRes.setMsg("删除角色失败!");
            ajaxRes.setSuccess(false);
        }
        return ajaxRes;
    }
    /*查询所有角色*/
    @RequestMapping("/roleList")
    public List<Role> roleList(){
        return roleService.roleList();
    }

    /*根据员工编号查询角色编号*/
    @RequestMapping("/getRoleByAid/{id}")
    public List<Long> getRoleByEid(@PathVariable("id") Long id){
        /*查询对应的角色*/
        return roleService.getRoleByAid(id);
    }
}
