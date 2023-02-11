package com.cinema.read_api.service;

import model.entity.Film;

import java.util.List;

public interface ReadFilmService {



    List<Film> findAll();

    List<Film> findByRegionAndType(String region,String type);

    //获取热门电影
    List<Film> findHots(Integer limit);

    //根据电影名模糊查询
    List<Film> findLikeName(String name);

    Film findById(Integer id);

}
