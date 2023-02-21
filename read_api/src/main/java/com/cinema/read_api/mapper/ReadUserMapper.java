package com.cinema.read_api.mapper;


import model.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ReadUserMapper {

    User selectById(Integer id);

    List<User> selectList();
}
