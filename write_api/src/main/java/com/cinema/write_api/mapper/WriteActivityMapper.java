package com.cinema.write_api.mapper;


import mapper.ActivityMapper;
import model.entity.Activity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface WriteActivityMapper {



    void insert(Activity activity);



    void deleteByMap(Map map);


    List<Activity> selectByMap(Map map);


    void update(Activity activity);
}
