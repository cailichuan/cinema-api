package com.cinema.write_api.mapper;


import mapper.ActivityMapper;
import model.entity.Activity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface WriteActivityMapper {



    void insert(Activity activity);



    void deleteById(Integer id);
}
