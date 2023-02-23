package com.cinema.main_api.service.impl;


import api.read.ReadApi;
import api.write.WriteApi;
import com.cinema.main_api.service.OrderExceptionService;
import com.cinema.main_api.util.RedisUtils;
import lombok.extern.slf4j.Slf4j;
import model.entity.OrderException;
import org.springframework.stereotype.Service;
import type.RedisTable;
import util.RDTCUitl;


import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
public class OrderExceptionServiceImpl implements OrderExceptionService {

    @Resource
    private ReadApi readApi;

    @Resource
    private WriteApi writeApi;


    @Resource
    private RedisUtils redisUtils;

    @Override
    public OrderException create(OrderException orderException,String uuid) throws Exception {
        writeApi.saveOrderException(orderException,uuid);

        return orderException;
    }

    @Override
    public List<OrderException> findAll() throws ClassNotFoundException {

        if (redisUtils.hSize(RedisTable.HASH.ORDEREXCEPTION)==0) {
            log.info("查询数据库");
            List<OrderException> allOrderException = readApi.findAllOrderException();

            log.info("写入缓存");
            for (OrderException orderException : allOrderException) {
                redisUtils.hPut(RedisTable.HASH.ORDEREXCEPTION,String.valueOf(orderException.getId()),String.valueOf(orderException));
            }

            return allOrderException;
        }

        log.info("查询缓存");
        List<OrderException> orderExceptionList=new ArrayList<>();

        List<Object> objects = redisUtils.hValues(RedisTable.HASH.ORDEREXCEPTION);
        for (Object object : objects) {

            OrderException  orderException= new RDTCUitl<OrderException>().getData(object, RDTCUitl.ClassName.ORDEREXCEPTION);
            orderExceptionList.add(orderException);
        }

        return orderExceptionList;
    }

    @Override
    public void handleException(OrderException orderException,String uuid) throws Exception {

        writeApi.handleOrderException(orderException,uuid);

    }
}
