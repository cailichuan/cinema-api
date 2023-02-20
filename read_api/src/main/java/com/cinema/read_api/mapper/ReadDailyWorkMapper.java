package com.cinema.read_api.mapper;


import model.entity.DailyWork;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ReadDailyWorkMapper {
    List<DailyWork> selectList();
}

