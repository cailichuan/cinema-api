package com.cinema.main_api.service;


import model.entity.WorkerEvaluate;

import java.util.List;

public interface WorkerEvaluateService {

    void save(WorkerEvaluate workerEvaluate,String uuid) throws Exception;

    void deleteById(Long id,String uuid) throws Exception;

    //删除该客服的所有评价
    void deleteAllByWid(Long wid,String uuid);

    //根据客服id查询他的所有评价
    List<WorkerEvaluate> findByWorkerId(Long wid) throws ClassNotFoundException;

}
