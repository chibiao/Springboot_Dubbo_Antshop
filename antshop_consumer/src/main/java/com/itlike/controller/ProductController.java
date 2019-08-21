package com.itlike.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.itlike.pojo.PageBean;
import com.itlike.pojo.Product;
import com.itlike.service.IProductService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

/**
 * @author : 迟彪
 * @date : 2019/8/7
 */
@Controller
public class ProductController {
    @Reference
    private IProductService productService;

    @RequestMapping("/product/getProductByCategory")
    public String getProductByCategory(Map<String, Object> map, long id, PageBean pageBean) {
        if (pageBean.getPageNo() == null) {
            pageBean.setPageNo(1);
        }
        pageBean = productService.getProductByCategory(id, pageBean.getPageNo());
        map.put("id", id);
        map.put("pageBean", pageBean);
        return "product";
    }

    @RequestMapping("/product/getProductdetail")
    public String getProductdetail(Map<String, Object> map, long id) {
        Product product = productService.selectProductById(id);
        map.put("product", product);
        return "detail";
    }
}
