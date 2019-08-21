package com.itlike.service;

import com.itlike.pojo.*;

import java.util.List;

public interface IProductService {
    PageListRes getProductList(QueryVo vo) ;

    //Long getCount();

    AjaxRes updateProduct(Product product);

    AjaxRes addProduct(Product product);

    AjaxRes upadateProductState(long id);

    Product selectProductById(Long id);

    //PageBean getProductByCategory(int id, int page);

    //PageBean getProductBySecondCategory(int id, Integer page);

    //List<Product> getProductByCart(List<Cart> carts);

    List<Product> getProductByUid(Long id) ;

    List<Product> getHotProduct();

    List<Product> getProductByName(String name);

    PageBean getProductByCategory(long id, Integer pageNo);

    Product getProductById(int id);
}
