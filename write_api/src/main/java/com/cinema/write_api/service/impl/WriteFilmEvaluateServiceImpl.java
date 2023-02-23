package com.cinema.write_api.service.impl;

import com.cinema.write_api.mapper.WriteFilmEvaluateMapper;
import com.cinema.write_api.mapper.WriteFilmMapper;
import com.cinema.write_api.service.WriteFilmEvaluateService;
import com.cinema.write_api.util.GetSnowId;
import com.cinema.write_api.util.RedisUtils;
import lombok.extern.slf4j.Slf4j;
import model.entity.Film;
import model.entity.FilmEvaluate;
import org.springframework.stereotype.Service;
import type.RedisTable;
import type.SnowIdType;
import util.DataTimeUtil;
import util.PatternUtil;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Slf4j
@Service
public class WriteFilmEvaluateServiceImpl implements WriteFilmEvaluateService {

    @Resource
    private WriteFilmEvaluateMapper writeFilmEvaluateMapper;

    @Resource
    private WriteFilmMapper writeFilmMapper;

    @Resource
    private GetSnowId getSnowId;

    @Resource
    private RedisUtils redisUtils;

    @Override
    public void save(FilmEvaluate filmEvaluate) throws Exception {

        Map<String,Long> map = new HashMap<>();
        map.put("fid", filmEvaluate.getFid());
        map.put("uid",filmEvaluate.getUid());

        List<FilmEvaluate> filmEvaluates = writeFilmEvaluateMapper.selectByMap(map);


        FilmEvaluate one = filmEvaluates.size()==0? null : filmEvaluates.get(0);

        if (one!=null){
            throw  new Exception("感谢您的参与，但是您已评分请不要重复操作");
        }

        getSnowId.setSonwIdType(SnowIdType.FILMEVALUATE);
        long id = getSnowId.nextId();

        filmEvaluate.setId(id);
        filmEvaluate.setCreateAt(DataTimeUtil.getNowDateTimeString());
        writeFilmEvaluateMapper.insert(filmEvaluate);

        log.info("写入缓存");
        redisUtils.hPut(RedisTable.HASH.FILMEVALUATE,String.valueOf(id),String.valueOf(filmEvaluate));

        //为电影添加热度
        Map<String,Long> map1= new HashMap<>();
        map1.put("id",filmEvaluate.getFid());
        List<Film> films = writeFilmMapper.selectByMap(map);
        Film film = films.size()==0? null : films.get(0);
        film.setHot(film.getHot()+1);
        writeFilmMapper.update(film);

        log.info("写入缓存");
        redisUtils.hPut(RedisTable.HASH.FILM,String.valueOf(film.getId()),String.valueOf(film));


    }

    @Override
    public void deleteAllByFilmId(Long fid) {

        Map<String,Long> map = new HashMap<>();
        map.put("fid",fid);

        writeFilmEvaluateMapper.deleteByMap(map);

        log.info("删除缓存");
        //生成key
        String key = RedisTable.SET.FILM_ID_BY_FILMEVALUAT_ID+fid;
        Set<Object> objects = redisUtils.setMembers(key);
        List<Long> FEIds = PatternUtil.getStringNumLong(String.valueOf(objects));
        for (Long FEId : FEIds) {

            redisUtils.hDelete(RedisTable.HASH.FILMEVALUATE,String.valueOf(FEId));
        }
        redisUtils.delete(key);

    }

    @Override
    public void deleteById(Long id) {

        Map<String,Long> map = new HashMap<>();
        map.put("id",id);

        writeFilmEvaluateMapper.deleteByMap(map);

        log.info("删除缓存");
        redisUtils.hDelete(RedisTable.HASH.FILMEVALUATE,String.valueOf(id));

    }
}
