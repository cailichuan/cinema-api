package api.read;

import model.entity.*;
import model.support.ResponseResult;
import model.vo.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;


/**
 * 读api
 */

//868356096

@FeignClient(value = "READ-API")
public interface ReadApi {

//===============================Activity======================================
    @GetMapping("/read/getdata")
    String getTestData();

    @GetMapping("/readapi/activity")
    public List<Activity>findAllActivity();


    @GetMapping("/readapi/activity/{id}")
    public Activity  findActivityById(@PathVariable(value = "id") Integer id);
    //===================================Arrangement=============================
    //列出电影排片
    @GetMapping("/readapi/arrangement")
    public List<Arrangement> findAllArrangement();

    //查询排片
    @GetMapping("/readapi/arrangement/{id}")
    public Map<String,Object> findArrangementById(@PathVariable(value = "id")Integer id);

    //获取座位情况
    @GetMapping("/readapi/arrangement/{id}/getSeats")
    public List<Integer> getArrangementSeats(@PathVariable(value = "id") Integer id);

    //查询某个电影的所有拍片
    @GetMapping("/readapi/arrangement/film/{fid}")
    public ArrangementVo findArrangemenByFilmId(@PathVariable(value = "fid") Integer fid);


    //=================================Cart=====================================
    @GetMapping("/readapi/cart")
    public List<CartVo> findAllCartVoByUid(@RequestParam(name ="uid") Integer uid);


    //=================================DailyWork===================================
    @GetMapping("/readapi/daily")
    public List<DailyWork> findAllDailyWork();

    //================================Film=======================================
    @GetMapping("/readapi/film")
    public List<Film> findAllFilm(@RequestParam(name = "region" ,required = false) String region, @RequestParam(name = "type",required = false) Integer type);

    @GetMapping("/readapi/film/hot/{limit}")
    public List<Film> findFilmHots(@PathVariable(value = "limit") Integer limit);

    @GetMapping("/readapi/film/name/{name}")
    public List<Film> searchFilmByName(@PathVariable(value = "name") String name);

    @GetMapping("/readapi/film/{id}")
    public Film findFilmById(@PathVariable(value = "id") Integer id);

    //===============================FilmEvaluate================================
    @GetMapping("/readapi/fe")
    public List<FilmEvaluateVo> findFilmEvaluateVoByFid(@RequestParam(name = "fid") Integer fid);

    //===============================LeavingMessage==============================
    @GetMapping("/readapi/lm")
    public List<LeavingMessageVo> findAllLeavingMessageVo();

    @GetMapping("/readapi/lm/active")
    public List<ActiveUserVo> findLeavingMessageActiveUsers();

    //===============================Order=======================================
    @GetMapping("/readapi/order")
    public List<OrderVo> findAllOrderVo();

    @GetMapping("/readapi/order/user/{id}")
    public List<OrderVo> findOrderVoByUserId(@PathVariable(value = "id") Integer id);

    //===============================OrderException=============================

    @GetMapping("/readapi/oe")
    public List<OrderException> findAllOrderException();

    //====================

}
