package com.cinema.main_api.service;


import model.dto.LoginDto;
import model.entity.Worker;

import java.util.List;

public interface WorkerService {

    Worker create(Worker worker) throws Exception;

    Worker login(LoginDto dto) throws Exception;

    void update(Worker worker) throws Exception;

    List<Worker> findAll();

    Worker findById(Integer id);

    void deleteById(Integer id);

}
