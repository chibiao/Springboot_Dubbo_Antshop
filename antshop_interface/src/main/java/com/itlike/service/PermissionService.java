package com.itlike.service;


import com.itlike.pojo.Permission;

import java.util.List;

public interface PermissionService {
    List<Permission> getPermissions();

    List<Permission> getPermissionByRid(Long rid);
}
