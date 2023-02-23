package com.cinema.main_api.service;

import model.entity.FilmEvaluate;
import model.vo.FilmEvaluateVo;

import java.util.List;

public interface FilmEvaluateService {
    public void save(FilmEvaluate filmEvaluate,String uuid) throws Exception;


    public List<FilmEvaluateVo> findAllByFilmId(Long fid) throws ClassNotFoundException;


    public void deleteAllByFilmId(Long fid,String uuid) throws Exception;
    public void deleteById(Long id,String uuid) throws Exception;
}
