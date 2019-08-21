package com.itlike.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.itlike.pojo.AjaxRes;
import com.itlike.pojo.Cart;
import com.itlike.pojo.Product;
import com.itlike.pojo.User;
import com.itlike.service.CartService;
import com.sun.org.apache.regexp.internal.RE;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author : 迟彪
 * @date : 2019/8/8
 */
@Controller
@RequestMapping("/cart")
public class CartController {
    @Reference
    private CartService cartService;

    //前端存入购物车凭证  后端存入redis
    @RequestMapping("/addToCart")
    @ResponseBody
    public AjaxRes addToCart(Cart cart, HttpServletResponse response, HttpServletRequest request) {
        AjaxRes ajaxRes = new AjaxRes();
        User user = (User) request.getSession().getAttribute("user");
        if (user!=null){
            cart.setUid(user.getId());
            try {
                cartService.addCart(cart);
                ajaxRes.setSuccess(true);
                ajaxRes.setMsg("添加成功");
            }catch (Exception e){
                ajaxRes.setMsg("添加失败");
                ajaxRes.setSuccess(false);
            }
            return ajaxRes;
        }
        String cartToken = null;
        Cookie[] cookies = request.getCookies();
        //用于判断是否找到购物车cookie
        boolean flag=false;
        if (cookies != null) {//获取cookies
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("cartToken")) {
                    cartToken = cookie.getValue();//获取购物车凭证
                    cartToken = cartService.addToCart(cartToken, cart);
                    ajaxRes.setSuccess(true);
                    ajaxRes.setMsg("添加成功");
                    //找到后跳出循环
                    flag=true;
                    break;
                }
            }
        }
        if(!flag){
            ajaxRes.setSuccess(true);
            ajaxRes.setMsg("添加成功");
            cartToken = cartService.addToCart(null, cart);
        }
        response.addCookie(new Cookie("cartToken", cartToken));
        return ajaxRes;
    }

    @RequestMapping("/index")
    public String cartIndex(Model model, HttpServletRequest request){
        User user = (User) request.getSession().getAttribute("user");
        if (user!=null){
            Map<Product,Integer> map=new LinkedHashMap<>();
            map = cartService.getCartByUid(user.getId());
            model.addAttribute("carts",map);
            return  "shopcar";
        }
        Cookie[] cookies = request.getCookies();
        boolean flag=false;
        String cartToken=null;
        Map<Product,Integer> map=new LinkedHashMap<>();
        if (cookies != null) {//获取cookies
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("cartToken")) {
                    cartToken = cookie.getValue();//获取购物车凭证
                    //获取购物车中的商品
                    map=cartService.getCart(cartToken);
                    model.addAttribute("carts",map);
                    //找到后跳出循环
                    flag=true;
                    break;
                }
            }
        }else {
            model.addAttribute("carts",null);
        }
        return "shopcar";
    }

    @RequestMapping("/deleteAll")
    @ResponseBody
    public AjaxRes deleteAll(HttpServletRequest request){
        AjaxRes ajaxRes = new AjaxRes();
        User user = (User) request.getSession().getAttribute("user");
        try {
            cartService.deleteAll(user.getId());
            ajaxRes.setSuccess(true);
            ajaxRes.setMsg("删除成功");
        }catch (Exception e){
            ajaxRes.setMsg("删除失败");
            ajaxRes.setSuccess(false);
        }
        return ajaxRes;
    }
    @RequestMapping("/deleteCart")
    @ResponseBody
    public AjaxRes deleteCart(Long id,HttpServletRequest request){
        AjaxRes ajaxRes = new AjaxRes();
        User user = (User) request.getSession().getAttribute("user");
        try {
            cartService.deleteCart(user.getId(),id);
            ajaxRes.setSuccess(true);
            ajaxRes.setMsg("删除成功");
        }catch (Exception e){
            ajaxRes.setMsg("删除失败");
            ajaxRes.setSuccess(false);
        }
        return ajaxRes;
    }
}
