package com.cinema.read_api.service.impl;

import com.cinema.read_api.mapper.ReadDailyWorkMapper;
import com.cinema.read_api.service.ReadDailyWorkService;
import model.entity.DailyWork;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ReadDailyWorkServiceImpl implements ReadDailyWorkService {

    @Resource
    private ReadDailyWorkMapper readDailyWorkMapper;
    @Override
    public List<DailyWork> findAll() {
        return readDailyWorkMapper.selectList();
    }
}
