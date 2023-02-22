package com.cinema.read_api.service;

import model.entity.FilmEvaluate;
import model.vo.FilmEvaluateVo;

import java.util.List;

public interface ReadFilmEvaluateService {



    public List<FilmEvaluateVo> findAllByFilmId(Long fid);



}
