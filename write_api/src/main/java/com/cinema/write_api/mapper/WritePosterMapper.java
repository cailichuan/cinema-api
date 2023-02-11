package com.cinema.write_api.mapper;


import model.entity.Poster;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface WritePosterMapper {

    void insert(Poster poster);
}
