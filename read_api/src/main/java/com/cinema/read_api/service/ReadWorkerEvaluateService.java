package com.cinema.read_api.service;


import model.entity.WorkerEvaluate;

import java.util.List;

public interface ReadWorkerEvaluateService {








    //根据客服id查询他的所有评价
    List<WorkerEvaluate> findByWorkerId(Long wid);


    WorkerEvaluate findById(Long id);

}
