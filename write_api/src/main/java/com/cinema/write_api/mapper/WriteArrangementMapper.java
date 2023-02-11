package com.cinema.write_api.mapper;


import model.entity.Arrangement;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface WriteArrangementMapper {
    void insert(Arrangement arrangement);

    void deleteById(Integer id);

    void updateById(Arrangement arrangement);
}
