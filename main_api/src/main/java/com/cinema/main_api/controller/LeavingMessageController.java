package com.cinema.main_api.controller;

import annotation.ApiIdempotence;
import annotation.ErrorVoidApi;
import com.cinema.main_api.service.LeavingMessageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import model.entity.LeavingMessage;
import model.vo.ActiveUserVo;
import model.vo.LeavingMessageVo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@Api(tags = "留言接口")
@RequestMapping("/api/lm")
public class LeavingMessageController {

    @Resource
    private LeavingMessageService leavingMessageService;



    @PostMapping("")
    @ApiOperation(value = "新增留言接口")
    @ApiIdempotence
    @ErrorVoidApi
    public void save(@RequestBody LeavingMessage leavingMessage, @RequestParam("uuid") String uuid)  throws Exception{

       leavingMessageService.save(leavingMessage,uuid);
    }

    @PutMapping("")
    @ApiOperation("回复留言")
    @ApiIdempotence
    @ErrorVoidApi
    public void reply(@RequestBody LeavingMessage leavingMessage,@RequestParam("uuid") String uuid) throws Exception {

        leavingMessageService.reply(leavingMessage,uuid);
    }


    //=======================================================
    @GetMapping("")
    @ApiOperation("获取所有影院的留言")
    public List<LeavingMessageVo> list() throws ClassNotFoundException {
        return leavingMessageService.findAll();
    }

    @GetMapping("/active")
    @ApiOperation("获取活跃的留言的用户")
    public List<ActiveUserVo> findActiveUsers() throws ClassNotFoundException {
        return leavingMessageService.findActiveUsers();
    }
}
