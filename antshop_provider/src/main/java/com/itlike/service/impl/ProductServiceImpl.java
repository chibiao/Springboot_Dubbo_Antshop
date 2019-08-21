package com.itlike.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itlike.mapper.ProductMapper;
import com.itlike.pojo.*;
import com.itlike.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

/**
 * @author : 迟彪
 * @date : 2019/8/5
 */
@Service
@Component
public class ProductServiceImpl implements IProductService {
    @Autowired
    private ProductMapper productMapper;
    @Override
    public PageListRes getProductList(QueryVo vo) {
        PageListRes pageListRes = new PageListRes();
        Page<Object> page = PageHelper.startPage(vo.getPage(), vo.getRows());
        List<Product> products = productMapper.selectAll(vo);
        Collections.reverse(products);
        pageListRes.setTotal(page.getTotal());
        pageListRes.setRows(products);
        return pageListRes;
    }

    @Override
    public AjaxRes updateProduct(Product product) {
        AjaxRes ajaxRes = new AjaxRes();
        try {
            productMapper.updateByPrimaryKey(product);
            ajaxRes.setSuccess(true);
            ajaxRes.setMsg("修改成功");
        }catch (Exception e){
            ajaxRes.setSuccess(false);
            ajaxRes.setMsg("修改失败");
        }
        return ajaxRes;
    }

    @Override
    public AjaxRes addProduct(Product product) {
        AjaxRes ajaxRes = new AjaxRes();
        try {
            productMapper.insert(product);
            ajaxRes.setSuccess(true);
            ajaxRes.setMsg("添加成功");
        }catch (Exception e){
            ajaxRes.setSuccess(false);
            ajaxRes.setMsg("添加失败");
        }
        return ajaxRes;
    }

    @Override
    public AjaxRes upadateProductState(long id) {
        AjaxRes ajaxRes = new AjaxRes();
        try {
            productMapper.updateProductState(id);
            ajaxRes.setSuccess(true);
            ajaxRes.setMsg("下架成功");
        }catch (Exception e){
            ajaxRes.setSuccess(false);
            ajaxRes.setMsg("下架失败");
        }
        return ajaxRes;
    }

    @Override
    public Product selectProductById(Long id) {
        return productMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Product> getProductByUid(Long id) {
        return null;
    }

    @Override
    public List<Product> getHotProduct() {
        return productMapper.getHotProduct();
    }

    @Override
    public List<Product> getProductByName(String name) {
        return null;
    }

    @Override
    public PageBean getProductByCategory(long id, Integer pageNo) {
        PageBean pageBean = new PageBean();
        pageBean.setPageNo(pageNo);
        Page<Object> page = PageHelper.startPage(pageNo, 8);
        List<Product> productByCategory =productMapper.getProductByCategory(id);
        pageBean.setProducts(productByCategory);
        pageBean.setTotalSize((int) page.getTotal());
        pageBean.setTotalPage(page.getPages());
        return pageBean;
    }

    @Override
    public Product getProductById(int id) {
        return productMapper.selectByPrimaryKey((long) id);
    }
}
