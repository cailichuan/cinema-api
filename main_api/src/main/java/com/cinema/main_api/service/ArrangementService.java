package com.cinema.main_api.service;

import model.entity.Arrangement;
import model.vo.ArrangementVo;
import model.vo.OneArrangementVo;

import java.util.List;
import java.util.Map;

public interface ArrangementService {

    void save(Arrangement arrangement,String uuid) throws Exception;

    List<Arrangement> findAll() throws ClassNotFoundException;

    ArrangementVo findByFilmId(Long fid) throws ClassNotFoundException;

    List<Integer> getSeatsHaveSelected(Long id);

    OneArrangementVo findById(Long id) throws ClassNotFoundException;

    void deleteById(Long id,String uuid) throws Exception;

    Arrangement Update(Arrangement arrangement,String uuid) throws Exception;
}
