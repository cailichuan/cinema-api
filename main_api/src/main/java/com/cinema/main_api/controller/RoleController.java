package com.cinema.main_api.controller;

import annotation.ApiIdempotence;
import annotation.ErrorVoidApi;
import com.cinema.main_api.service.RoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import model.entity.Role;
import org.springframework.web.bind.annotation.*;
import type.Roles;

import javax.annotation.Resource;
import java.util.List;

/**
 * 对工作人员的权限管理
 * 所有接口都需要管理员权限认证
 */

@RestController
@Api(tags = "权限接口")
@RequestMapping("/api/role")
public class RoleController {

    @Resource
    private RoleService roleService;


    @PostMapping("")
    @ApiOperation("添加权限")
    @ApiIdempotence
    public Role create(@RequestBody Role role,@RequestParam("uuid") String uuid)  throws Exception {
        return roleService.create(role,uuid);
    }

    @DeleteMapping("/{id}")
    @ApiOperation("删除权限")
    @ApiIdempotence
    @ErrorVoidApi
    public void delete(@PathVariable("id") Long id,@RequestParam("uuid") String uuid)  throws Exception {
        roleService.deleteById(id,uuid);
    }

    //============================================================
    @GetMapping("/system")
    @ApiOperation("查看系统设置有哪些权限")
    public String[] listSystemRoles() {
        return Roles.roles;
    }

    @GetMapping("")
    @ApiOperation("查询员工的权限")
    public List<Role> listByWorkerId(@RequestParam(name = "wid") Long wid) throws ClassNotFoundException {
        return roleService.listRolesByWorkerId(wid);
    }
}
