package com.cinema.read_api.service;

import model.entity.Cart;
import model.vo.CartVo;

import java.util.List;

public interface ReadCartService {

    void save(Cart cart);

    void deleteAllByUserId(Integer uid);

    List<CartVo> findAllByUserId(Integer id);

    //删除用户选中的购物车
    void  deleteCarts(List<Cart> carts);

    //结算用户选中的购物车
    void settleCarts(List<Cart> carts);
}
