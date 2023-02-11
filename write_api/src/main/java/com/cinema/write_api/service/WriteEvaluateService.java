package com.cinema.write_api.service;


import model.entity.WorkerEvaluate;

import java.util.List;

public interface WriteEvaluateService {

    void save(WorkerEvaluate workerEvaluate);

    void deleteById(Integer id);

    //删除该客服的所有评价
    void deleteAllByWid(Integer wid);



}
