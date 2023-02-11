package com.cinema.read_api.service;

import model.entity.FilmEvaluate;
import model.vo.FilmEvaluateVo;

import java.util.List;

public interface ReadFilmEvaluateService {
    public void save(FilmEvaluate filmEvaluate) throws Exception;


    public List<FilmEvaluateVo> findAllByFilmId(Integer fid);


    public void deleteAllByFilmId(Integer fid);
    public void deleteById(Integer id);
}
