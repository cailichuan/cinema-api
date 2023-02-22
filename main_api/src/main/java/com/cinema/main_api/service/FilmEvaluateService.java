package com.cinema.main_api.service;

import model.entity.FilmEvaluate;
import model.vo.FilmEvaluateVo;

import java.util.List;

public interface FilmEvaluateService {
    public void save(FilmEvaluate filmEvaluate) throws Exception;


    public List<FilmEvaluateVo> findAllByFilmId(Long fid);


    public void deleteAllByFilmId(Long fid);
    public void deleteById(Long id);
}
