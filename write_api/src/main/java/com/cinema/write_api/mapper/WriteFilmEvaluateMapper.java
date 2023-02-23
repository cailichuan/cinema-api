package com.cinema.write_api.mapper;


import model.entity.FilmEvaluate;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface WriteFilmEvaluateMapper {
    void insert(FilmEvaluate filmEvaluate);

    List<FilmEvaluate> selectByMap(Map map);

    void deleteByMap(Map map);
}
