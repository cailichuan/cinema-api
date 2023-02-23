package com.cinema.write_api.mapper;


import model.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

@Mapper
public interface WriteUserMapper {

    void insert(User user);

    Integer countByUserName(String userName);

    void  deleteByMap(Map map);

    void update(User user);
}
