package com.cinema.main_api.service;

import model.entity.DailyWork;

import java.util.List;

public interface DailyWorkService {

    void save(DailyWork dailyWork,String uuid) throws Exception;

    List<DailyWork> findAll() throws ClassNotFoundException;

    void deleteById(Long id,String uuid) throws Exception;
}
