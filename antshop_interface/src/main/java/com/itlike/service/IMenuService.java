package com.itlike.service;


import com.itlike.pojo.AjaxRes;
import com.itlike.pojo.Menu;
import com.itlike.pojo.PageListRes;
import com.itlike.pojo.QueryVo;

import java.util.List;

/**
 * @author : 迟彪
 * @date : 2019/7/26
 */
public interface IMenuService {
    List<Menu> getTreeData();

    PageListRes menuList(QueryVo vo);

    List<Menu> parentList();

    void saveMenu(Menu menu);

    AjaxRes updateMenu(Menu menu);

    AjaxRes deleteMenu(Long id);
}
