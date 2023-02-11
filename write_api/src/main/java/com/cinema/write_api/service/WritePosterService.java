package com.cinema.write_api.service;

import model.entity.Poster;

import java.util.List;

public interface WritePosterService {
    void save(Poster poster);

    void update(Poster poster);



    void deleteById(Integer id);

    void deleteAll();
}
