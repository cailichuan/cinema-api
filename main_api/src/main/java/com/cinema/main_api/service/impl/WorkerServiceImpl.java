package com.cinema.main_api.service.impl;


import api.read.ReadApi;
import api.write.WriteApi;
import com.cinema.main_api.service.WorkerService;
import com.cinema.main_api.util.RedisUtils;
import lombok.extern.slf4j.Slf4j;
import model.dto.LoginDto;
import model.entity.Worker;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import type.RedisTable;
import util.RDTCUitl;


import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
public class WorkerServiceImpl implements WorkerService {

    @Resource
    private ReadApi readApi;

    @Resource
    private WriteApi writeApi;

    @Resource
    private RedisUtils redisUtils;


    @Override
    public Worker create(Worker worker,String uuid) throws Exception {

        writeApi.saveWorker(worker,uuid);
        return worker;
    }

    @Override
    public Worker login(LoginDto dto) throws Exception {
        return null;
    }

    @Override
    public void update(Worker worker,String uuid) throws Exception {

        writeApi.updateWorker(worker,uuid);
    }

    @Override
    public List<Worker> findAll() throws ClassNotFoundException {
        if (redisUtils.hSize(RedisTable.HASH.WORKER)==0) {

            log.info("查询数据库");
            List<Worker> allWorker = readApi.findAllWorker();

            for (Worker worker : allWorker) {
                redisUtils.hPut(RedisTable.HASH.WORKER,String.valueOf(worker.getId()),String.valueOf(worker));
            }

            return allWorker;
        }


        log.info("查询缓存");
        List<Object> WOs = redisUtils.hValues(RedisTable.HASH.WORKER);
        List<Worker> workers = new ArrayList<>();
        for (Object wo : WOs) {

            Worker worker = new RDTCUitl<Worker>().getData(wo, RDTCUitl.ClassName.WORKER);
            workers.add(worker);

        }


        return workers;
    }

    @Override
    public Worker findById(Long id) throws ClassNotFoundException {

        if (!redisUtils.hExists(RedisTable.HASH.WORKER,String.valueOf(id))) {
            log.info("查询数据库");
            Worker worker = readApi.findWorkerById(id);
            redisUtils.hPut(RedisTable.HASH.WORKER,String.valueOf(id),String.valueOf(worker));

            return worker;
        }

        log.info("查询缓存");
        Object WO = redisUtils.hGet(RedisTable.HASH.WORKER, String.valueOf(id));
        Worker worker = new RDTCUitl<Worker>().getData(WO, RDTCUitl.ClassName.WORKER);

        return worker;
    }

    @Override
    public void deleteById(Long id,String uuid) throws Exception {

        writeApi.deleteWorkerById(id,uuid);
    }
}
