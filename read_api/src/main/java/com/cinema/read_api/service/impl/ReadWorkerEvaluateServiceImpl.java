package com.cinema.read_api.service.impl;

import com.cinema.read_api.mapper.ReadWorkerEvaluateMapper;
import com.cinema.read_api.service.ReadWorkerEvaluateService;

import model.entity.WorkerEvaluate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ReadWorkerEvaluateServiceImpl  implements ReadWorkerEvaluateService {


    @Resource
    private ReadWorkerEvaluateMapper readWorkerEvaluateMapper;
    @Override
    public List<WorkerEvaluate> findByWorkerId(Long wid) {

        Map<String,Long> map = new HashMap<>();
        map.put("wid",wid);
        return readWorkerEvaluateMapper.selectListByMap(map);
    }

    @Override
    public WorkerEvaluate findById(Long id) {
        Map<String,Long> map=new HashMap<>();
        map.put("id",id);
        List<WorkerEvaluate> WES = readWorkerEvaluateMapper.selectListByMap(map);
        return WES==null? null: WES.get(0);
    }
}
