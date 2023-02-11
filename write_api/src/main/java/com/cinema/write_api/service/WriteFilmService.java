package com.cinema.write_api.service;

import model.entity.Film;

import java.util.List;

public interface WriteFilmService {

    void save(Film film);

    void deleteById(Integer id);



    Film update(Film film);
}
