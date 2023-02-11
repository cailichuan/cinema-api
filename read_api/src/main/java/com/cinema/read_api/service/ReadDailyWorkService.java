package com.cinema.read_api.service;

import model.entity.DailyWork;

import java.util.List;

public interface ReadDailyWorkService {

    void save(DailyWork dailyWork);

    List<DailyWork> findAll();

    void deleteById(Integer id);
}
