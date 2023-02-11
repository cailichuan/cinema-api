package com.cinema.write_api.service;

import model.entity.LeavingMessage;
import model.vo.ActiveUserVo;
import model.vo.LeavingMessageVo;

import java.util.List;

public interface WriteLeavingMessageService {

    void save(LeavingMessage leavingMessage);

    void reply(LeavingMessage leavingMessage);


}
