package api.read;

import annotation.FeginDecoderBaseResponse;
import model.entity.*;

import model.vo.*;

import org.springframework.cloud.openfeign.FeignClient;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


import java.io.IOException;
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
    List<Activity>findAllActivity();


    @GetMapping("/readapi/activity/{id}")
    Activity  findActivityById(@PathVariable(value = "id") Long id);
    //===================================Arrangement=============================
    //列出电影排片
    @GetMapping("/readapi/arrangement")
    List<Arrangement> findAllArrangement();

    //查询排片
    @GetMapping("/readapi/arrangement/{id}")
    OneArrangementVo findArrangementById(@PathVariable(value = "id")Long id);

    //获取座位情况
    @GetMapping("/readapi/arrangement/{id}/getSeats")
    List<Integer> getArrangementSeats(@PathVariable(value = "id") Long id);

    //查询某个电影的所有拍片
    @GetMapping("/readapi/arrangement/film/{fid}")
    ArrangementVo findArrangemenByFilmId(@PathVariable(value = "fid") Long fid);


    //=================================Cart=====================================
    @GetMapping("/readapi/cart")
    List<CartVo> findAllCartVoByUid(@RequestParam(name ="uid") Long uid);

    @PostMapping("")
    Cart findCartById(@RequestParam(name = "id") Long id);

    //=================================DailyWork===================================
    @GetMapping("/readapi/daily")
    List<DailyWork> findAllDailyWork();

    //================================Film=======================================
    @GetMapping("/readapi/film")
    List<Film> findAllFilm(@RequestParam(name = "region" ,required = false) String region, @RequestParam(name = "type",required = false) String type);

    @GetMapping("/readapi/film/hot/{limit}")
    List<Film> findFilmHots(@PathVariable(value = "limit") Integer limit);

    @GetMapping("/readapi/film/name/{name}")
    List<Film> searchFilmByName(@PathVariable(value = "name") String name);

    @GetMapping("/readapi/film/{id}")
    Film findFilmById(@PathVariable(value = "id") Long id);

    //===============================FilmEvaluate================================
    @GetMapping("/readapi/fe")
    List<FilmEvaluateVo> findFilmEvaluateVoByFid(@RequestParam(name = "fid") Long fid);

    @PostMapping("/readapi/fe")
    FilmEvaluate findFilmEvaluateById(@RequestParam(name = "id") Long id);

    //===============================LeavingMessage==============================
    @GetMapping("/readapi/lm")
    List<LeavingMessageVo> findAllLeavingMessageVo();

    @GetMapping("/readapi/lm/active")
    List<ActiveUserVo> findLeavingMessageActiveUsers();

    @PostMapping("/readapi/lm//byid")
    LeavingMessage findLeavingMessageById(@RequestParam(name = "id") Long id);

    @PostMapping("/readapi/lm")
    public List<LeavingMessage> findLeavingMessagesByUid(@RequestParam(name = "uid") Long uid);
    //===============================Order=======================================
    @GetMapping("/readapi/order")
    List<OrderVo> findAllOrderVo();

    @GetMapping("/readapi/order/user/{id}")
    List<OrderVo> findOrderVoByUserId(@PathVariable(value = "id") Long id);

    @PostMapping("/readapi/order")
    Order findOrderById(@RequestParam(name = "id") Long id);

    //===============================OrderException=============================

    @GetMapping("/readapi/oe")
    List<OrderException> findAllOrderException();

    //=============================Poster========================================

    @GetMapping("/readapi/poster")
    List<Poster> findPosterByMap(@RequestParam(name = "status",required = false) Boolean status);

    @PostMapping("/readapi/poster")
    Poster findPosterById(@RequestParam(name = "id") Long id);

    //=============================ReadRegistration==============================

    @GetMapping("readapi/registration")
    List<Registration> findAllReadRegistration();

    //=============================Roles=====================================

    @GetMapping("/readapi/role")
    List<Role> findRoleByWid(@RequestParam(name = "wid") Long wid);

    @PostMapping("/readapi/role")
    Role findRoleById(@RequestParam(name = "id") Long id);

    //============================Upload=================================
    @GetMapping(value = "/readapi/upload",produces = MediaType.IMAGE_JPEG_VALUE)
    @FeginDecoderBaseResponse
    byte[] findUploadFileById(@RequestParam(name = "id") Long id)throws IOException;


    //==========================User===========================================

    @PostMapping("/readapi/user/getuser")
    User findUserByUserName(@RequestParam(name = "username") String userName);

    @GetMapping("/readapi/user")
    List<User> findAllUser();

    @GetMapping("/readapi/user/{id}")
    User findUserById(@PathVariable(value = "id") Long id);

    //=======================WorkerEvaluate================================

    @GetMapping("/readapi/we")
    List<WorkerEvaluate> findWorkerEvaluateByWid(@RequestParam(name = "wid") Long wid);

    @PostMapping("/readapi/we")
    WorkerEvaluate findWorkerEvaluateById(@RequestParam(name = "id") Long id);

    //=======================Worker==============================================
    @PostMapping("/readapi/worker")
    Worker findWorkerById(@RequestParam(name = "id") Long id);

    @GetMapping("/readapi/worker")
    List<Worker> findAllWorker();
    //==============

}
