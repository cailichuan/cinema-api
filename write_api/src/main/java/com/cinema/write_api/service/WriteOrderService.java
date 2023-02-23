package com.cinema.write_api.service;

import model.entity.Cart;
import model.entity.Order;
import model.vo.OrderVo;

import java.util.List;

public interface WriteOrderService {

    Order create(Cart cart) throws Exception;

    Order pay(Long id) throws Exception;

    void update(Order order);




}
