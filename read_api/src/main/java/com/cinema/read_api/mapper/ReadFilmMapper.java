package com.cinema.read_api.mapper;


import model.entity.Film;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface ReadFilmMapper {
    Film selectById(Long id);



    List<Film> selectListByMap(Map map);



}
