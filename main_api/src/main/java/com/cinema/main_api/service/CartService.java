package com.cinema.main_api.service;

import model.entity.Cart;
import model.vo.CartVo;

import java.util.List;

public interface CartService {

    void save(Cart cart,String uuid) throws Exception;

    void deleteAllByUserId(Long uid,String uuid);

    List<CartVo> findAllByUserId(Long id) throws ClassNotFoundException;

    //删除用户选中的购物车
    void  deleteCarts(List<Cart> carts ,String uuid);

    //结算用户选中的购物车
    void settleCarts(List<Cart> carts,String uuid);

    void deleteById(Long id,String uuid) throws Exception;
}
