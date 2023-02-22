package com.cinema.read_api.mapper;


import mapper.ActivityMapper;
import model.entity.Activity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ReadActivityMapper {




    Activity selectById(Long id);


    List<Activity> selectList();


}
