package com.itlike.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.itlike.pojo.AjaxRes;
import com.itlike.pojo.Category;
import com.itlike.pojo.PageListRes;
import com.itlike.pojo.QueryVo;
import com.itlike.service.ICategoryService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @author : 迟彪
 * @date : 2019/7/29
 */
@RestController
@RequestMapping("/category")
public class CategoryController {
    @Reference
    private ICategoryService categoryService;

    @RequestMapping("/categoryList")
    public PageListRes categoryList(QueryVo vo) {
        return categoryService.getCategoryList(vo);
    }

    @PutMapping("/updateCategory")
    public AjaxRes updateCategory(Category category) {
        return categoryService.updateCategory(category);
    }

    @RequestMapping("/addCategory")
    public AjaxRes addCategory(Category category) {
        return categoryService.addCategory(category);
    }

    @DeleteMapping("/deleteCategory/{id}")
    public AjaxRes addCategory(@PathVariable("id") Long id) {
        return categoryService.deleteCategory(id);
    }

    @RequestMapping("/allCategory")
    public List<Category> addCategory(){
        return categoryService.allCategory();
    }

    @RequestMapping("/getCategoryList")
    public List<Category> getCategoryList(){
        System.out.println(categoryService.getAllCategoryList());
        return categoryService.getAllCategoryList();
    }
}
