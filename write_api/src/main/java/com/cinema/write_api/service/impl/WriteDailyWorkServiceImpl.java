package com.cinema.write_api.service.impl;

import com.cinema.write_api.mapper.WriteDailyWorkMapper;
import com.cinema.write_api.service.WriteDailyWorkService;
import com.cinema.write_api.util.GetSnowId;
import com.cinema.write_api.util.RedisUtils;
import lombok.extern.slf4j.Slf4j;
import model.entity.DailyWork;
import org.springframework.stereotype.Service;
import type.RedisTable;
import type.SnowIdType;
import util.DataTimeUtil;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
public class WriteDailyWorkServiceImpl implements WriteDailyWorkService {

    @Resource
    private GetSnowId getSnowId;

    @Resource
    private WriteDailyWorkMapper writeDailyWorkMapper;

    @Resource
    private RedisUtils redisUtils;

    @Override
    public void save(DailyWork dailyWork) {

        getSnowId.setSonwIdType(SnowIdType.DAILYWORK);
        long id = getSnowId.nextId();

        dailyWork.setId(id);
        dailyWork.setCreateAt(DataTimeUtil.getNowDateTimeString());

        writeDailyWorkMapper.insert(dailyWork);

        log.info("写入缓存");
        redisUtils.hPut(RedisTable.HASH.DAILY_WORK,String.valueOf(id),String.valueOf(dailyWork));

    }

    @Override
    public void deleteById(Long id) {

        Map<String,Long> map = new HashMap<>();
        map.put("id",id);

        writeDailyWorkMapper.deleteByMap(map);

        log.info("删除缓存");
        redisUtils.hDelete(RedisTable.HASH.DAILY_WORK,String.valueOf(id));

    }
}
