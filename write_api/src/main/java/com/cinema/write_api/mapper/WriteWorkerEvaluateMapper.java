package com.cinema.write_api.mapper;


import model.entity.WorkerEvaluate;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface WriteWorkerEvaluateMapper {

    void insert(WorkerEvaluate workerEvaluate);
}
