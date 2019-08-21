package com.itlike.mapper;

import com.itlike.pojo.Admin;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AdminMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Admin record);

    Admin selectByPrimaryKey(Long id);

    List<Admin> selectAll();

    int updateByPrimaryKey(Admin record);

    void insertAdminAndRoleRel(@Param("id") Long id, @Param("rid") Long rid);

    void deleteAdminAndRoleRel(Long id);

    Admin getAdminByName(String name);

    List<String> getRolesById(Long id);

    List<String> getPermissionById(Long id);
}