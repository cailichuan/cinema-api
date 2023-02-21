package com.cinema.read_api.controller;

import com.cinema.read_api.service.ReadLeavingMessageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import model.vo.ActiveUserVo;
import model.vo.LeavingMessageVo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@Api(tags = "留言接口")
@RequestMapping("/readapi/lm")
public class ReadLeavingMessageController {

    @Resource
    private ReadLeavingMessageService readLeavingMessageService;

    @GetMapping("")
    @ApiOperation("获取所有影院留言")
    public List<LeavingMessageVo> list() {
        return readLeavingMessageService.findAll();
    }

    @GetMapping("/active")
    @ApiOperation("获取活跃留言的用户")
    public List<ActiveUserVo> findActiveUsers() {
        return readLeavingMessageService.findActiveUsers();
    }

}
