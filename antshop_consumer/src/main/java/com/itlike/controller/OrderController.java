package com.itlike.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.itlike.pojo.*;
import com.itlike.service.OrderService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * @author : 迟彪
 * @date : 2019/8/9
 */
@Controller
@RequestMapping("/order")
public class OrderController {
    @Reference
    OrderService orderService;

    @RequestMapping("/addOrders")
    @ResponseBody
    public AjaxRes addOrders(@RequestBody JSONObject obj,HttpServletRequest request){
        User user = (User) request.getSession().getAttribute("user");
        //获取所有提交的订单
        AjaxRes ajaxRes = new AjaxRes();
        String data=obj.toJSONString();
        JSONObject json = JSON.parseObject(data);
        List<Cart> carts = JSONArray.parseArray(json.getString("carts"), Cart.class);
        int total = Integer.parseInt(json.getString("total"));
        ajaxRes.setMsg("提交成功");
        ajaxRes.setSuccess(true);
        return ajaxRes;
    }
    @RequestMapping("/orderIndex")
    public String getOrderListByUser(Map<String,Object> map, Long id, HttpServletRequest request){
        User user = (User) request.getSession().getAttribute("user");
        List<Orders> orders = orderService.getOrderListByUser(user.getId());
        map.put("orders",orders);
        return "order";
    }

    @RequestMapping("/orderAdminList")
    @ResponseBody
    public PageListRes orderAdminList(QueryVo vo){
        return orderService.getAllOrders(vo);
    }

    @RequestMapping("/updateSendState")
    @ResponseBody
    public AjaxRes updateSendState(String uuid){
        AjaxRes ajaxRes = new AjaxRes();
        try {
            orderService.updateSendState(uuid);
            ajaxRes.setMsg("发货成功");
            ajaxRes.setSuccess(true);
        }catch (Exception e){
            ajaxRes.setSuccess(false);
            ajaxRes.setMsg("发货失败");
        }
        return ajaxRes;
    }
    @RequestMapping("/updatePayState")
    public String updatePayState(String orderId){
        orderService.updatePayState(orderId);
        return "forward:/order/orderIndex";
    }
}
