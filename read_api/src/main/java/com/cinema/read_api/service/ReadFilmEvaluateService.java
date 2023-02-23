package com.cinema.read_api.service;

import model.entity.FilmEvaluate;
import model.vo.FilmEvaluateVo;

import java.util.List;

public interface ReadFilmEvaluateService {



    List<FilmEvaluateVo> findAllByFilmId(Long fid);


    FilmEvaluate findById(Long id);



}
