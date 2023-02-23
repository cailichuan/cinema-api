package com.cinema.write_api.mapper;


import model.entity.OrderException;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface WriteOrderExceptionMapper {

    void insert(OrderException orderException);

    void update(OrderException orderException);
}
