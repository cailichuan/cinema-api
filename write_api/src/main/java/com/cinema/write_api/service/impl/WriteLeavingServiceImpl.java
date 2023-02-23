package com.cinema.write_api.service.impl;

import com.cinema.write_api.mapper.WriteLeavingMessageMapper;
import com.cinema.write_api.service.WriteLeavingMessageService;
import com.cinema.write_api.util.GetSnowId;
import com.cinema.write_api.util.RedisUtils;
import lombok.extern.slf4j.Slf4j;
import model.entity.LeavingMessage;
import org.springframework.stereotype.Service;
import type.RedisTable;
import type.SnowIdType;
import util.DataTimeUtil;

import javax.annotation.Resource;

@Slf4j
@Service
public class WriteLeavingServiceImpl implements WriteLeavingMessageService {

    @Resource
    private WriteLeavingMessageMapper writeLeavingMessageMapper;

    @Resource
    private GetSnowId getSnowId;

    @Resource
    private RedisUtils redisUtils;

    @Override
    public void save(LeavingMessage leavingMessage) {

        getSnowId.setSonwIdType(SnowIdType.LEAVINGMESSAGE);
        long id = getSnowId.nextId();

        leavingMessage.setId(id);
        leavingMessage.setCreateAt(DataTimeUtil.getNowDateTimeString());

        writeLeavingMessageMapper.insert(leavingMessage);

        log.info("写入缓存");
        redisUtils.hPut(RedisTable.HASH.LEAVINGMESSAGE,String.valueOf(id),String.valueOf(leavingMessage));

    }

    @Override
    public void reply(LeavingMessage leavingMessage) {

        writeLeavingMessageMapper.update(leavingMessage);

        log.info("写入缓存");
        redisUtils.hPut(RedisTable.HASH.LEAVINGMESSAGE,String.valueOf(leavingMessage.getId()),String.valueOf(leavingMessage));

    }
}
