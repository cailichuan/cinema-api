package com.cinema.main_api.service.impl;


import api.read.ReadApi;
import com.cinema.main_api.service.CartService;
import model.entity.Cart;
import model.vo.CartVo;
import org.springframework.cache.annotation.CacheConfig;

import org.springframework.stereotype.Service;


import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
@CacheConfig(cacheNames = "cart")
public class CartServiceImpl implements CartService {

    @Resource
    private ReadApi readApi;

    public CartServiceImpl() {
        super();
    }

    @Override
    public void save(Cart cart) {

    }

    @Override
    public void deleteAllByUserId(Integer uid) {

    }

    @Override
    public List<CartVo> findAllByUserId(Integer id) {
        return readApi.findAllCartVoByUid(id);
    }

    @Override
    public void deleteCarts(List<Cart> carts) {

    }

    @Override
    public void settleCarts(List<Cart> carts) {

    }
}
