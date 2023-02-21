package com.cinema.read_api.mapper;


import model.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ReadUserMapper {

    User selectById(Integer id);
}
