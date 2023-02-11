package com.cinema.read_api.mapper;


import model.entity.Film;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ReadFilmMapper {
    Film selectById(Integer id);

}
