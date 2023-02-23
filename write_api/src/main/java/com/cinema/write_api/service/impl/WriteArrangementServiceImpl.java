package com.cinema.write_api.service.impl;

import com.cinema.write_api.mapper.WriteArrangementMapper;
import com.cinema.write_api.mapper.WriteOrderMapper;
import com.cinema.write_api.service.WriteArrangementService;
import com.cinema.write_api.util.GetSnowId;
import com.cinema.write_api.util.RedisUtils;
import lombok.extern.slf4j.Slf4j;
import model.entity.Arrangement;
import model.entity.Order;
import org.springframework.stereotype.Service;
import type.RedisTable;
import type.SnowIdType;
import util.DataTimeUtil;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Slf4j
@Service
public class WriteArrangementServiceImpl implements WriteArrangementService {

    @Resource
    private GetSnowId snowId;
    @Resource
    private WriteArrangementMapper writeArrangementMapper;

    @Resource
    private WriteOrderMapper writeOrderMapper;


    @Resource
    private RedisUtils redisUtils;


    @Override
    public void save(Arrangement arrangement) {
        snowId.setSonwIdType(SnowIdType.Arrangement);
        Long id = snowId.nextId();
        arrangement.setId(id);
        writeArrangementMapper.insert(arrangement);

        log.info("更新缓存");
        redisUtils.hPut(RedisTable.HASH.ARRANGEMENT,String.valueOf(id),String.valueOf(arrangement));

    }

    @Override
    public void deleteById(Long id) {

        Map<String,Long> map = new HashMap<>();
        map.put("id",id);

        writeArrangementMapper.deleteByMap(map);

        log.info("删除缓存");
        redisUtils.hDelete(RedisTable.HASH.ARRANGEMENT,String.valueOf(id));
    }

    @Override
    public Arrangement Update(Arrangement arrangement) {


        arrangement.setCreateAt(DataTimeUtil.getNowDateTimeString());
        writeArrangementMapper.update(arrangement);

        log.info("更新缓存");
        redisUtils.hPut(RedisTable.HASH.ARRANGEMENT,String.valueOf(arrangement.getId()),String.valueOf(arrangement));
        return arrangement;
    }


    @Override
    public List<Integer> getSeatSeatsHaveSelected(Long id) {
        //通过场次id获得已经购买或正在购买座位号信息
        Map<String, Long> map = new HashMap<>();
        map.put("aid",id);
        List<Order> orders = writeOrderMapper.selectListByMap(map);
        List<Integer> seats = new ArrayList<>();
        for (Order order : orders) {
            String[] seatsSplit = order.getSeats().split("号");
            for (String seat : seatsSplit) {
                seats.add(Integer.parseInt(seat));

            }

        }
        return seats;
    }


    @Override
    public Arrangement findById(Long id) {

        Map<String,Long> map = new HashMap<>();
        List<Arrangement> arrangements = writeArrangementMapper.selectByMap(map);


        return arrangements.size() == 0? null:arrangements.get(0);
    }
}
