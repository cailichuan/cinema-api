package api.write;

import annotation.PostApiIP;
import model.dto.PosterDto;
import model.dto.RegisterDto;
import model.entity.*;
import org.apache.ibatis.annotations.Param;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * 写api
 */


@FeignClient(name = "WRITE-API")
public interface WriteApi {
    //==================Activity==================================
    @PostMapping("/writeapi/activity")
    void createActivity(@RequestBody Activity activity,@RequestParam(name = "uuid") String uuid) throws Exception;

    @DeleteMapping("/writeapi/activity{id}")
    void deleteActivityById(@PathVariable(value = "id") Long id,@RequestParam(name = "uuid") String uuid) throws Exception;


    //===================Arrangement===============================
    @PostMapping("/writeapi/arrangement")
    void createArrangement(@RequestBody Arrangement arrangement,@RequestParam(name = "uuid") String uuid) throws Exception;

    @PutMapping("/writeapi/arrangement")
    Arrangement updateArrangement(@RequestBody Arrangement arrangement,@RequestParam(name = "uuid") String uuid) throws Exception;

    @DeleteMapping("/writeapi/arrangement")
    void deleteArrangementById(@RequestParam(name = "id") Long id,@RequestParam(name = "uuid") String uuid) throws Exception;

    //======================Cart==========================================
    @PostMapping("/writeapi/cart")
    void saveCart(@RequestBody Cart cart,@RequestParam(name = "uuid") String uuid) throws Exception;

    @DeleteMapping("/writeapi/cart")
    void deleteCartById(@RequestParam(name = "id") Long id,@RequestParam(name = "uuid") String uuid) throws Exception;

    //=============================Daily===============================
    @PostMapping("/writeapi/daily")
    void saveDaily(@RequestBody DailyWork dailyWork,@RequestParam(name = "uuid") String uuid) throws Exception;

    @DeleteMapping("/writeapi/daily/{id}")
    void deleteDailyById(@PathVariable("id") Long id,@RequestParam(name = "uuid") String uuid) throws Exception;

    //=============================Film================================
    @PostMapping("/writeapi/film")
    void saveFilm(@RequestBody Film film,@RequestParam(name = "uuid") String uuid) throws Exception;

    @PutMapping("/writeapi/film")
    void  updateFilm(@RequestBody Film film,@RequestParam(name = "uuid") String uuid) throws Exception;

    @DeleteMapping("/writeapi/film/{id}")
    void deleteFilmById(@PathVariable(value = "id") Long id,@RequestParam(name = "uuid") String uuid) throws Exception;

    //==================================FilmEvaluate===========================
    @PostMapping("/writeapi/fe")
    void saveFilmEvaluate(@RequestBody FilmEvaluate filmEvaluate,@RequestParam(name = "uuid") String uuid) throws Exception;

    @DeleteMapping("/writeapi/fe/{id}")
    void deleteFilmEvaluate(@PathVariable(value = "id") Long id,@RequestParam(name = "uuid") String uuid) throws Exception;

    @DeleteMapping("/writeapi/fe")
    void deleteAllFilmEvaluate(@RequestParam(name = "fid") Long fid,@RequestParam(name = "uuid") String uuid) throws Exception;

    //========================================LeavingMessage=====================
    @PostMapping("/writeapi/lm")
    void saveLeavingMessage(@RequestBody LeavingMessage leavingMessage,@RequestParam(name = "uuid") String uuid) throws Exception;

    @PutMapping("/writeapi/lm")
    void replyLeavingMessage(@RequestBody LeavingMessage leavingMessage,@RequestParam(name = "uuid") String uuid) throws Exception;

    //=======================================Order========================
    @PostMapping("/writeapi/order")
    void saveOrder(@RequestBody Cart cart,@RequestParam(name = "uuid") String uuid) throws Exception;

    @PutMapping("/writeapi/order")
    void updateOrder(@RequestBody Order order,@RequestParam(name = "uuid") String uuid) throws Exception;

    @GetMapping("/writeapi/order/pay")
    Order payOrderById(@RequestParam(name = "id") Long id) throws Exception;

    //================================OrderException=======================

    @PostMapping("/writeapi/oe")
    OrderException saveOrderException(@RequestBody OrderException orderException,@RequestParam(name = "uuid") String uuid) throws Exception;

    @PutMapping("/writeapi/oe")
    void handleOrderException(@RequestBody OrderException orderException,@RequestParam(name = "uuid") String uuid) throws Exception;

    //=================================Poster=================================

    @PostMapping(value = "/writeapi/poster",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    void savePoste(@RequestParam("posterDtoJson") String posterDtoJson, @RequestPart("file") MultipartFile file) throws Exception;

    @PutMapping(value = "/writeapi/poster",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    void updatePoste(@RequestParam("posterDtoJson") String posterDtoJson, @RequestPart("file")MultipartFile file) throws Exception;

    @DeleteMapping("/writeapi/poster/{id}")
    void deletePoste(@PathVariable("id") Long id,@RequestParam(name = "uuid") String uuid) throws Exception;

    @DeleteMapping("/writeapi/poster")
    void deleteAllPoste(@RequestParam(name = "uuid") String uuid) throws Exception;

    //=======================================Registration===================

    @PostMapping("/writeapi/registration")
    void saveRegistration(@RequestBody Registration registration,@RequestParam(name = "uuid") String uuid) throws Exception;


    //===================================Role==============================

    @PostMapping("/writeapi/role")
    Role saveRole(@RequestBody Role role,@RequestParam("uuid") String uuid) throws Exception;

    @DeleteMapping("/writeapi/role/{id}")
    void deleteRole(@PathVariable("id") Long id,@RequestParam(name = "uuid") String uuid) throws Exception;



    //============================================Upload===========================
    @PostMapping(value = "/writeapi/upload",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    Long savaUpload(@RequestPart("file")MultipartFile file, @RequestParam(name = "path") String path,@RequestParam(name = "uuid") String uuid) throws Exception;

    @DeleteMapping("/writeapi/upload")
    void deleteUpload(@RequestParam("id") Long id,@RequestParam(name = "uuid") String uuid) throws Exception;
    //================== =========            User==========

    @PostMapping(value = "/writeapi/user/register",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)//
    User saveUser( @RequestParam("registerDtoJson") String registerDtoJson,@RequestPart("Hs") MultipartFile HS) throws Exception;

    //=====================================Worker========================

    @PostMapping("/writeapi/worker")
    Worker saveWorker(@RequestBody Worker worker,@RequestParam(name = "uuid") String uuid) throws Exception;

    @DeleteMapping("/writeapi/worker/{id}")
    void deleteWorkerById(@PathVariable(value = "id") Long id,@RequestParam(name = "uuid") String uuid) throws Exception;

    @PutMapping("/writeapi/worker")
    void updateWorker(@RequestBody Worker worker,@RequestParam(name = "uuid") String uuid) throws Exception;

    //=========================WorkerEvaluate============================

    @PostMapping("/writeapi/we")
    void saveWorkerEvaluate(@RequestBody WorkerEvaluate workerEvaluate,@RequestParam(name = "uuid") String uuid) throws Exception;

    @DeleteMapping("/writeapi/we/{id}")
    void deleteWorkerEvaluateById(@PathVariable(value = "id") Long id,@RequestParam(name = "uuid") String uuid) throws Exception;


}
