package com.cinema.write_api.service.impl;

import com.cinema.write_api.mapper.WriteOrderExceptionMapper;
import com.cinema.write_api.service.WriteOrderExceptionService;
import com.cinema.write_api.util.GetSnowId;
import com.cinema.write_api.util.RedisUtils;
import lombok.extern.slf4j.Slf4j;
import model.entity.OrderException;
import org.springframework.stereotype.Service;
import type.RedisTable;
import type.SnowIdType;
import util.DataTimeUtil;

import javax.annotation.Resource;

@Slf4j
@Service
public class WriteOrderExceptionServiceImpl implements WriteOrderExceptionService {

    @Resource
    private WriteOrderExceptionMapper writeOrderExceptionMapper;

    @Resource
    private GetSnowId getSnowId;

    @Resource
    private RedisUtils redisUtils;

    @Override
    public OrderException create(OrderException orderException) {

        getSnowId.setSonwIdType(SnowIdType.ORDEREXCEPTION);
        long id = getSnowId.nextId();


        orderException.setStatus(false);
        orderException.setId(id);
        orderException.setCreateAt(DataTimeUtil.getNowDateTimeString());

        writeOrderExceptionMapper.insert(orderException);

        log.info("写入缓存");
        redisUtils.hPut(RedisTable.HASH.ORDEREXCEPTION,String.valueOf(id),String.valueOf(orderException));

        return orderException;
    }

    @Override
    public void handleException(OrderException orderException) {

        orderException.setEndAt(DataTimeUtil.getNowDateTimeString());
        writeOrderExceptionMapper.update(orderException);

        log.info("写入缓存");
        redisUtils.hPut(RedisTable.HASH.ORDEREXCEPTION,String.valueOf(orderException.getId()),String.valueOf(orderException));

    }
}
