package com.cinema.read_api.service;

import model.entity.Poster;

import java.util.List;

public interface ReadPosterService {
    void save(Poster poster);

    void update(Poster poster);

    List<Poster> findAll();

    List<Poster> findByStatus(boolean status);

    void deleteById(Integer id);

    void deleteAll();
}
