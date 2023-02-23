package com.cinema.write_api.service.impl;


import com.cinema.write_api.mapper.WriteActivityMapper;
import com.cinema.write_api.service.WriteActivityService;
import com.cinema.write_api.util.GetSnowId;
import com.cinema.write_api.util.RedisUtils;
import lombok.extern.slf4j.Slf4j;
import model.entity.Activity;
import org.springframework.stereotype.Service;
import type.RedisTable;
import type.SnowIdType;
import util.DataTimeUtil;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
public class WriteActivityServiceImpl implements WriteActivityService {

    @Resource
    private GetSnowId snowId;

    @Resource
    private WriteActivityMapper writeActivityMapper;

    @Resource
    private RedisUtils redisUtils;

    @Override
    public void create(Activity activity) {
        snowId.setSonwIdType(SnowIdType.Activity);
        Long id = snowId.nextId();
        activity.setId(id);
        activity.setCreateAt(DataTimeUtil.getNowDateTimeString());

        writeActivityMapper.insert(activity);

        log.info("写入缓存");
        redisUtils.hPut(RedisTable.HASH.ACTIVITY,String.valueOf(id),String.valueOf(activity));
    }

    @Override
    public void deleteById(Long id) {

        Map<String,Long> map = new HashMap<>();
        map.put("id",id);
        writeActivityMapper.deleteByMap(map);

        log.info("删除缓存");
        redisUtils.hDelete(RedisTable.HASH.ACTIVITY,String.valueOf(id));
    }
}
