package com.cinema.read_api.controller;

import com.cinema.read_api.service.ReadLeavingMessageService;
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
@RequestMapping("/readapi/lm")
public class ReadLeavingMessageController {

    @Resource
    private ReadLeavingMessageService readLeavingMessageService;

    @GetMapping("")
    @ApiOperation("获取所有影院留言")
    public List<LeavingMessageVo> list() {
        return readLeavingMessageService.findAll();
    }


    @PostMapping("")
    @ApiOperation("根据用户id获取留言")
    public List<LeavingMessage> selectListByUid(@RequestParam(name = "uid") Long uid){
        return readLeavingMessageService.selectListByUid(uid);
    }

    @PostMapping("/byid")
    @ApiOperation("根据id获取留言")
    public LeavingMessage findById(@RequestParam(name = "id") Long id){
        return readLeavingMessageService.findById(id);
    }

    @GetMapping("/active")
    @ApiOperation("获取活跃留言的用户")
    public List<ActiveUserVo> findActiveUsers() {
        return readLeavingMessageService.findActiveUsers();
    }

}
