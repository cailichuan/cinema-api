package com.cinema.write_api.mapper;


import model.entity.Admin;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface WriteAdminMapper {
    void insert(Admin admin);
}
