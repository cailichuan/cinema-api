package com.cinema.main_api.service.impl;


import com.cinema.main_api.service.DailyWorkService;
import model.entity.DailyWork;
import org.springframework.stereotype.Service;


import javax.annotation.Resource;
import java.util.List;
import java.util.UUID;

@Service
public class DailyWorkServiceImpl implements DailyWorkService {

    public DailyWorkServiceImpl() {
        super();
    }

    @Override
    public void save(DailyWork dailyWork) {

    }

    @Override
    public List<DailyWork> findAll() {
        return null;
    }

    @Override
    public void deleteById(Integer id) {

    }
}
