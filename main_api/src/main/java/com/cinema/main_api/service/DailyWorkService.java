package com.cinema.main_api.service;

import model.entity.DailyWork;

import java.util.List;

public interface DailyWorkService {

    void save(DailyWork dailyWork);

    List<DailyWork> findAll();

    void deleteById(Integer id);
}
