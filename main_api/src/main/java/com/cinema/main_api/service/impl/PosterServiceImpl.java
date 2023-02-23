package com.cinema.main_api.service.impl;


import api.read.ReadApi;
import api.write.WriteApi;
import com.cinema.main_api.service.PosterService;
import com.cinema.main_api.util.RedisUtils;
import lombok.extern.slf4j.Slf4j;
import model.dto.PosterDto;
import model.entity.Poster;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import type.RedisTable;
import util.PatternUtil;
import util.RDTCUitl;


import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Slf4j
@Service
public class PosterServiceImpl implements PosterService {

    @Resource
    private ReadApi readApi;

    @Resource
    private WriteApi writeApi;

    @Resource
    private RedisUtils redisUtils;

    @Override
    public void save(PosterDto posterDto,MultipartFile file) throws Exception {


        writeApi.savePoste(String.valueOf(posterDto),file);
    }

    @Override
    public void update(PosterDto posterDto,MultipartFile file) throws Exception {

        String posterDtoJson = String.valueOf(posterDto);
        writeApi.updatePoste(posterDtoJson,file);
    }


    @Override
    public List<Poster> findByMap(Boolean status) throws ClassNotFoundException {
        //生成key
        String key = RedisTable.SET.POSTER_BOOLEAN_BY_POSTER_ID+status;

        if (redisUtils.sSize(key)==0) {
            log.info("查询数据库");
            List<Poster> posters = readApi.findPosterByMap(status);

            //写入缓存
            for (Poster poster : posters) {
                redisUtils.sAdd(key,String.valueOf(poster.getId()));
                redisUtils.hPutIfAbsent(RedisTable.HASH.POSTER,String.valueOf(poster.getId()),String.valueOf(poster));
            }

            return posters;
        }

        log.info("查询缓存");
        Set<Object> objects = redisUtils.setMembers(key);
        List<Long> pids = PatternUtil.getStringNumLong(String.valueOf(objects));
        List<Poster> posters = new ArrayList<>();

        for (Long pid : pids) {

            Object PO = redisUtils.hGet(RedisTable.HASH.POSTER, String.valueOf(pid));
            System.out.println(PO);
            Poster poster =null;
            if (PO==null){
                //缓存为空查询数据库
                poster = readApi.findPosterById(Long.valueOf(pid));
                if (poster==null){
                    redisUtils.sRemove(key,pid);
                    continue;
                }
                //写入缓存
                redisUtils.hPut(RedisTable.HASH.POSTER,String.valueOf(pid),String.valueOf(poster));
            }
            else poster = new RDTCUitl<Poster>().getData(PO,RDTCUitl.ClassName.POSTER);

            posters.add(poster);
        }


        return posters;
    }

    @Override
    public void deleteById(Long id,String uuid) throws Exception {
        writeApi.deletePoste(id,uuid);
    }

    @Override
    public void deleteAll(String uuid) throws Exception {

        writeApi.deleteAllPoste(uuid);
    }
}
