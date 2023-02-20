package com.cinema.read_api.mapper;


import model.entity.Cart;
import model.vo.CartVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ReadCartMapper {
    List<Cart> selectListByUid(Integer uid);
}
