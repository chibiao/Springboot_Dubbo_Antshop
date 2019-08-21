package com.itlike.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 用于跳转页面
 *
 * @author : 迟彪
 * @date : 2019/8/4
 */
@Controller
public class AdminIndexController {
    @RequestMapping("/category")
    public String category() {
        return "admin/category";
    }

    @RequestMapping("/secondCategory")
    public String secondCategory() {
        return "admin/secondcategory";
    }

    @RequestMapping("/product")
    public String product() {
        return "admin/product";
    }

    @RequestMapping("/admin")
    public String admin() {
        return "admin/admin";
    }

    @RequestMapping("/admin/index")
    public String index() {
        return "admin/index";
    }

    @RequestMapping("/user")
    public String user() {
        return "admin/user";
    }

    @RequestMapping("/menu")
    public String menu() {
        return "admin/menu";
    }

    @RequestMapping("/role")
    public String role() {
        return "admin/role";
    }
    @RequestMapping("/order")
    public String order() {
        return "admin/order";
    }
}
