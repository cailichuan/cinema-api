package com.cinema.write_api.service.impl;

import com.cinema.write_api.mapper.WriteFilmMapper;
import com.cinema.write_api.service.WriteFilmService;
import com.cinema.write_api.util.GetSnowId;
import com.cinema.write_api.util.RedisUtils;
import lombok.extern.slf4j.Slf4j;
import model.entity.Film;
import org.springframework.stereotype.Service;
import type.RedisTable;
import type.SnowIdType;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;


@Slf4j
@Service
public class WriteFilmServiceImpl implements WriteFilmService {

    @Resource
    private WriteFilmMapper writeFilmMapper;

    @Resource
    private GetSnowId getSnowId;

    @Resource
    private RedisUtils redisUtils;

    @Override
    public void save(Film film) {



        getSnowId.setSonwIdType(SnowIdType.FILM);
        long id = getSnowId.nextId();

        film.setId(id);
        film.setHot(0);
        writeFilmMapper.insert(film);

        log.info("写入缓存");
        redisUtils.hPut(RedisTable.HASH.FILM,String.valueOf(id),String.valueOf(film));
    }

    @Override
    public void deleteById(Long id) {

        Map<String,Long> map = new HashMap<>();
        map.put("id",id);

        writeFilmMapper.deleteByMap(map);
        log.info("删除缓存");
        redisUtils.hDelete(RedisTable.HASH.FILM,String.valueOf(id));
    }

    @Override
    public Film update(Film film) {
        writeFilmMapper.update(film);

        log.info("更新缓存");
        redisUtils.hPut(RedisTable.HASH.FILM,String.valueOf(film.getId()),String.valueOf(film));

        return film;
    }
}
