package com.cinema.write_api.service;

import model.entity.Arrangement;
import model.vo.ArrangementVo;

import java.util.List;

public interface WriteArrangementService {

    void save(Arrangement arrangement);





    void deleteById(Integer id);

    Arrangement Update(Arrangement arrangement);
}
