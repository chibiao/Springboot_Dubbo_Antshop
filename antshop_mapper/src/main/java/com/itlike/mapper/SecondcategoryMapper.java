package com.itlike.mapper;


import com.itlike.pojo.Secondcategory;

import java.util.List;

public interface SecondcategoryMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Secondcategory record);

    Secondcategory selectByPrimaryKey(Long id);

    List<Secondcategory> selectAll();

    int updateByPrimaryKey(Secondcategory record);
}