package com.cinema.write_api.mapper;


import model.entity.Cart;
import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

@Mapper
public interface WriteCartMapper {
    void insert(Cart cart);

    void deleteByMap(Map map);


}
