package com.itlike.service;


import com.itlike.pojo.Orders;
import com.itlike.pojo.PageListRes;
import com.itlike.pojo.QueryVo;
import com.itlike.pojo.User;

import java.util.List;

public interface OrderService {
    void addOrder(Orders order, User user) ;

    List<Orders> getOrderListByUser(Long id) ;

    void updatePayState(String orderId);

    Orders getMessage(String uuid);

    void updateMessage(String uuid, String name, String phone, String addr);

    PageListRes getAllOrders(QueryVo vo);

    void updateSendState(String uuid);

    void deleteOrder(String uuid);
}
