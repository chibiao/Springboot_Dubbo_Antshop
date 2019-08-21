package com.itlike.mapper;


import com.itlike.pojo.Menu;

import java.util.List;

public interface MenuMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Menu record);

    Menu selectByPrimaryKey(Long id);

    List<Menu> selectAll();

    int updateByPrimaryKey(Menu record);

    List<Menu> getTreeData();

    Long selectParentId(Long id);

    void updateMenuRel(Long id);

}