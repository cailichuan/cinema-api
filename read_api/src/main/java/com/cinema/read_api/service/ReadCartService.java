package com.cinema.read_api.service;

import model.entity.Cart;
import model.vo.CartVo;

import java.util.List;

public interface ReadCartService {



    List<CartVo> findAllByUserId(Long id);

    Cart findById(Long id);

}
