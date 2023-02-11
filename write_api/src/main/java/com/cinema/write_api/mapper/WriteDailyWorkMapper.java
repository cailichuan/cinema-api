package com.cinema.write_api.mapper;


import model.entity.DailyWork;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface WriteDailyWorkMapper {
    void insert(DailyWork dailyWork);
}

