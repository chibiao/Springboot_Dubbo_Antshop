package com.itlike.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itlike.mapper.CategoryMapper;
import com.itlike.pojo.AjaxRes;
import com.itlike.pojo.Category;
import com.itlike.pojo.PageListRes;
import com.itlike.pojo.QueryVo;
import com.itlike.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author : 迟彪
 * @date : 2019/7/29
 */
@Service
@Component
public class CategoryServiceImpl implements ICategoryService {
    @Autowired
    CategoryMapper categoryMapper;

    @Override
    public PageListRes getCategoryList(QueryVo vo) {
        PageListRes pageListRes = new PageListRes();
        Page<Object> page = PageHelper.startPage(vo.getPage(), vo.getRows());
        List<Category> categories = categoryMapper.selectAll();
        pageListRes.setRows(categories);
        pageListRes.setTotal(page.getTotal());
        return pageListRes;
    }

    @Override
    public AjaxRes updateCategory(Category category) {
        AjaxRes ajaxRes = new AjaxRes();
        try {
            categoryMapper.updateByPrimaryKey(category);
            ajaxRes.setSuccess(true);
            ajaxRes.setMsg("修改成功");
        } catch (Exception e) {
            ajaxRes.setMsg("修改失败");
            ajaxRes.setSuccess(false);
        }
        return ajaxRes;
    }

    @Override
    public AjaxRes addCategory(Category category) {
        AjaxRes ajaxRes = new AjaxRes();
        try {
            categoryMapper.insert(category);
            ajaxRes.setSuccess(true);
            ajaxRes.setMsg("添加成功");
        } catch (Exception e) {
            ajaxRes.setMsg("添加失败");
            ajaxRes.setSuccess(false);
        }
        return ajaxRes;
    }

    @Override
    public AjaxRes deleteCategory(Long id) {
        AjaxRes ajaxRes = new AjaxRes();
        try {
            categoryMapper.deleteByPrimaryKey(id);
            ajaxRes.setSuccess(true);
            ajaxRes.setMsg("删除成功");
        } catch (Exception e) {
            ajaxRes.setMsg("删除失败");
            ajaxRes.setSuccess(false);
        }
        return ajaxRes;

    }

    @Override
    public List<Category> allCategory() {
        return categoryMapper.selectAll();
    }

    @Override
    public List<Category> getAllCategoryList() {
        return categoryMapper.getAllCategoryList();
    }
}
