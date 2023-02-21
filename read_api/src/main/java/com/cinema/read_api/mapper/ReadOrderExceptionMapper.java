package com.cinema.read_api.mapper;


import model.entity.OrderException;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ReadOrderExceptionMapper {

    List<OrderException> selectList();
}
