package com.cinema.write_api.mapper;


import model.entity.Film;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface WriteFilmMapper {
    void insert(Film film);
}
