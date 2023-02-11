package com.cinema.read_api.service.impl;

import com.cinema.read_api.mapper.ReadArrangementMapper;
import com.cinema.read_api.mapper.ReadFilmMapper;
import com.cinema.read_api.mapper.ReadOrderMapper;
import com.cinema.read_api.service.ReadArrangementService;

import model.entity.Arrangement;
import model.entity.Order;
import model.vo.ArrangementVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class ReadArrangementServiceImpl implements ReadArrangementService {

    @Resource
    private ReadArrangementMapper readArrangementMapper;

    @Resource
    private ReadFilmMapper readFilmMapper;

    @Resource
    private ReadOrderMapper readOrderMapper;

    @Override
    public List<Arrangement> findAll() {
        return readArrangementMapper.selectList();
    }

    @Override
    public ArrangementVo findByFilmId(Integer fid) {
        List<Arrangement> arrangements = readArrangementMapper.selectListByFid(fid);

        return new ArrangementVo(arrangements,readFilmMapper.selectById(fid));
    }

    @Override
    public List<Integer> getSeatSeatsHaveSelected(Integer id) {
        //通过场次id获得已经购买或正在购买座位号信息
        List<Order> orders = readOrderMapper.selectListByAid(id);
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
    public Arrangement findById(Integer id) {
        return readArrangementMapper.selectById(id);
    }
}
