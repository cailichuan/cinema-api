package com.cinema.write_api.service;

import model.entity.Cart;
import model.vo.CartVo;

import java.util.List;

public interface WriteCartService {

    void save(Cart cart);

    void deleteAllByUserId(Long uid);

    void deleteById(Long id);


    //删除用户选中的购物车
    void  deleteCarts(List<Cart> carts);

    //结算用户选中的购物车
    void settleCarts(List<Cart> carts) throws Exception;
}
