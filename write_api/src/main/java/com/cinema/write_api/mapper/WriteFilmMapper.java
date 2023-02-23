package com.cinema.write_api.mapper;


import model.entity.Film;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface WriteFilmMapper {
    void insert(Film film);

    List<Film> selectByMap(Map map);

    void update(Film film);

    void deleteByMap(Map map);
}
