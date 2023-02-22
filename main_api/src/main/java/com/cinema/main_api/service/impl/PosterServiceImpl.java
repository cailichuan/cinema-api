package com.cinema.main_api.service.impl;


import api.read.ReadApi;
import com.cinema.main_api.service.PosterService;
import model.entity.Poster;
import org.springframework.stereotype.Service;


import javax.annotation.Resource;
import java.util.List;
import java.util.UUID;

@Service
public class PosterServiceImpl implements PosterService {

    @Resource
    private ReadApi readApi;
    @Override
    public void save(Poster poster) {

    }

    @Override
    public void update(Poster poster) {

    }


    @Override
    public List<Poster> findByMap(Boolean status) {
        return readApi.findPosterByMap(status);
    }

    @Override
    public void deleteById(Integer id) {

    }

    @Override
    public void deleteAll() {

    }
}
