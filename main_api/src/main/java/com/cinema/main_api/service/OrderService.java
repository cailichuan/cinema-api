package com.cinema.main_api.service;

import model.entity.Cart;
import model.entity.Order;
import model.vo.OrderVo;

import java.util.List;

public interface OrderService {

    void create(Cart cart,String uuid) throws Exception;

    Order pay(Long id) throws Exception;

    void update(Order order,String uuid) throws Exception;

    List<OrderVo> findAll() throws ClassNotFoundException;

    List<OrderVo> findByUser(Long uid) throws ClassNotFoundException;
}
