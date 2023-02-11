package com.cinema.write_api.service;

import model.entity.Cart;
import model.entity.Order;
import model.vo.OrderVo;

import java.util.List;

public interface WriteOrderService {

    Order create(Cart cart);

    Order pay(Integer id);

    void update(Order order);


}
