package com.cinema.read_api.mapper;


import model.entity.LeavingMessage;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ReadLeavingMessageMapper {
    List<LeavingMessage> selectList();

    List<LeavingMessage> selectListByUid(Long id);
}
