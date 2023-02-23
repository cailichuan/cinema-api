package com.cinema.write_api.mapper;


import model.entity.Registration;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface WriteRegistrationMapper {

    void insert(Registration registration);

    List<Registration> selectByMap(Map map);

    void deleteByMap(Map map);
}
