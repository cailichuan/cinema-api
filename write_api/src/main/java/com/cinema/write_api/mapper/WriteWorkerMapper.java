package com.cinema.write_api.mapper;


import model.entity.Worker;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface WriteWorkerMapper {

    void insert(Worker worker);
}
