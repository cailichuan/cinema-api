package com.cinema.write_api.mapper;


import model.entity.Order;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface WriteOrderMapper {

    void insert(Order order);
}
