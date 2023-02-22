package com.cinema.main_api.service.impl;


import api.read.ReadApi;
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

    @Resource
    private ReadApi readApi;


    @Override
    public void save(FilmEvaluate filmEvaluate) throws Exception {

    }

    @Override
    public List<FilmEvaluateVo> findAllByFilmId(Long fid) {
        return readApi.findFilmEvaluateVoByFid(fid);
    }

    @Override
    public void deleteAllByFilmId(Long fid) {

    }

    @Override
    public void deleteById(Long id) {

    }
}
