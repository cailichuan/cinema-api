package com.cinema.write_api.mapper;


import model.entity.Upload;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface WriteUploadMapper {

    void insert(Upload upload);


    List<Upload> selectByMap(Map map);


    void deleteByMap(Map map);
}
