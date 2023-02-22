package com.cinema.read_api.mapper;


import model.entity.Poster;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface ReadPosterMapper {

    List<Poster> selectListByMap(Map map);
}
