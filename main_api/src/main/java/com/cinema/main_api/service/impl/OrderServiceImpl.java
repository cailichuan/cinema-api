package com.cinema.main_api.service.impl;


import api.read.ReadApi;
import com.cinema.main_api.service.OrderService;
import model.entity.Cart;
import model.entity.Order;
import model.vo.OrderVo;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;


import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@CacheConfig(cacheNames = "order")
public class OrderServiceImpl implements OrderService {

    @Resource
    private ReadApi readApi;

    @Override
    public Order create(Cart cart) {
        return null;
    }

    @Override
    public Order pay(Long id) {
        return null;
    }

    @Override
    public void update(Order order) {

    }

    @Override
    public List<OrderVo> findAll() {
        return readApi.findAllOrderVo();
    }

    @Override
    public List<OrderVo> findByUser(Long uid) {
        return readApi.findOrderVoByUserId(uid);
    }
}
