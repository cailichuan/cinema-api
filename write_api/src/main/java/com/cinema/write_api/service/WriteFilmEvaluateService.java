package com.cinema.write_api.service;

import model.entity.FilmEvaluate;
import model.vo.FilmEvaluateVo;

import java.util.List;

public interface WriteFilmEvaluateService {
    public void save(FilmEvaluate filmEvaluate) throws Exception;





    public void deleteAllByFilmId(Long fid);
    public void deleteById(Long id);
}
