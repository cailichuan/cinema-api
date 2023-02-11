package com.cinema.write_api.mapper;


import model.entity.FilmEvaluate;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface WriteFilmEvaluateMapper {
    void insert(FilmEvaluate filmEvaluate);
}
