package com.itlike.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itlike.mapper.SecondcategoryMapper;
import com.itlike.pojo.AjaxRes;
import com.itlike.pojo.PageListRes;
import com.itlike.pojo.QueryVo;
import com.itlike.pojo.Secondcategory;
import com.itlike.service.ISecondCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author : 迟彪
 * @date : 2019/7/29
 */
@Service
@Component
public class SecondCategoryServiceImpl implements ISecondCategoryService {
    @Autowired
    SecondcategoryMapper secondcategoryMapper;
    @Override
    public PageListRes secondCategoryList(QueryVo vo) {
        PageListRes pageListRes = new PageListRes();
        Page<Object> page = PageHelper.startPage(vo.getPage(), vo.getRows());
        List<Secondcategory> secondcategories = secondcategoryMapper.selectAll();
        pageListRes.setRows(secondcategories);
        pageListRes.setTotal(page.getTotal());
        return pageListRes;
    }

    @Override
    public AjaxRes updateSecondCategory(Secondcategory secondcategory) {
        AjaxRes ajaxRes = new AjaxRes();
        try {
            secondcategoryMapper.updateByPrimaryKey(secondcategory);
            ajaxRes.setMsg("修改成功");
            ajaxRes.setSuccess(true);
        }catch (Exception e){
            ajaxRes.setMsg("修改失败");
            ajaxRes.setSuccess(false);
        }
        return ajaxRes;
    }

    @Override
    public AjaxRes addSecondCategory(Secondcategory secondcategory) {
        AjaxRes ajaxRes = new AjaxRes();
        try {
            secondcategoryMapper.insert(secondcategory);
            ajaxRes.setMsg("添加成功");
            ajaxRes.setSuccess(true);
        }catch (Exception e){
            ajaxRes.setMsg("添加失败");
            ajaxRes.setSuccess(false);
        }
        return ajaxRes;
    }

    @Override
    public AjaxRes deleteSecondCategory(long id) {
        AjaxRes ajaxRes = new AjaxRes();
        try {
            secondcategoryMapper.deleteByPrimaryKey(id);
            ajaxRes.setMsg("删除成功");
            ajaxRes.setSuccess(true);
        }catch (Exception e){
            ajaxRes.setMsg("删除失败");
            ajaxRes.setSuccess(false);
        }
        return ajaxRes;
    }

    @Override
    public List<Secondcategory> AllSecondCategory() {
        return secondcategoryMapper.selectAll();
    }
}
