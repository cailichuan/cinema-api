package com.cinema.write_api.service;

import model.entity.Arrangement;
import model.vo.ArrangementVo;

import java.util.List;
import java.util.Map;

public interface WriteArrangementService {

    void save(Arrangement arrangement);





    void deleteById(Long id);


    Arrangement Update(Arrangement arrangement);

    List<Integer> getSeatSeatsHaveSelected(Long id);

    Arrangement findById(Long id);
}
