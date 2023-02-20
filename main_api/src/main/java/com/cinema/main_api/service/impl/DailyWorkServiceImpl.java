package com.cinema.main_api.service.impl;


import api.read.ReadApi;
import com.cinema.main_api.service.DailyWorkService;
import model.entity.DailyWork;
import org.springframework.stereotype.Service;


import javax.annotation.Resource;
import java.util.List;
import java.util.UUID;

@Service
public class DailyWorkServiceImpl implements DailyWorkService {

    @Resource
    private ReadApi readApi;

    public DailyWorkServiceImpl() {
        super();
    }

    @Override
    public void save(DailyWork dailyWork) {

    }

    @Override
    public List<DailyWork> findAll() {
        return readApi.findAllDailyWork();
    }

    @Override
    public void deleteById(Integer id) {

    }
}
