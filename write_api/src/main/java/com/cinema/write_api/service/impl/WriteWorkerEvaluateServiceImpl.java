package com.cinema.write_api.service.impl;

import com.cinema.write_api.mapper.WriteWorkerEvaluateMapper;
import com.cinema.write_api.service.WriteWorkerEvaluateService;
import com.cinema.write_api.util.GetSnowId;
import com.cinema.write_api.util.RedisUtils;
import lombok.extern.slf4j.Slf4j;
import model.entity.WorkerEvaluate;
import org.springframework.stereotype.Service;
import type.RedisTable;
import type.SnowIdType;
import util.DataTimeUtil;
import util.PatternUtil;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Slf4j
@Service
public class WriteWorkerEvaluateServiceImpl implements WriteWorkerEvaluateService {

    @Resource
    private WriteWorkerEvaluateMapper writeWorkerEvaluateMapper;

    @Resource
    private GetSnowId getSnowId;

    @Resource
    private RedisUtils redisUtils;


    @Override
    public void save(WorkerEvaluate workerEvaluate) {

        getSnowId.setSonwIdType(SnowIdType.WORKEREVALUATE);
        long id = getSnowId.nextId();

        workerEvaluate.setId(id);
        workerEvaluate.setCreateAt(DataTimeUtil.getNowDateTimeString());

        writeWorkerEvaluateMapper.insert(workerEvaluate);

        log.info("写入缓存");
        redisUtils.hPut(RedisTable.HASH.WORKEREVALUATE,String.valueOf(workerEvaluate.getId()),String.valueOf(workerEvaluate));
    }

    @Override
    public void deleteById(Long id) {

        Map<String,Long> map = new HashMap<>();
        map.put("id",id);

        writeWorkerEvaluateMapper.deleteByMap(map);

        log.info("删除缓存");
        redisUtils.hDelete(RedisTable.HASH.WORKEREVALUATE,String.valueOf(id));
    }

    @Override
    public void deleteAllByWid(Long wid) {

        Map<String,Long> map = new HashMap<>();
        map.put("wid",wid);

        writeWorkerEvaluateMapper.deleteByMap(map);

        log.info("删除缓存");
        //生成key
        String key = RedisTable.SET.WORKER_ID_BY_WORKEREVALUATE_ID+wid;
        Set<Object> objects = redisUtils.setMembers(key);
        List<Long> WEIds = PatternUtil.getStringNumLong(String.valueOf(objects));
        for (Long weId : WEIds) {
            redisUtils.hDelete(RedisTable.HASH.WORKEREVALUATE,String.valueOf(weId));
        }
        redisUtils.delete(key);
    }
}
