package com.cinema.main_api.service;

import model.entity.Film;

import java.util.List;

public interface FilmService {

    void save(Film film,String uuid) throws Exception;

    void deleteById(Long id,String uuid) throws Exception;

    List<Film> findAll();

    List<Film> findByRegionAndType(String region,String type) throws ClassNotFoundException;

    //获取热门电影
    List<Film> findHots(Integer limit) throws ClassNotFoundException;

    //根据电影名模糊查询
    List<Film> findLikeName(String name) throws ClassNotFoundException;

    Film findById(Long id) throws ClassNotFoundException;

    Film update(Film film,String uuid) throws Exception;
}
