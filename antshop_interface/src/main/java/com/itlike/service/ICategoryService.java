package com.itlike.service;


import com.itlike.pojo.AjaxRes;
import com.itlike.pojo.Category;
import com.itlike.pojo.PageListRes;
import com.itlike.pojo.QueryVo;

import java.util.List;

/**
 * @author : 迟彪
 * @date : 2019/7/29
 */
public interface ICategoryService {
    PageListRes getCategoryList(QueryVo vo);

    AjaxRes updateCategory(Category category);

    AjaxRes addCategory(Category category);

    AjaxRes deleteCategory(Long id);

    List<Category> allCategory();

    List<Category> getAllCategoryList();
}
