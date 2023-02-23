package com.cinema.write_api.service.impl;

import com.cinema.write_api.mapper.WriteFilmMapper;
import com.cinema.write_api.mapper.WriteOrderMapper;
import com.cinema.write_api.service.WriteArrangementService;
import com.cinema.write_api.service.WriteOrderService;
import com.cinema.write_api.util.GetSnowId;
import com.cinema.write_api.util.RedisUtils;
import lombok.extern.slf4j.Slf4j;
import model.entity.Cart;
import model.entity.Film;
import model.entity.Order;
import org.springframework.stereotype.Service;
import type.OrderStatus;
import type.RedisTable;
import type.SnowIdType;
import util.DataTimeUtil;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class WriteOrderServiceImpl implements WriteOrderService {

    @Resource
    private WriteOrderMapper writeOrderMapper;

    @Resource
    private WriteFilmMapper writeFilmMapper;

    @Resource
    private WriteArrangementService writeArrangementService;

    @Resource
    private GetSnowId getSnowId;

    @Resource
    private RedisUtils redisUtils;

    @Override
    public Order create(Cart cart) throws Exception {
        List<Integer> seats = writeArrangementService.getSeatSeatsHaveSelected(cart.getAid());
        String[] split = cart.getSeats().split("号");
        for (String s : split) {
            if (seats.contains(Integer.parseInt(s))) {
                throw new Exception("影片在购物车中躺了太长时间了，座位已被其他用户预订并支付了");
            }
        }



        getSnowId.setSonwIdType(SnowIdType.ORDER);
        long id = getSnowId.nextId();

        Order order = new Order();

        //生成订单id
        order.setId(id);

        //写入用户id
        order.setUid(cart.getUid());

        //写入用户电话
        order.setPhone(cart.getPhone());

        //写入场次id
        order.setAid(cart.getAid());

        //写入座位信息
        order.setStatus(cart.getStatus());
        order.setSeats(cart.getSeats());
        if(cart.getStatus() ==2) order.setPayAt(DataTimeUtil.getNowDateTimeString());
        order.setPrice(cart.getPrice());
        order.setCreateAt(DataTimeUtil.getNowDateTimeString());
        writeOrderMapper.insert(order);

        log.info("写入缓存");
        redisUtils.hPut(RedisTable.HASH.ORDER,String.valueOf(order.getId()),String.valueOf(order));

        //订几个座位就添加多少热度
        Map<String,Long> map = new HashMap<>();
        map.put("id",writeArrangementService.findById(cart.getAid()).getFid());
        List<Film> films = writeFilmMapper.selectByMap(map);
        Film film = films.size()==0? null:films.get(0);
        film.setHot(film.getHot()+split.length);
        writeFilmMapper.update(film);

        log.info("写入缓存");
        redisUtils.hPut(RedisTable.HASH.FILM,String.valueOf(film.getId()),String.valueOf(film));

        return order;
    }

    @Override
    public Order pay(Long id) throws Exception {
        Map<String,Long> map = new HashMap<>();
        map.put("id",id);

        List<Order> orders = writeOrderMapper.selectListByMap(map);
        if (orders.size() == 0){

            //删除接口幂等性数据
            redisUtils.hDelete(RedisTable.HASH.PAY_ORDER,String.valueOf(id));

            log.info("不存在的订单id");
            throw new Exception("不存在的订单id");
        }

        Order order = orders.get(0);

        if (order.getStatus() == OrderStatus.PAYMENT_SUCCESSFUL){

            //删除接口幂等性数据
            redisUtils.hDelete(RedisTable.HASH.PAY_ORDER,String.valueOf(id));

            throw new Exception("订单已支付，请勿重复支付");
        }


        if (DataTimeUtil.parseDateTimeStamp(order.getCreateAt()+ OrderStatus.EXPIRATION_TIME) < System.currentTimeMillis()){
            order.setStatus(OrderStatus.PAYMENT_FAILED);

            writeOrderMapper.update(order);

            log.info("订单超时");
            log.info("写入缓存");
            redisUtils.hPut(RedisTable.HASH.ORDER,String.valueOf(order.getId()),String.valueOf(order));

            //删除接口幂等性数据
            redisUtils.hDelete(RedisTable.HASH.PAY_ORDER,String.valueOf(id));

            throw new Exception("订单支付失败");

        }

        order.setStatus(OrderStatus.PAYMENT_SUCCESSFUL);
        order.setPayAt(DataTimeUtil.getNowDateTimeString());
        writeOrderMapper.update(order);

        log.info("支付成功");
        log.info("写入缓存");
        redisUtils.hPut(RedisTable.HASH.ORDER,String.valueOf(order.getId()),String.valueOf(order));

        //删除接口幂等性数据
        redisUtils.hDelete(RedisTable.HASH.PAY_ORDER,String.valueOf(id));

        return order;
    }

    @Override
    public void update(Order order) {

        writeOrderMapper.update(order);

        log.info("写入缓存");
        redisUtils.hPut(RedisTable.HASH.ORDER,String.valueOf(order.getId()),String.valueOf(order));
    }
}
