package com.itlike.service.impl;


import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itlike.mapper.OrdersMapper;
import com.itlike.mapper.ProductMapper;
import com.itlike.pojo.*;
import com.itlike.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author : 迟彪
 * @date : 2019/8/11
 */
@Component
@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    OrdersMapper ordersMapper;

    @Autowired
    ProductMapper productMapper;
    @Override
    public void addOrder(Orders order, User user) {

    }
    @Override
    public List<Orders> getOrderListByUser(Long id) {
        List<Orders> orders=ordersMapper.getOrderListByUser(id);
        for (Orders order : orders) {
            List<OrderItem> orderItemList = ordersMapper.getOrderItemByOrderId(order.getUuid());
            for (OrderItem orderItem : orderItemList) {
                Product product = productMapper.selectProductById(orderItem.getProductId());
                orderItem.setProduct(product);
            }
            order.setOrderItems(orderItemList);
        }
        return orders;
    }

    @Override
    public void updatePayState(String orderId) {
        ordersMapper.updatePayState(orderId);
    }

    @Override
    public Orders getMessage(String uuid) {
        return null;
    }

    @Override
    public void updateMessage(String uuid, String name, String phone, String addr) {

    }

    @Override
    public PageListRes getAllOrders(QueryVo vo) {
        PageListRes pageListRes = new PageListRes();
        Page<Object> page = PageHelper.startPage(vo.getPage(), vo.getRows());
        List<Orders> orders = ordersMapper.selectAll();
        pageListRes.setRows(orders);
        pageListRes.setTotal(page.getTotal());
        return pageListRes;
    }

    @Override
    public void updateSendState(String uuid) {
        ordersMapper.updateSendState(uuid);
    }

    @Override
    public void deleteOrder(String uuid) {
        ordersMapper.deleteOrder(uuid);
    }
}
