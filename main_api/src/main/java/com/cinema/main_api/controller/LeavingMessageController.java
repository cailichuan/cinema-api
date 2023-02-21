package com.cinema.main_api.controller;

import com.cinema.main_api.service.LeavingMessageService;
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
@RequestMapping("/api/lm")
public class LeavingMessageController {

    @Resource
    private LeavingMessageService leavingMessageService;

    @GetMapping("")
    @ApiOperation("获取所有影院的留言")
    public List<LeavingMessageVo> list(){
        return leavingMessageService.findAll();
    }

    @GetMapping("/active")
    @ApiOperation("获取活跃的留言的用户")
    public List<ActiveUserVo> findActiveUsers(){
        return leavingMessageService.findActiveUsers();
    }
}
