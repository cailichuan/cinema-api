package com.cinema.write_api.mapper;


import model.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface WriteUserMapper {

    void insert(User user);
}
