package com.itlike.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.itlike.mapper.CartMapper;
import com.itlike.pojo.Cart;
import com.itlike.pojo.Product;
import com.itlike.service.CartService;
import com.itlike.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * @author : 迟彪
 * @date : 2019/8/8
 */
@Service
@Component
public class CartServiceImpl implements CartService {
    @Autowired
    IProductService productService;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    CartMapper cartMapper;

    @Override   //购物车凭证 和购物项
    public String addToCart(String cartToken, Cart cart) {
        List<Cart> carts = new ArrayList<>();
        if (cartToken == null) {
            System.out.println("cartToken=" + "null");
            cartToken = UUID.randomUUID().toString().replace("-", "");
            carts.add(cart);
            stringRedisTemplate.opsForValue().set(cartToken, JSON.toJSONString(carts));
        } else {
            System.out.println("cartToken=" + "string");
            String s = stringRedisTemplate.opsForValue().get(cartToken);
            carts = JSON.parseObject(s, new TypeReference<ArrayList<Cart>>() {
            });
            if (carts != null) {
                System.out.println("carts is not null");
                carts.add(cart);
            }
            stringRedisTemplate.opsForValue().set(cartToken, JSON.toJSONString(carts));
        }
        return cartToken;
    }

    @Override
    public Map<Product,Integer> getCartByUid(Long id) {
        Map<Product,Integer> map=new LinkedHashMap<>();
        List<Cart> carts = cartMapper.getCartByUid(id);
        for (Cart cart : carts) {
            Product product = productService.getProductById(cart.getId());
            map.put(product, cart.getCount());
        }
        return map;
    }

    @Override
    public void deleteCart(Long uid, Long pid) {
        cartMapper.deleteByPrimaryKey(uid, pid);
    }

    @Override
    public Map<Product, Integer> getCart(String cartToken) {
        Map<Product, Integer> map = new LinkedHashMap<>();
        String s = stringRedisTemplate.opsForValue().get(cartToken);
        List<Cart> carts = JSON.parseObject(s, new TypeReference<ArrayList<Cart>>() {
        });
        for (Cart cart : carts) {
            Product product = productService.getProductById(cart.getId());
            map.put(product, cart.getCount());
        }
        return map;
    }

    @Override
    public void addCart(Cart cart) {
        cartMapper.insert(cart);
    }

    @Override
    public void deleteAll(Long id) {
        cartMapper.deleteAllByUid(id);
    }
}
