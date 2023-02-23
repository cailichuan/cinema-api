package com.cinema.read_api.controller;


import com.cinema.read_api.service.ReadUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import model.entity.User;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/readapi/user")
@Api(tags = "用户接口")
public class ReadUserController {

    @Resource
    private ReadUserService readUserService;

    @PostMapping("/getuser")
    public User selectUserByUserName(@RequestParam(name = "username") String userName){

        return readUserService.findByUserName(userName);
    }

    @GetMapping("")
    @ApiOperation(value = "查找所有用户")
    public List<User> findAll(){
        return readUserService.findAll();
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "查找用户")
    public User findById(@PathVariable(value = "id") Long id){
        return readUserService.findById(id);
    }





}
