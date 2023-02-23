package com.cinema.main_api.service.impl;


import api.read.ReadApi;
import api.write.WriteApi;
import com.cinema.main_api.service.WorkerEvaluateService;
import com.cinema.main_api.util.RedisUtils;
import lombok.extern.slf4j.Slf4j;
import model.entity.WorkerEvaluate;
import org.springframework.cache.annotation.CacheConfig;

import org.springframework.stereotype.Service;
import type.RedisTable;
import util.PatternUtil;
import util.RDTCUitl;


import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Slf4j
@Service
public class WorkerEvaluateServiceImpl implements WorkerEvaluateService {

    @Resource
    private ReadApi readApi;

    @Resource
    private WriteApi writeApi;

    @Resource
    private RedisUtils redisUtils;



    @Override
    public void save(WorkerEvaluate workerEvaluate,String uuid) throws Exception {

        writeApi.saveWorkerEvaluate(workerEvaluate,uuid);
    }

    @Override
    public void deleteById(Long id,String uuid) throws Exception {

        writeApi.deleteWorkerEvaluateById(id,uuid);
    }

    @Override
    public void deleteAllByWid(Long wid,String uuid) {


    }

    @Override
    public List<WorkerEvaluate> findByWorkerId(Long wid) throws ClassNotFoundException {
        //生成key
        String key= RedisTable.SET.WORKER_ID_BY_WORKEREVALUATE_ID+wid;

        if (redisUtils.sSize(key)==0){

            log.info("查询数据库");

            List<WorkerEvaluate> workerEvaluates = readApi.findWorkerEvaluateByWid(wid);


            //写入缓存
            for (WorkerEvaluate w : workerEvaluates) {

                redisUtils.sAdd(key,String.valueOf(w.getId()));
                redisUtils.hPut(RedisTable.HASH.WORKEREVALUATE,String.valueOf(w.getId()),String.valueOf(w));
            }

            return workerEvaluates;
        }


        log.info("查询缓存");
        Set<Object> objects = redisUtils.setMembers(key);
        List<Long> wvids = PatternUtil.getStringNumLong(String.valueOf(objects));
        List<WorkerEvaluate> WES = new ArrayList<>();

        for (Long wvid : wvids) {

            Object WVO = redisUtils.hGet(RedisTable.HASH.WORKEREVALUATE, String.valueOf(wvid));
            WorkerEvaluate wv = null;
            if (WVO==null){
                wv = readApi.findWorkerEvaluateById(Long.valueOf(wvid));
                if (wv==null){
                    redisUtils.sRemove(key,String.valueOf(wv.getId()));
                    continue;
                }
                redisUtils.hPut(RedisTable.HASH.WORKEREVALUATE,String.valueOf(wvid),String.valueOf(wv));
            }
            else wv= new RDTCUitl<WorkerEvaluate>().getData(WVO,RDTCUitl.ClassName.WORKEREVALUATE);

            WES.add(wv);
        }

        return WES;
    }
}
