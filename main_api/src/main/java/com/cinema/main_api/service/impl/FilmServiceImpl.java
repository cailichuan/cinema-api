package com.cinema.main_api.service.impl;


import api.read.ReadApi;
import com.cinema.main_api.service.FilmService;
import model.entity.Film;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;


import javax.annotation.Resource;
import java.util.List;

@Service
@CacheConfig(cacheNames = "film")
public class FilmServiceImpl implements FilmService {


    @Resource
    private ReadApi readApi;

    @Override
    public void save(Film film) {

    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public List<Film> findAll() {
        return readApi.findAllFilm(null,null);
    }

    @Override
    public List<Film> findByRegionAndType(String region, Integer type) {
        return readApi.findAllFilm(region,type);
    }

    @Override
    public List<Film> findHots(Integer limit) {
        return readApi.findFilmHots(limit);
    }

    @Override
    public List<Film> findLikeName(String name) {
        return readApi.searchFilmByName(name);
    }

    @Override
    public Film findById(Long id) {
        return readApi.findFilmById(id);
    }

    @Override
    public Film update(Film film) {
        return null;
    }
}
