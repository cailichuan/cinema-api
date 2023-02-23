package com.cinema.write_api.controller;

import annotation.ApiIdempotence;
import annotation.ErrorVoidApi;
import com.cinema.write_api.service.WriteRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import model.entity.Role;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@Api(tags = "权限接口")
@RequestMapping("/writeapi/role")
public class WriteRoleController {

    @Resource
    private WriteRoleService writeRoleService;


    @PostMapping("")
    @ApiOperation("添加权限")
    @ApiIdempotence
    public Role create(@RequestBody Role role,@RequestParam("uuid") String uuid)  throws Exception {
        return writeRoleService.create(role);
    }

    @DeleteMapping("/{id}")
    @ApiOperation("删除权限")
    @ApiIdempotence
    @ErrorVoidApi
    public void delete(@PathVariable("id") Long id,@RequestParam("uuid") String uuid)  throws Exception {
        writeRoleService.deleteById(id);
    }


}
