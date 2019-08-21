package com.itlike.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.itlike.pojo.AjaxRes;
import com.itlike.pojo.PageListRes;
import com.itlike.pojo.QueryVo;
import com.itlike.pojo.Secondcategory;
import com.itlike.service.ISecondCategoryService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @author : 迟彪
 * @date : 2019/7/29
 */
@RestController
@RequestMapping("/secondCategory")
public class SecondCategoryController {
    @Reference
    private ISecondCategoryService secondCategoryService;

    @RequestMapping("/secondCategoryList")
    public PageListRes secondCategoryList(QueryVo vo) {
        return secondCategoryService.secondCategoryList(vo);
    }

    @RequestMapping("/AllSecondCategory")
    public List<Secondcategory> AllSecondCategory() {
        return secondCategoryService.AllSecondCategory();
    }

    @PutMapping("/updateSecondCategory")
    public AjaxRes updateSecondCategory(Secondcategory secondcategory) {
        return secondCategoryService.updateSecondCategory(secondcategory);
    }

    @PostMapping("/addSecondCategory")
    public AjaxRes addSecondCategory(Secondcategory secondcategory) {
        return secondCategoryService.addSecondCategory(secondcategory);
    }

    @DeleteMapping("/deleteSecondCategory/{id}")
    public AjaxRes deleteSecondCategory(@PathVariable("id") long id) {
        return secondCategoryService.deleteSecondCategory(id);
    }
}
