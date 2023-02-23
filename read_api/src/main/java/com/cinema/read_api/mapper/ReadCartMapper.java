package com.cinema.read_api.mapper;


import model.entity.Cart;
import model.vo.CartVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface ReadCartMapper {
    List<Cart> selectListByMap(Map map);
}
