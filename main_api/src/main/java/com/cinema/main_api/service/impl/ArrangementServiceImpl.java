package com.cinema.main_api.service.impl;


import api.read.ReadApi;
import api.write.WriteApi;
import com.cinema.main_api.service.ArrangementService;
import com.cinema.main_api.service.UuidService;
import com.cinema.main_api.util.RedisUtils;
import lombok.extern.slf4j.Slf4j;
import model.entity.Arrangement;
import model.entity.Film;
import model.vo.ArrangementVo;
import model.vo.OneArrangementVo;
import org.springframework.stereotype.Service;
import type.RedisTable;
import util.PatternUtil;
import util.RDTCUitl;


import javax.annotation.Resource;
import java.util.*;

@Slf4j
@Service
public class ArrangementServiceImpl implements ArrangementService {

    @Resource
    private WriteApi writeApi;

    @Resource
    private ReadApi readApi;

    @Resource
    private RedisUtils redisUtils;





    @Override
    public void save(Arrangement arrangement,String uuid) throws Exception {


        log.info("存入缓存");
        redisUtils.hPut(RedisTable.HASH.ARRANGEMENT,String.valueOf(arrangement.getId()),String.valueOf(arrangement));
        writeApi.createArrangement(arrangement,uuid);


    }

    @Override
    public List<Arrangement> findAll() throws ClassNotFoundException {



        if (redisUtils.hSize(RedisTable.HASH.ARRANGEMENT) == 0 ) {
            log.info("查询数据库");
            List<Arrangement> arrangements=readApi.findAllArrangement();


            if (arrangements!=null){
                log.info("将数据存入缓存");
                for (Arrangement arrangement : arrangements) {

                    redisUtils.hPutIfAbsent(RedisTable.HASH.ARRANGEMENT,String.valueOf(arrangement.getId()),String.valueOf(arrangement));
                }
            }

            return arrangements;
        }



        log.info("查询缓存");
        List<Arrangement> arrangementList=new ArrayList<>();
        List<Object> objects = redisUtils.hValues(RedisTable.HASH.ARRANGEMENT);
        if (objects!=null){
            for (Object object : objects) {
                Arrangement data = new RDTCUitl<Arrangement>().getData(object, RDTCUitl.ClassName.ARRANGEMENT);
                arrangementList.add(data);
            }
        }

        return arrangementList;



    }

    @Override
    public ArrangementVo findByFilmId(Long fid) throws ClassNotFoundException {
        //生成key=fid,value=list<aid>缓存里的key
        String key = RedisTable.SET.FILM_ID_BY_ARRANGEMENT_ID+fid;

        //如果缓存中不存在数据，查询数据
        if (redisUtils.sSize(key)==0){
            log.info("查询数据库");
            ArrangementVo arrangementVo = readApi.findArrangemenByFilmId(fid);
            if (arrangementVo!=null){

                List<Arrangement> arrangements = arrangementVo.getArrangements();


                for (Arrangement arrangement : arrangements) {



                    //存入key=fid,value=list<aid>
                    redisUtils.sAdd(key,String.valueOf(arrangement.getId()));

                    //存入该电影所有场次的数据作为缓存
                    redisUtils.hPut(
                            RedisTable.HASH.ARRANGEMENT,
                            String.valueOf(arrangement.getId()),
                            String.valueOf(arrangement)
                            );
                }




                //存入film
                Film film = arrangementVo.getFilm();
                if (film!=null)
                    redisUtils.hPutIfAbsent(
                            RedisTable.HASH.FILM,
                            String.valueOf(fid),
                            String.valueOf(film)
                );

            }
            
            return arrangementVo;
        }



        log.info("缓存中查询数据");

        //根据filmId获取film
        Object filmo = redisUtils.hGet(RedisTable.HASH.FILM, String.valueOf(fid));
        Film film = null;

        if (filmo==null){
            film = readApi.findFilmById(fid);
            if (film==null){
                redisUtils.delete(key);
                return null;
            }
        }

        else film = new RDTCUitl<Film>().getData(filmo, RDTCUitl.ClassName.FILM);

        //获取filmid对于的aid的list
        Set<Object> objects = redisUtils.setMembers(key);
        List<Arrangement> arrangementList=new ArrayList<>();

        //根据aid查询Arrangement
        for (Object object : objects) {

            //获取arrangement
            String aid = (String) object;
            Object o = redisUtils.hGet(RedisTable.HASH.ARRANGEMENT, aid);
            Arrangement arrangement = null;
            if (o==null){
                arrangement = readApi.findArrangementById(Long.valueOf(aid)).getArrangement();
                if(arrangement!=null) redisUtils.hPut(RedisTable.HASH.ARRANGEMENT,aid,String.valueOf(arrangement));
            }
            else arrangement = new RDTCUitl<Arrangement>().getData(o, RDTCUitl.ClassName.ARRANGEMENT);

            //arrangement的fid!=fimid
            if (arrangement.getFid()!=fid){
                redisUtils.sRemove(key,aid);
                continue;
            }


            arrangementList.add(arrangement);
        }

            //封装ArrangementVo
            return new ArrangementVo(arrangementList,film);

    }

    @Override
    public List<Integer> getSeatsHaveSelected(Long id) {
        //生成key
        String key=RedisTable.SET.SEATS_HAVE_SELECTED+String.valueOf(id);

        if (redisUtils.sSize(key)==0) {
            log.info("查询数据库");
            List<Integer> arrangementSeats = readApi.getArrangementSeats(id);

            System.out.println("list=" + arrangementSeats);
            for (Integer arrangementSeat : arrangementSeats) {
                System.out.println("座位号" + arrangementSeat);
            }
            String[] strings = new String[arrangementSeats.size()];


            for (int i=0;i<arrangementSeats.size();i++){
                strings[i]=String.valueOf(arrangementSeats.get(i));
            }

            log.info("写入缓存");
            redisUtils.sAdd(key,strings);


            return arrangementSeats;
        }



        log.info("查询缓存");
        Set<Object> objects = redisUtils.setMembers(key);

        List<Integer> seats = PatternUtil.getStringNum(String.valueOf(objects));


        return seats;

    }

    @Override
    public OneArrangementVo findById(Long id) throws ClassNotFoundException {
        if (!redisUtils.hExists(RedisTable.HASH.ARRANGEMENT,String.valueOf(id))){

            log.info("查询数据库");


            OneArrangementVo OAV = readApi.findArrangementById(id);

            if (OAV!=null){

                Film film= OAV.getFilm();
                Arrangement arrangement = OAV.getArrangement();

                log.info("写入缓存");
                redisUtils.hPut(RedisTable.HASH.ARRANGEMENT,String.valueOf(id),String.valueOf(arrangement));

                //存入film,不存在时才存入
                redisUtils.hPutIfAbsent(RedisTable.HASH.FILM,String.valueOf(film.getId()),String.valueOf(film));

            }

            return OAV;

        }

        log.info("查询缓存");


        Object arrangementO =  redisUtils.hGet(RedisTable.HASH.ARRANGEMENT, String.valueOf(id));
        Arrangement arrangement = new RDTCUitl<Arrangement>().getData(arrangementO, RDTCUitl.ClassName.ARRANGEMENT);

        Long fid = arrangement.getFid();
        Object FO = redisUtils.hGet(RedisTable.HASH.FILM, String.valueOf(fid));
        Film film = null;
        if (FO==null){
            film = readApi.findFilmById(fid);
            if (film!=null) redisUtils.hPut(RedisTable.HASH.FILM,String.valueOf(fid),String.valueOf(film));
        }

        else film=new RDTCUitl<Film>().getData(FO,RDTCUitl.ClassName.FILM);


        return new OneArrangementVo(film,arrangement);
    }

    @Override
    public void deleteById(Long id,String uuid) throws Exception {

        writeApi.deleteArrangementById(id,uuid);
    }

    @Override
    public Arrangement Update(Arrangement arrangement ,String uuid) throws Exception {

        return writeApi.updateArrangement(arrangement,uuid);
    }
}
