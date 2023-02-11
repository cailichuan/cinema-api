package com.cinema.main_api.service.impl;


import com.cinema.main_api.service.PosterService;
import model.entity.Poster;
import org.springframework.stereotype.Service;


import javax.annotation.Resource;
import java.util.List;
import java.util.UUID;

@Service
public class PosterServiceImpl implements PosterService {

    public PosterServiceImpl() {
        super();
    }

    @Override
    public void save(Poster poster) {

    }

    @Override
    public void update(Poster poster) {

    }

    @Override
    public List<Poster> findAll() {
        return null;
    }

    @Override
    public List<Poster> findByStatus(boolean status) {
        return null;
    }

    @Override
    public void deleteById(Integer id) {

    }

    @Override
    public void deleteAll() {

    }
}
