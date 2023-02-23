package com.cinema.main_api.service.impl;


import api.read.ReadApi;
import api.write.WriteApi;
import com.cinema.main_api.service.FilmEvaluateService;
import com.cinema.main_api.util.RedisUtils;
import lombok.extern.slf4j.Slf4j;
import model.entity.FilmEvaluate;
import model.entity.User;
import model.vo.FilmEvaluateVo;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import type.RedisTable;
import util.PatternUtil;
import util.RDTCUitl;


import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
@Slf4j
public class FilmEvaluateServiceImpl implements FilmEvaluateService {

    @Resource
    private ReadApi readApi;

    @Resource
    private WriteApi writeApi;


    @Resource
    private RedisUtils redisUtils;


    @Override
    public void save(FilmEvaluate filmEvaluate,String uuid) throws Exception {

        writeApi.saveFilmEvaluate(filmEvaluate,uuid);

    }

    @Override
    public List<FilmEvaluateVo> findAllByFilmId(Long fid) throws ClassNotFoundException {
        //生成key
        String key = RedisTable.SET.FILM_ID_BY_FILMEVALUAT_ID+fid;

        if (redisUtils.sSize(key)==0) {

            log.info("查询数据库");

            List<FilmEvaluateVo> FEVs = readApi.findFilmEvaluateVoByFid(fid);

            System.out.println("FEV=" + FEVs);


            //写入缓存
            for (FilmEvaluateVo fev : FEVs) {
                FilmEvaluate filmEvaluate = fev.getFilmEvaluate();
                User user = fev.getUser();
                System.out.println("filmEvaluate" + filmEvaluate);
                System.out.println("user" + user);
                redisUtils.sAdd(key,String.valueOf(filmEvaluate.getId()));
                redisUtils.hPut(RedisTable.HASH.FILMEVALUATE,String.valueOf(filmEvaluate.getId()),String.valueOf(filmEvaluate));
                if (user!=null)redisUtils.hPut(RedisTable.HASH.USER,String.valueOf(user.getId()),String.valueOf(user));
            }

            return FEVs;

        }


        log.info("查询缓存");

        List<FilmEvaluateVo> FEVs = new ArrayList<>();
        Set<Object> objects = redisUtils.setMembers(key);
        List<Long> FEIds = PatternUtil.getStringNumLong(String.valueOf(objects));
        for (Long feId : FEIds) {

            //FilmEvaluate
            Object feO = redisUtils.hGet(RedisTable.HASH.FILMEVALUATE, String.valueOf(feId));
            FilmEvaluate filmEvaluate = null;
            if (feO ==null) {
                //查询数据库
                filmEvaluate = readApi.findFilmEvaluateById(feId);

                if (filmEvaluate==null){
                    redisUtils.sRemove(key,String.valueOf(feId));
                    continue;
                }

                //写入缓存
                redisUtils.hPut(RedisTable.HASH.FILMEVALUATE,String.valueOf(feId),String.valueOf(filmEvaluate));
            }

            else filmEvaluate = new RDTCUitl<FilmEvaluate>().getData(feO, RDTCUitl.ClassName.FILMEVALUATE);

            //filmEvaluate的fid!=filmId
            if (filmEvaluate.getFid()!=fid){
                redisUtils.sRemove(key,String.valueOf(feId));
                continue;
            }


            //user
            Object UO = redisUtils.hGet(RedisTable.HASH.USER, String.valueOf(filmEvaluate.getUid()));
            User user = null;
            if (UO==null) {

                user = readApi.findUserById(filmEvaluate.getUid());
                if (user!=null){
                    redisUtils.hPut(RedisTable.HASH.USER,String.valueOf(user.getId()),String.valueOf(user));
                }


            }

            else user = new RDTCUitl<User>().getData(UO, RDTCUitl.ClassName.USER);


            FEVs.add(new FilmEvaluateVo(filmEvaluate.getId(),filmEvaluate,user));

        }


        return FEVs;
    }

    @Override
    public void deleteAllByFilmId(Long fid,String uuid) throws Exception {

        writeApi.deleteAllFilmEvaluate(fid,uuid);
    }

    @Override
    public void deleteById(Long id,String uuid) throws Exception {

        writeApi.deleteFilmEvaluate(id,uuid);
    }
}
