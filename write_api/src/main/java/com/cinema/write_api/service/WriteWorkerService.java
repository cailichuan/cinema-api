package com.cinema.write_api.service;


import model.dto.LoginDto;
import model.entity.Worker;

import java.util.List;

public interface WriteWorkerService {

    Worker create(Worker worker) throws Exception;



    void update(Worker worker) throws Exception;



    void deleteById(Long id);

}
