package com.cinema.main_api.service.impl;


import com.cinema.main_api.service.LeavingMessageService;
import model.entity.LeavingMessage;
import model.vo.ActiveUserVo;
import model.vo.LeavingMessageVo;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;


import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
@CacheConfig(cacheNames = "leavingMessage")
public class LeavingMessageServiceImpl implements LeavingMessageService {

    public LeavingMessageServiceImpl() {
        super();
    }

    @Override
    public void save(LeavingMessage leavingMessage) {

    }

    @Override
    public void reply(LeavingMessage leavingMessage) {

    }

    @Override
    public List<LeavingMessageVo> findAll() {
        return null;
    }

    @Override
    public List<ActiveUserVo> findActiveUsers() {
        return null;
    }
}
