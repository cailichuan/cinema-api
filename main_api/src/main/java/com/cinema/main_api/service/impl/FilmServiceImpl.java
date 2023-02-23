package com.cinema.main_api.service.impl;


import api.read.ReadApi;
import api.write.WriteApi;
import com.cinema.main_api.service.FilmService;
import com.cinema.main_api.util.RedisUtils;
import lombok.extern.slf4j.Slf4j;
import model.entity.Film;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import type.RedisTable;
import util.PatternUtil;
import util.RDTCUitl;


import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;


@Slf4j
@Service
public class FilmServiceImpl implements FilmService {


    @Resource
    private ReadApi readApi;

    @Resource
    private WriteApi writeApi;


    @Resource
    private RedisUtils redisUtils;

    @Override
    public void save(Film film,String uuid) throws Exception {

        writeApi.saveFilm(film,uuid);
    }

    @Override
    public void deleteById(Long id,String uuid) throws Exception {

        writeApi.deleteFilmById(id,uuid);
    }

    @Override
    public List<Film> findAll() {
        if (redisUtils.hSize(RedisTable.HASH.FILM)==0) {
            log.info("查询数据库");
            List<Film> allFilm = readApi.findAllFilm(null, null);

            log.info("写入缓存");
            for (Film film : allFilm) {
                redisUtils.hPut(RedisTable.HASH.FILM,String.valueOf(film.getId()),String.valueOf(film));
            }

            return allFilm;
        }

        log.info("查询缓存");
        List<Film> films = new ArrayList<>();
        List<Object> objects = redisUtils.hValues(RedisTable.HASH.FILM);
        for (Object object : objects) {
            films.add((Film) object);
        }


        return films;
    }

    @Override
    public List<Film> findByRegionAndType(String region, String type) throws ClassNotFoundException {
        //生成key
        String rKey = RedisTable.SET.FILM_REGIN+region;
        String tKey = RedisTable.SET.FILM_TYPE+type;

        //整两个集合，求交集
        if (redisUtils.sSize(rKey) == 0 || redisUtils.sSize(tKey) == 0){

            log.info("查询数据库");
            List<Film> allFilm = readApi.findAllFilm(region, type);

            log.info("写入缓存");
            for (Film film : allFilm) {
                String fid = String.valueOf(film.getId());
                redisUtils.sAdd(rKey,fid);
                redisUtils.sAdd(tKey,fid);
                redisUtils.hPutIfAbsent(RedisTable.HASH.FILM,fid,String.valueOf(film));
            }

            return allFilm;
        }

        log.info("读取缓存");
        //获取两集合的交集
        Set<Object> objects = redisUtils.sIntersect(rKey, tKey);
        if (objects==null)return null;
        List<Long> fids = PatternUtil.getStringNumLong(String.valueOf(objects));
        List<Film> films = new ArrayList<>();


        for (Long fid : fids) {

            Film film = null;
            Object FO = redisUtils.hGet(RedisTable.HASH.FILM, String.valueOf(film));
            if (FO==null){
                film=readApi.findFilmById(fid);
                if (film==null) {
                    redisUtils.sRemove(rKey,String.valueOf(fid));
                    redisUtils.sRemove(tKey,String.valueOf(fid));
                    continue;
                }
                redisUtils.hPut(RedisTable.HASH.FILM,String.valueOf(fid),String.valueOf(film));
            }

            else film=new RDTCUitl<Film>().getData(FO,RDTCUitl.ClassName.FILM);

            films.add(film);
        }

        return films;
    }



    @Override
    public List<Film> findHots(Integer limit) throws ClassNotFoundException {
        if (redisUtils.zSize(RedisTable.Z_SET.FilM_HOT)==0){

            log.info("查询数据库");

            List<Film> films = readApi.findFilmHots(limit);

            log.info("写入数据");
            for (Film film : films) {
                redisUtils.zAdd(RedisTable.Z_SET.FilM_HOT,String.valueOf(film.getId()),(double) film.getHot());
                redisUtils.hPutIfAbsent(RedisTable.HASH.FILM,String.valueOf(film.getId()),String.valueOf(film));
            }
            return films;
        }


        log.info("查询缓存");
        List<Film> filmList = new ArrayList<>();
        Set<Object> objects = redisUtils.zReverseRange(RedisTable.Z_SET.FilM_HOT, 0, limit);
        List<Long> fids = PatternUtil.getStringNumLong(String.valueOf(objects));

        for (Long fid : fids) {


            Film film=null;
            Object FO = redisUtils.hGet(RedisTable.HASH.FILM, String.valueOf(fid));
            if (FO==null){
                film = readApi.findFilmById(fid);
                if (film==null){
                    redisUtils.zRemove(RedisTable.Z_SET.FilM_HOT,String.valueOf(fid));
                    continue;
                }
                redisUtils.hPut(RedisTable.HASH.FILM,String.valueOf(fid),String.valueOf(film));
            }

            else film = new RDTCUitl<Film>().getData(FO,RDTCUitl.ClassName.FILM);

            filmList.add(film);
        }


        return filmList;
    }


    /**
     * 以下用到两个redis表
     * 1。likename的热度hash: likename -- hot
     * 2.likename的数据缓存list: likename -- filmId(设置过期时间)
     * @param name
     * @return
     */
    @Override
    public List<Film> findLikeName(String name) throws ClassNotFoundException {
        //生成key
        String key = RedisTable.SET.FILM_LIKE_NAME+"_"+name;
        String hotKeyZ_set=RedisTable.Z_SET.LIKE_NAME_HOT;

        //维护一个热度表，每个健存在过期时间，和热度，每次搜索就查热度表，热度高就查缓存，热度低就查数据库

        Double hotNum = redisUtils.zScore(hotKeyZ_set, key);
        if (hotNum==null || hotNum<1){

            log.info("查询数据库");
            List<Film> films = readApi.searchFilmByName(name);


            log.info("写入缓存");
            for (Film film : films) {

                redisUtils.sAdd(key,String.valueOf(film.getId()));
                redisUtils.hPutIfAbsent(RedisTable.HASH.FILM,String.valueOf(film.getId()),String.valueOf(film));
            }
            //向热度表注册数
            redisUtils.zAdd(hotKeyZ_set,key,1d);

            //设置过期时间
            redisUtils.expire(key,2, TimeUnit.MINUTES);

            return films;
        }


        log.info("查询缓存");
        Double hot = redisUtils.zScore(hotKeyZ_set, key);


        System.out.println("该key的热度="+hot);
        List<Film> films= null;
        //超过100热度就查询缓存
        if (hot>=100){

            Set<Object> objects = redisUtils.setMembers(key);


            //为空查询数据库
            if (objects==null){
                films = readApi.searchFilmByName(name);

                if (films==null) return null;

                //重新设置热度
                redisUtils.zAdd(hotKeyZ_set,name,1d);

                //将数据重新写入缓存,并设置过期时间
                for (Film film : films) {

                    redisUtils.sAdd(key,String.valueOf(film.getId()));


                    //缓存中无则加入film
                    redisUtils.hPutIfAbsent(RedisTable.HASH.FILM,String.valueOf(film.getId()),String.valueOf(film));

                }

                //把搜索结果设置过期时间
                redisUtils.expire(key,2, TimeUnit.MINUTES);
            }

            else {

                //不为空的情况
                List<Long> fids = PatternUtil.getStringNumLong(String.valueOf(objects));
                for (Long filmId : fids) {


                    Film film=null;
                    Object FO = redisUtils.hGet(RedisTable.HASH.FILM, String.valueOf(filmId));
                    if (FO==null){
                        film=readApi.findFilmById( filmId);


                        if (film==null) {
                            redisUtils.sRemove(key,String.valueOf(filmId));
                           continue;
                        }

                        redisUtils.hPut(RedisTable.HASH.FILM,String.valueOf(filmId),String.valueOf(film));


                    }

                    else film= new RDTCUitl<Film>().getData(FO,RDTCUitl.ClassName.FILM);
                    //热度+1
                    redisUtils.zIncrementScore(hotKeyZ_set,key,1d);

                    films.add(film);
                }
            }



        }

        //不超过100则查询数据库
        else {

            //热度+1

            redisUtils.zIncrementScore(hotKeyZ_set,key,1d);
            films=readApi.searchFilmByName(name);

        }
        return films;
    }

    @Override
    public Film findById(Long id) throws ClassNotFoundException {

        if (!redisUtils.hExists(RedisTable.HASH.FILM,String.valueOf(id))) {
            log.info("搜索数据库");
            Film film = readApi.findFilmById(id);
            if (film!=null) {
                redisUtils.hPut(RedisTable.HASH.FILM,String.valueOf(film.getId()),String.valueOf(film));
            }

            return film;
        }

        log.info("查询缓存");
        Object FO = redisUtils.hGet(RedisTable.HASH.FILM, String.valueOf(id));
        Film film = new RDTCUitl<Film>().getData(FO, RDTCUitl.ClassName.FILM);
        return film;
    }

    @Override
    public Film update(Film film,String uuid) throws Exception {
        writeApi.updateFilm(film,uuid);

        return film;
    }
}
