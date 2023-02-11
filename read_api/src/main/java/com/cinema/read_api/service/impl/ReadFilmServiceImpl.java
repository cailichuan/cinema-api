package com.cinema.read_api.service.impl;

import com.cinema.read_api.mapper.ReadFilmMapper;
import com.cinema.read_api.service.ReadFilmService;
import model.entity.Film;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ReadFilmServiceImpl implements ReadFilmService {

    @Resource
    private ReadFilmMapper readFilmMapper;

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
        return readFilmMapper.selectById(id);
    }
}
