package com.cinema.read_api.mapper;


import model.entity.Worker;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface ReadWorkerMapper {

    List<Worker> findByMap(Map map);
}
