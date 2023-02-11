package com.cinema.main_api.service;

import model.entity.Arrangement;
import model.vo.ArrangementVo;

import java.util.List;
import java.util.Map;

public interface ArrangementService {

    void save(Arrangement arrangement);

    List<Arrangement> findAll();

    ArrangementVo findByFilmId(Integer fid);

    List<Integer> getSeatsHaveSelected(Integer id);

    Map<String,Object> findById(Integer id);

    void deleteById(Integer id);

    Arrangement Update(Arrangement arrangement);
}
