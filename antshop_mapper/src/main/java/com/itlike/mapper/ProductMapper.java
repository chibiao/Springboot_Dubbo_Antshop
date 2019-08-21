package com.itlike.mapper;

import com.itlike.pojo.Product;
import com.itlike.pojo.QueryVo;

import java.util.List;

public interface ProductMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Product record);

    Product selectByPrimaryKey(Long id);

    List<Product> selectAll(QueryVo vo);

    int updateByPrimaryKey(Product record);

    void updateProductState(long id);

    List<Product> getHotProduct();

    List<Product> getProductByCategory(long id);

    Product selectProductById(Long productId);
}