package com.cinema.main_api.service.impl;


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

    public FilmServiceImpl() {
        super();
    }

    @Override
    public void save(Film film) {

    }

    @Override
    public void deleteById(Integer id) {

    }

    @Override
    public List<Film> findAll() {
        return null;
    }

    @Override
    public List<Film> findByRegionAndType(String region, String type) {
        return null;
    }

    @Override
    public List<Film> findHots(Integer limit) {
        return null;
    }

    @Override
    public List<Film> findLikeName(String name) {
        return null;
    }

    @Override
    public Film findById(Integer id) {
        return null;
    }

    @Override
    public Film update(Film film) {
        return null;
    }
}
