package com.itlike.service;


import com.itlike.pojo.PageListRes;
import com.itlike.pojo.QueryVo;
import com.itlike.pojo.Role;

import java.util.List;

public interface RoleService {
    /*查询角色列表*/
    public PageListRes getRoles(QueryVo vo);

    void saveRole(Role role);

    void updateRole(Role role);

    void deleteRole(long rid);

    List<Role> roleList();
    /*根据用户id查询对应的角色*/
    List<Long> getRoleByAid(Long id);
}
