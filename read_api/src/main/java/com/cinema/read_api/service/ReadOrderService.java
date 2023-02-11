package com.cinema.read_api.service;

import model.entity.Cart;
import model.entity.Order;
import model.vo.OrderVo;

import java.util.List;

public interface ReadOrderService {

    Order create(Cart cart);

    Order pay(Integer id);

    void update(Order order);

    List<OrderVo> findAll();

    List<OrderVo> findByUser(Integer uid);
}
