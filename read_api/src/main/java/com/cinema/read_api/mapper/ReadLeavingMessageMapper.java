package com.cinema.read_api.mapper;


import model.entity.LeavingMessage;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface ReadLeavingMessageMapper {
    List<LeavingMessage> selectListByMap(Map map);


}
