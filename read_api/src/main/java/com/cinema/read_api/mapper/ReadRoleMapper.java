package com.cinema.read_api.mapper;


import model.entity.Role;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface ReadRoleMapper {

    List<Role> selectListByWid(Long wid);
}
