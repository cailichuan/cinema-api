package com.cinema.read_api.mapper;


import model.entity.Arrangement;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ReadArrangementMapper {
    List<Arrangement> selectList();

    List<Arrangement> selectListByFid(Integer fid);

    Arrangement selectById(Integer id);
}
