package com.cinema.read_api.mapper;


import model.entity.Role;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;


@Mapper
public interface ReadRoleMapper {

    List<Role> selectByMap(Map map);
}
