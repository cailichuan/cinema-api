package com.cinema.read_api.mapper;


import model.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ReadUserMapper {
    User selectById(Long id);

    List<User> selectList();

    User selectByUserName(String userName);
}
