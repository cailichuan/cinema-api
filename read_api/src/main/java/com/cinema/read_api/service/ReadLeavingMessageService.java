package com.cinema.read_api.service;

import model.entity.LeavingMessage;
import model.vo.ActiveUserVo;
import model.vo.LeavingMessageVo;

import java.util.List;

public interface ReadLeavingMessageService {



    List<LeavingMessageVo> findAll();

    //获取活跃留言的用户
    List<ActiveUserVo> findActiveUsers();


    List<LeavingMessage> selectListByUid(Long uid);

    LeavingMessage findById(Long id);
}
