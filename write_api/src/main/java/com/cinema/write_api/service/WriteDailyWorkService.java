package com.cinema.write_api.service;

import model.entity.DailyWork;

import java.util.List;

public interface WriteDailyWorkService {

    void save(DailyWork dailyWork);

    void deleteById(Integer id);
}
