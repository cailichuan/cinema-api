package com.cinema.write_api.controller;

import annotation.ApiIdempotence;
import annotation.ErrorVoidApi;
import com.cinema.write_api.service.WriteLeavingMessageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import model.entity.LeavingMessage;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@Api(tags = "留言接口")
@RequestMapping("/writeapi/lm")
public class WriteLeavingMessageController {

    @Resource
    private WriteLeavingMessageService writeLeavingMessageService;

    @PostMapping("")
    @ApiOperation(value = "新增留言接口")
    @ApiIdempotence
    @ErrorVoidApi
    public void save(@RequestBody LeavingMessage leavingMessage,@RequestParam("uuid") String uuid)  throws Exception{

        writeLeavingMessageService.save(leavingMessage);
    }

    @PutMapping("")
    @ApiOperation("回复留言")
    @ApiIdempotence
    @ErrorVoidApi
    public void reply(@RequestBody LeavingMessage leavingMessage,@RequestParam("uuid") String uuid) throws Exception {

        writeLeavingMessageService.reply(leavingMessage);
    }

}

