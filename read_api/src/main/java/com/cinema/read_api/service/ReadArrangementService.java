package com.cinema.read_api.service;

import model.entity.Arrangement;
import model.vo.ArrangementVo;

import java.util.List;

public interface ReadArrangementService {



    List<Arrangement> findAll();

    ArrangementVo findByFilmId(Long fid);

    List<Integer> getSeatSeatsHaveSelected(Long id);

    Arrangement findById(Long id);


}
