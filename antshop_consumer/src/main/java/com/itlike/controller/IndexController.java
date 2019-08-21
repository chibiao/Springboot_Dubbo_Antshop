package com.itlike.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author : 迟彪
 * @date : 2019/8/7
 */
@Controller
public class IndexController {
    @RequestMapping("/index")
    public String index() {
        return "index";
    }

    @RequestMapping("/loginIndex")
    public String loginIndex() {
        return "login";
    }

    @RequestMapping("/registIndex")
    public String registIndex() {
        return "regist";
    }
}
