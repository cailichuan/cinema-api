package com.cinema.read_api.mapper;


import model.entity.WorkerEvaluate;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface ReadWorkerEvaluateMapper {


    List<WorkerEvaluate> selectListByMap (Map map);

}
