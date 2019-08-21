package com.itlike.mapper;

import com.itlike.pojo.Cart;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CartMapper {
    int deleteByPrimaryKey(@Param("uid") Long uid, @Param("id") Long id);

    int insert(Cart record);

    Cart selectByPrimaryKey(@Param("uid") Long uid, @Param("id") Long id);

    List<Cart> selectAll();

    int updateByPrimaryKey(Cart record);

    List<Cart> getCartByUid(Long id);

    void deleteAllByUid(Long id);
}