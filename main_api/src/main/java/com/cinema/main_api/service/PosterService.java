package com.cinema.main_api.service;

import model.entity.Poster;

import java.util.List;

public interface PosterService {
    void save(Poster poster);

    void update(Poster poster);


    List<Poster> findByMap(Boolean status);

    void deleteById(Integer id);

    void deleteAll();
}
