package com.cinema.read_api.service.impl;

import com.cinema.read_api.mapper.ReadPosterMapper;
import com.cinema.read_api.service.ReadPosterService;
import model.entity.Poster;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class ReadPosterServiceImpl implements ReadPosterService {

    @Resource
    private ReadPosterMapper readPosterMapper;


    @Override
    public List<Poster> findByMap(Map map) {
        return readPosterMapper.selectListByMap(map);
    }
}
