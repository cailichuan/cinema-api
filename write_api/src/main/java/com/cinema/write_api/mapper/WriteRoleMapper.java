package com.cinema.write_api.mapper;


import model.entity.Role;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface WriteRoleMapper {

    void insert(Role role);

    List<Role> selectByMap(Map map);

    void deleteByMap(Map map);
}
