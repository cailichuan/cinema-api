package com.cinema.main_api.service.impl;


import com.cinema.main_api.service.FilmEvaluateService;
import model.entity.FilmEvaluate;
import model.vo.FilmEvaluateVo;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;


import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
@CacheConfig(cacheNames = "filmEvaluate")
public class FilmEvaluateServiceImpl implements FilmEvaluateService {

    public FilmEvaluateServiceImpl() {
        super();
    }

    @Override
    public void save(FilmEvaluate filmEvaluate) throws Exception {

    }

    @Override
    public List<FilmEvaluateVo> findAllByFilmId(Integer fid) {
        return null;
    }

    @Override
    public void deleteAllByFilmId(Integer fid) {

    }

    @Override
    public void deleteById(Integer id) {

    }
}