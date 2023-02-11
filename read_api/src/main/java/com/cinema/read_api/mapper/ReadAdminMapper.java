package com.cinema.read_api.mapper;


import model.entity.Admin;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ReadAdminMapper {
    void insert(Admin admin);
}
