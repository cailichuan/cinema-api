package com.cinema.main_api.service.impl;


import com.cinema.main_api.service.WorkerEvaluateService;
import model.entity.WorkerEvaluate;
import org.springframework.cache.annotation.CacheConfig;

import org.springframework.stereotype.Service;


import javax.annotation.Resource;
import java.util.List;
import java.util.UUID;

@Service
@CacheConfig(cacheNames = "workerEvaluate")
public class WorkerEvaluateServiceImpl implements WorkerEvaluateService {

    public WorkerEvaluateServiceImpl() {
        super();
    }

    @Override
    public void save(WorkerEvaluate workerEvaluate) {

    }

    @Override
    public void deleteById(Integer id) {

    }

    @Override
    public void deleteAllByWid(Integer wid) {

    }

    @Override
    public List<WorkerEvaluate> findByWorkerId(Integer wid) {
        return null;
    }
}
