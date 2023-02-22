package com.cinema.read_api.service.impl;

import com.cinema.read_api.mapper.ReadActivityMapper;
import com.cinema.read_api.service.ReadActivityService;
import mapper.ActivityMapper;
import model.entity.Activity;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ReadActivityServiceImpl implements ReadActivityService {


    @Resource
    ReadActivityMapper readActivityMapper;


   //=============================================================
    @Override
    public Activity finfById(Long id) {
        return readActivityMapper.selectById(id);
    }

    @Override
    public List<Activity> findAll() {
        return readActivityMapper.selectList();
    }

    //=====================================================


}
