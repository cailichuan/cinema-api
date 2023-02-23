package com.cinema.write_api.mapper;


import model.entity.DailyWork;
import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

@Mapper
public interface WriteDailyWorkMapper {
    void insert(DailyWork dailyWork);

    void deleteByMap(Map map);
}

