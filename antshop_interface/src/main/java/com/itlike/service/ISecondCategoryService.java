package com.itlike.service;


import com.itlike.pojo.AjaxRes;
import com.itlike.pojo.PageListRes;
import com.itlike.pojo.QueryVo;
import com.itlike.pojo.Secondcategory;

import java.util.List;

/**
 * @author : 迟彪
 * @date : 2019/7/29
 */
public interface ISecondCategoryService {
    PageListRes secondCategoryList(QueryVo vo);

    AjaxRes updateSecondCategory(Secondcategory secondcategory);

    AjaxRes addSecondCategory(Secondcategory secondcategory);

    AjaxRes deleteSecondCategory(long id);

    List<Secondcategory> AllSecondCategory();
}
