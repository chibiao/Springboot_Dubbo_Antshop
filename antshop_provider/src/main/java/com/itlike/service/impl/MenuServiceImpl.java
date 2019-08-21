package com.itlike.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itlike.mapper.MenuMapper;
import com.itlike.pojo.AjaxRes;
import com.itlike.pojo.Menu;
import com.itlike.pojo.PageListRes;
import com.itlike.pojo.QueryVo;
import com.itlike.service.IMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author : 迟彪
 * @date : 2019/7/26
 */
@Service
@Component
public class MenuServiceImpl implements IMenuService {
    @Autowired
    MenuMapper menuMapper;
    @Override
    public List<Menu> getTreeData() {
        return menuMapper.getTreeData();
    }

    @Override
    public PageListRes menuList(QueryVo vo) {
        PageListRes pageListRes = new PageListRes();
        Page<Object> page = PageHelper.startPage(vo.getPage(), vo.getRows());
        List<Menu> menus = menuMapper.selectAll();
        pageListRes.setTotal(page.getTotal());
        pageListRes.setRows(menus);
        return pageListRes;
    }

    @Override
    public List<Menu> parentList() {
        return menuMapper.selectAll();
    }
    /*接收菜单表单*/
    @Override
    public void saveMenu(Menu menu) {
        menuMapper.insert(menu);
    }

    @Override
    public AjaxRes updateMenu(Menu menu) {
        AjaxRes ajaxRes=new AjaxRes();
        /*判断你选择的父菜单是不是你自己的子菜单*/
        /*先取出父级菜单的id*/
        Long id = menu.getParent().getId();
        while(id!=null){
            /*查询出该id对应的menu*/
            Long parentId = menuMapper.selectParentId(id);
            if(menu.getId()==parentId){
                ajaxRes.setMsg("不能设置自己的子菜单为父菜单");
                ajaxRes.setSuccess(false);
                return ajaxRes;
            }
            id=parentId;
        }
        try {
            menuMapper.updateByPrimaryKey(menu);
            ajaxRes.setMsg("保存成功");
            ajaxRes.setSuccess(true);
        }catch (Exception e){
            ajaxRes.setMsg("保存失败");
            ajaxRes.setSuccess(false);
        }
        return ajaxRes;
    }

    @Override
    public AjaxRes deleteMenu(Long id) {
        AjaxRes ajaxRes=new AjaxRes();
        try {
            /*1.打破菜单关系*/
            menuMapper.updateMenuRel(id);
            /*2.删除记录*/
            menuMapper.deleteByPrimaryKey(id);
            ajaxRes.setMsg("删除成功");
            ajaxRes.setSuccess(true);
        }catch (Exception e){
            ajaxRes.setMsg("删除失败");
            ajaxRes.setSuccess(false);
        }
        return ajaxRes;
    }
}
