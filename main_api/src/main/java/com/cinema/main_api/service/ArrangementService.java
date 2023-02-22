package com.cinema.main_api.service;

import model.entity.Arrangement;
import model.vo.ArrangementVo;

import java.util.List;
import java.util.Map;

public interface ArrangementService {

    void save(Arrangement arrangement);

    List<Arrangement> findAll();

    ArrangementVo findByFilmId(Long fid);

    List<Integer> getSeatsHaveSelected(Long id);

    Map<String,Object> findById(Long id);

    void deleteById(Long id);

    Arrangement Update(Arrangement arrangement);
}
