package com.cinema.write_api.mapper;


import model.entity.Upload;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface WriteUploadMapper {

    void insert(Upload upload);


}
