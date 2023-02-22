package com.cinema.read_api.service.impl;

import com.cinema.read_api.mapper.ReadFilmMapper;
import com.cinema.read_api.service.ReadFilmService;
import model.entity.Film;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ReadFilmServiceImpl implements ReadFilmService {

    @Resource
    private ReadFilmMapper readFilmMapper;



    @Override
    public List<Film> findByMap(Map map) {
        return readFilmMapper.selectListByMap(map);
    }

    @Override
    public Film findById(Long id) {
        return readFilmMapper.selectById(id);
    }
}
