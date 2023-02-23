package com.cinema.write_api.mapper;


import model.entity.Worker;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface WriteWorkerMapper {

    void insert(Worker worker);

    List<Worker> selectByMap(Map map);

    void update(Worker worker);

    void deleteByMap(Map map);
}
