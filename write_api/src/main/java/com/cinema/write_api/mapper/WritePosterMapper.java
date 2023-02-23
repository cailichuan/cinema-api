package com.cinema.write_api.mapper;


import model.entity.Poster;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface WritePosterMapper {

    void insert(Poster poster);

    void update(Poster poster);

    void deleteByMap(Map map);

    List<Poster> selectByMap(Map map);
}
