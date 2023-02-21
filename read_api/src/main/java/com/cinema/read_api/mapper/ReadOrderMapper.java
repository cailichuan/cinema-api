package com.cinema.read_api.mapper;


import model.entity.Order;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface ReadOrderMapper {



    List<Order> selectListByMap(Map map);


}
