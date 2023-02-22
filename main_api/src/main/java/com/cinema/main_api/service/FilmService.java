package com.cinema.main_api.service;

import model.entity.Film;

import java.util.List;

public interface FilmService {

    void save(Film film);

    void deleteById(Long id);

    List<Film> findAll();

    List<Film> findByRegionAndType(String region,Integer type);

    //获取热门电影
    List<Film> findHots(Integer limit);

    //根据电影名模糊查询
    List<Film> findLikeName(String name);

    Film findById(Long id);

    Film update(Film film);
}
