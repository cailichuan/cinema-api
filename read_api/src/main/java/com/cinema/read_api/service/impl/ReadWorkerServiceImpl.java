package com.cinema.read_api.service.impl;


import com.cinema.read_api.mapper.ReadWorkerMapper;
import com.cinema.read_api.service.ReadWorkerService;
import model.dto.LoginDto;
import model.entity.Worker;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ReadWorkerServiceImpl implements ReadWorkerService {

    @Resource
    private ReadWorkerMapper readWorkerMapper;

    @Override
    public Worker login(LoginDto dto) throws Exception {
        return null;
    }

    @Override
    public List<Worker> findAll() {
        return readWorkerMapper.findByMap(null);
    }

    @Override
    public Worker findById(Long id) {
        Map<String,Long> map = new HashMap<>();
        map.put("id",id);

        List<Worker> workers = readWorkerMapper.findByMap(map);

        return workers==null? null:workers.get(0);

    }
}
