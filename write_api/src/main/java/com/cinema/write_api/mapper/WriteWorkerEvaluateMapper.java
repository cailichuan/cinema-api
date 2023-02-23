package com.cinema.write_api.mapper;


import model.entity.WorkerEvaluate;
import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

@Mapper
public interface WriteWorkerEvaluateMapper {

    void insert(WorkerEvaluate workerEvaluate);

    void deleteByMap(Map map);
}
