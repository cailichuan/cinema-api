package com.cinema.write_api.mapper;


import model.entity.Order;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface WriteOrderMapper {

    void insert(Order order);

    List<Order> selectListByMap(Map map);

    void update(Order order);
}
