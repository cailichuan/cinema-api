package com.cinema.main_api.service;


import model.dto.LoginDto;
import model.entity.Worker;

import java.util.List;

public interface WorkerService {

    Worker create(Worker worker,String uuid) throws Exception;

    Worker login(LoginDto dto) throws Exception;

    void update(Worker worker,String uuid) throws Exception;

    List<Worker> findAll() throws ClassNotFoundException;

    Worker findById(Long id) throws ClassNotFoundException;

    void deleteById(Long id,String uuid) throws Exception;

}
