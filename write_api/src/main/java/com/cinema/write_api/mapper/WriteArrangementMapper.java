package com.cinema.write_api.mapper;


import model.entity.Arrangement;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface WriteArrangementMapper {
    void insert(Arrangement arrangement);

    void deleteByMap(Map map);


    void update(Arrangement arrangement);

    List<Arrangement> selectByMap(Map map);
}
