package com.cinema.write_api.service.impl;

import com.cinema.write_api.mapper.WriteCartMapper;
import com.cinema.write_api.service.WriteCartService;
import com.cinema.write_api.service.WriteOrderService;
import com.cinema.write_api.util.GetSnowId;
import com.cinema.write_api.util.RedisUtils;
import lombok.extern.slf4j.Slf4j;
import model.entity.Cart;
import org.springframework.stereotype.Service;
import type.RedisTable;
import type.SnowIdType;
import util.PatternUtil;
import util.RDTCUitl;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;


@Slf4j
@Service
public class WriteCartServiceImpl implements WriteCartService {

    @Resource
    private WriteCartMapper writeCartMapper;


    @Resource
    private WriteOrderService writeOrderService;

    @Resource
    private GetSnowId getSnowId;

    @Resource
    private RedisUtils redisUtils;

    @Override
    public void save(Cart cart) {

        getSnowId.setSonwIdType(SnowIdType.CART);
        long id = getSnowId.nextId();

        cart.setId(id);

        writeCartMapper.insert(cart);

        log.info("写入缓存");
        redisUtils.hPut(RedisTable.HASH.CART,String.valueOf(cart.getId()),String.valueOf(cart));

    }

    @Override
    public void deleteAllByUserId(Long uid) {

        Map<String,Long> map = new HashMap<>();
        map.put("uid",uid);

        writeCartMapper.deleteByMap(map);

        log.info("删除缓存");
        //生成set的key
        String key = RedisTable.SET.USER_ID_BY_CART_ID + uid;

        Set<Object> objects = redisUtils.setMembers(key);
        List<Long> cartIds = PatternUtil.getStringNumLong(String.valueOf(objects));
        for (Long cartId : cartIds) {

            redisUtils.hDelete(RedisTable.HASH.CART,String.valueOf(cartId));
        }

        redisUtils.delete(key);


    }

    @Override
    public void deleteById(Long id) {

        Map<String,Long> map = new HashMap<>();
        map.put("id",id);

        writeCartMapper.deleteByMap(map);

        log.info("删除缓存");
        redisUtils.hDelete(RedisTable.HASH.CART,String.valueOf(id));
    }

    @Override
    public void deleteCarts(List<Cart> carts) {
        for (Cart cart : carts) {
            Map<String,Long> map = new HashMap<>();
            map.put("id",cart.getId());

            writeCartMapper.deleteByMap(map);

            log.info("删除缓存");
            redisUtils.hDelete(RedisTable.HASH.CART,String.valueOf(cart.getId()));
        }
    }

    @Override
    public void settleCarts(List<Cart> carts) throws Exception {

        for (Cart cart : carts) {
            writeOrderService.create(cart);
        }

    }
}
