package com.cinema.main_api.service.impl;


import com.cinema.main_api.service.WorkerService;
import model.dto.LoginDto;
import model.entity.Worker;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


import javax.annotation.Resource;
import java.util.List;
import java.util.UUID;

@Service
public class WorkerServiceImpl implements WorkerService {

    @Override
    public Worker create(Worker worker) throws Exception {
        return null;
    }

    @Override
    public Worker login(LoginDto dto) throws Exception {
        return null;
    }

    @Override
    public void update(Worker worker) throws Exception {

    }

    @Override
    public List<Worker> findAll() {
        return null;
    }

    @Override
    public Worker findById(Integer id) {
        return null;
    }

    @Override
    public void deleteById(Integer id) {

    }
}
