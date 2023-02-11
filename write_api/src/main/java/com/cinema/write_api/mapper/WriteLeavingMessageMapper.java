package com.cinema.write_api.mapper;


import model.entity.LeavingMessage;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface WriteLeavingMessageMapper {
    void insert(LeavingMessage leavingMessage);
}
