package com.cinema.read_api.mapper;


import model.entity.Registration;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ReadRegistrationMapper {

    List<Registration> selectList();
}
