package com.itlike.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.itlike.pojo.*;
import com.itlike.service.IMenuService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Iterator;
import java.util.List;

/**
 * @author : 迟彪
 * @date : 2019/7/26
 */
@RestController
@RequestMapping("/menu")
public class MenuController {
    @Reference
    IMenuService menuService;

    @PostMapping("/getTreeData")
    public List<Menu> getTreeData() {
        List<Menu> treeData = menuService.getTreeData();
        Subject subject = SecurityUtils.getSubject();
        Admin principal = (Admin) subject.getPrincipal();
        checkPermission(treeData);
        return treeData;
    }

    @RequestMapping("/menuList")
    public PageListRes menuList(QueryVo vo) {
        return menuService.menuList(vo);
    }

    /*加载父级菜单*/
    @RequestMapping("/parentList")
    public List<Menu> parentList() {
        return menuService.parentList();
    }

    /*接收菜单表单*/
    @PostMapping("/saveMenu")
    public AjaxRes saveMenu(Menu menu) {
        AjaxRes ajaxRes = new AjaxRes();
        try {
            menuService.saveMenu(menu);
            ajaxRes.setMsg("保存成功");
            ajaxRes.setSuccess(true);
        } catch (Exception e) {
            ajaxRes.setMsg("保存失败");
            ajaxRes.setSuccess(false);
        }
        return ajaxRes;
    }

    /*接收更新菜单表单*/
    @PutMapping("/updateMenu")
    public AjaxRes updateMenu(Menu menu) {
        return menuService.updateMenu(menu);
    }

    /*删除菜单*/
    @DeleteMapping("/deleteMenu/{id}")
    public AjaxRes deleteMenu(@PathVariable("id") Long id) {
        return menuService.deleteMenu(id);
    }

    private void checkPermission(List<Menu> menus){
        //获取主体
        Subject subject = SecurityUtils.getSubject();
        //遍历所有的菜单及子菜单
        Iterator<Menu> iterator = menus.iterator();
        while (iterator.hasNext()){
            Menu menu = iterator.next();
            if (menu.getPermission() !=null){
                //判断当前menu是否有权限对象,如果说没有 当前遍历的菜单从集合当中移除
                String presource = menu.getPermission().getPresource();
                if (!subject.isPermitted(presource)){
                    //当前遍历的菜单从集合当中移除
                    iterator.remove();
                    continue;
                }
            }
            /*判断是否有子菜单  有子菜单也要做权限检验*/
            if (menu.getChildren().size() > 0){
                checkPermission(menu.getChildren());
            }
        }
    }
}
