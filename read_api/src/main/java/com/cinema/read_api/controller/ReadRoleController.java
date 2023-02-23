package com.cinema.read_api.controller;

import com.cinema.read_api.service.ReadRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import model.entity.Role;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@Api(tags = "权限接口")
@RequestMapping("/readapi/role")
public class ReadRoleController {

    @Resource
    private ReadRoleService readRoleService;

    @GetMapping("")
    @ApiOperation("查询员工的权限")
    public List<Role> listByWorkerId(@RequestParam(name = "wid") Long wid) {
        return readRoleService.listRolesByWorkerId(wid);
    }

    @PostMapping("")
    @ApiOperation("根据id查询权限")
    public Role findById(@RequestParam(name = "id") Long id){

        return readRoleService.findById(id);
    }


}
