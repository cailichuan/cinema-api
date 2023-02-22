package com.cinema.read_api.mapper;


import model.entity.Upload;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ReadUploadMapper {

   Upload selectById(Long id);
}
