package com.cinema.read_api.mapper;


import model.entity.FilmEvaluate;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface ReadFilmEvaluateMapper {
    List<FilmEvaluate> selectListByMap(Map map);



}
