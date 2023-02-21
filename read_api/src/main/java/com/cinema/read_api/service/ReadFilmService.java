package com.cinema.read_api.service;

import model.entity.Film;

import java.util.List;
import java.util.Map;

public interface ReadFilmService {





    List<Film> findByMap(Map map);

    //获取热门电影




    Film findById(Integer id);

}
