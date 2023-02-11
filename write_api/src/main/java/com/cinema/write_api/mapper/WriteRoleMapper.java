package com.cinema.write_api.mapper;


import model.entity.Role;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface WriteRoleMapper {

    void insert(Role role);
}
