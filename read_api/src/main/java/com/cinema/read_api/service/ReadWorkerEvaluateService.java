package com.cinema.read_api.service;


import model.entity.WorkerEvaluate;

import java.util.List;

public interface ReadWorkerEvaluateService {

    void save(WorkerEvaluate workerEvaluate);

    void deleteById(Integer id);

    //删除该客服的所有评价
    void deleteAllByWid(Integer wid);

    //根据客服id查询他的所有评价
    List<WorkerEvaluate> findByWorkerId(Integer wid);

}
