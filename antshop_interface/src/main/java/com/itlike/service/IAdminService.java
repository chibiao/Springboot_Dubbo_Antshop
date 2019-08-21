package com.itlike.service;

import com.itlike.pojo.Admin;
import com.itlike.pojo.AjaxRes;
import com.itlike.pojo.PageListRes;
import com.itlike.pojo.QueryVo;

import java.util.List;

public interface IAdminService {
    PageListRes adminList(QueryVo vo);

    //Long getCount() throws SQLException;

    AjaxRes updateAdmin(Admin admin);

    AjaxRes addAdmin(Admin admin);

    AjaxRes deleteAdmin(Long id);

    Admin getAdminByName(String name);

    List<String> getRolesById(Long id);

    List<String> getPermissionById(Long id);

    //List<Permission> getAllPermissionById(Long id);
}
