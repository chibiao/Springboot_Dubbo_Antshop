package com.itlike.service;


import com.itlike.pojo.Cart;
import com.itlike.pojo.Product;

import java.util.List;
import java.util.Map;

public interface CartService {
    String addToCart(String cartToken, Cart cart);

    Map<Product,Integer> getCartByUid(Long id);

    void deleteCart(Long uid, Long pid);

    Map<Product, Integer> getCart(String cartToken);

    void addCart(Cart cart);

    void deleteAll(Long id);

}
