package com.cinema.write_api.mapper;


import model.entity.Registration;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface WriteRegistrationMapper {

    void insert(Registration registration);
}
