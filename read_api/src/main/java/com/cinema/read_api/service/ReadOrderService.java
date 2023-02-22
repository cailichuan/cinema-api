package com.cinema.read_api.service;

import model.entity.Cart;
import model.entity.Order;
import model.vo.OrderVo;

import java.util.List;
import java.util.Map;

public interface ReadOrderService {



    List<OrderVo> findOrderVoList(Long Uid);
}
