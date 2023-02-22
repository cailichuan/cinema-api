package com.cinema.read_api.service.impl;

import com.cinema.read_api.mapper.ReadArrangementMapper;
import com.cinema.read_api.mapper.ReadCartMapper;
import com.cinema.read_api.mapper.ReadFilmMapper;
import com.cinema.read_api.service.ReadCartService;
import model.entity.Arrangement;
import model.entity.Cart;
import model.entity.Film;
import model.vo.CartVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class ReadCartServiceImpl implements ReadCartService {

    @Resource
    private ReadCartMapper readCartMapper;

    @Resource
    private ReadArrangementMapper readArrangementMapper;

    @Resource
    private ReadFilmMapper readFilmMapper;
    @Override
    public List<CartVo> findAllByUserId(Long uid) {
        List<CartVo> result = new ArrayList<>();
        List<Cart> carts = readCartMapper.selectListByUid(uid);
        for (Cart cart : carts) {
            Arrangement arrangement = readArrangementMapper.selectById(cart.getAid());
            Film film = readFilmMapper.selectById(arrangement.getFid());
            CartVo cartVo = new CartVo(film, arrangement, cart);
            result.add(cartVo);
        }

        return result;
    }
}
