package com.cinema.read_api.mapper;


import model.entity.Order;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ReadOrderMapper {

    List<Order> selectList();

    List<Order> selectListByAid(Integer aid);
}
