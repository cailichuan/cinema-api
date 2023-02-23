package com.cinema.read_api.service;


import model.dto.LoginDto;
import model.entity.Worker;

import java.util.List;

public interface ReadWorkerService {



    Worker login(LoginDto dto) throws Exception;



    List<Worker> findAll();

    Worker findById(Long id);


}
