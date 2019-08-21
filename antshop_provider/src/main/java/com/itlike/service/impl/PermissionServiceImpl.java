package com.itlike.service.impl;


import com.alibaba.dubbo.config.annotation.Service;
import com.itlike.mapper.PermissionMapper;
import com.itlike.pojo.Permission;
import com.itlike.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Service
public class PermissionServiceImpl implements PermissionService {
    @Autowired
    private PermissionMapper permissionMapper;
    @Override
    public List<Permission> getPermissions() {
        return permissionMapper.selectAll();
    }

    @Override
    public List<Permission> getPermissionByRid(Long rid) {
        return permissionMapper.getPermissionByRid(rid);
    }
}
