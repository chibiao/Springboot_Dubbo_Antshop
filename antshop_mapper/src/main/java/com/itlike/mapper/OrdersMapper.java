package com.itlike.mapper;

import com.itlike.pojo.OrderItem;
import com.itlike.pojo.Orders;
import java.util.List;

public interface OrdersMapper {
    int deleteByPrimaryKey(String uuid);

    int insert(Orders record);

    Orders selectByPrimaryKey(String uuid);

    List<Orders> selectAll();

    int updateByPrimaryKey(Orders record);

    List<Orders> getOrderListByUser(Long id);

    List<OrderItem> getOrderItemByOrderId(String uuid);

    void deleteOrder(String uuid);

    void updateSendState(String uuid);

    void updatePayState(String orderId);
}