package com.cinema.write_api.service;

import model.entity.WorkerEvaluate;

public interface WriteWorkerEvaluateService {

    void save(WorkerEvaluate workerEvaluate);

    void deleteById(Long id);

    //删除该客服的所有评价
    void deleteAllByWid(Long wid);
}
