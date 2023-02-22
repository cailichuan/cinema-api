package com.cinema.read_api.mapper;


import model.entity.FilmEvaluate;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ReadFilmEvaluateMapper {
    List<FilmEvaluate> selectListByFid(Long fid);

}
