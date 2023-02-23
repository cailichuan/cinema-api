package com.cinema.read_api.service.impl;

import com.cinema.read_api.mapper.ReadArrangementMapper;
import com.cinema.read_api.mapper.ReadFilmMapper;
import com.cinema.read_api.mapper.ReadOrderMapper;
import com.cinema.read_api.mapper.ReadUserMapper;
import com.cinema.read_api.service.ReadArrangementService;
import com.cinema.read_api.service.ReadOrderService;
import io.swagger.models.auth.In;
import model.entity.Arrangement;
import model.entity.Order;
import model.vo.OrderVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ReadOrderServiceImpl  implements ReadOrderService {

    @Resource
    private ReadOrderMapper readOrderMapper;

    @Resource
    private ReadUserMapper readUserMapper;

    @Resource
    private ReadArrangementMapper readArrangementMapper;

    @Resource
    private ReadFilmMapper readFilmMapper;

    @Override
    public List<OrderVo> findOrderVoList(Long uid) {
        Map<String,Long> map =new HashMap<>();

        if (uid!=null){
            map.put("uid",uid);
        }
        List<Order> orders = readOrderMapper.selectListByMap(map);
        List<OrderVo> result = new ArrayList<>();
        for (Order order : orders) {
            OrderVo orderVo = new OrderVo();
            orderVo.setOrder(order);
            orderVo.setUser(readUserMapper.selectById(order.getUid()));
            Arrangement arrangement = readArrangementMapper.selectById(order.getAid());
            orderVo.setArrangement(arrangement);
            orderVo.setFilm(readFilmMapper.selectById(arrangement.getFid()));
            result.add(orderVo);
        }
        return result;
    }


    @Override
    public Order findOrderById(Long id) {

        Map<String,Long> map = new HashMap<>();
        map.put("id",id);

        List<Order> orders = readOrderMapper.selectListByMap(map);
        Order order = null;

       return order= orders==null? null: orders.get(0);

    }
}
