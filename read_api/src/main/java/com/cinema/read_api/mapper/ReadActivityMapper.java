package com.cinema.read_api.mapper;


import mapper.ActivityMapper;
import model.entity.Activity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ReadActivityMapper extends ActivityMapper {


    @Override
    void insert(Activity activity);

    @Override
    Activity selectById(Integer id);

    @Override
    List<Activity> selectList();

    @Override
    void deleteById(Integer id);
}
