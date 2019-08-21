package com.itlike.controller;


import com.alibaba.dubbo.config.annotation.Reference;
import com.itlike.pojo.Permission;
import com.itlike.service.PermissionService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/permission")
public class PermissionController {
    @Reference
    private PermissionService permissionService;

    @RequestMapping("/permissionList")
    public List<Permission> permissionList() {
        return permissionService.getPermissions();
    }

    @RequestMapping("/getPermissionByRid/{rid}")
    public List<Permission> getPermissionByRid(@PathVariable("rid") Long rid) {
        List<Permission> permissions = permissionService.getPermissionByRid(rid);
        return permissions;
    }
}
