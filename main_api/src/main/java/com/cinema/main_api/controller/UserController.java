package com.cinema.main_api.controller;

import annotation.*;
import com.cinema.main_api.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import model.dto.LoginDto;
import model.dto.RegisterDto;
import model.entity.User;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@RestController
@Api(tags = "用户接口")
@RequestMapping("/api/user")
public class UserController {
    @Resource
    private UserService userService;

//    @Resource
//    private BCryptPasswordEncoder bCryptPasswordEncoder;



    @PostMapping("/register")
    @ApiOperation(value = "用户注册")
    @PostApiIdempotence
    public User save( @PostApiIP RegisterDto registerDto, @RequestPart("Hs") MultipartFile HS, @RequestParam("uuid") String uuid)  throws Exception {

            return userService.save(registerDto,HS,uuid);

    }


    //==============================================================
    @PostMapping("/login")
    @ApiOperation("用户登录")
    public Map<String,Object> login(@RequestBody LoginDto dto) throws Exception{

//        User user = userService.login(dto);
//        Map<String,Object> map = new HashMap<>();
//        //是否记住我
//        long exp=dto.isRemember()? JwtTokenUtil.REMEMBER_EXPIRATION:JwtTokenUtil.EXPIRATION_TIME;
//        List<String> roles=new ArrayList<>();
//        roles.add(Roles.ROLE_USER);
//        map.put("token",JwtTokenUtil.createToken(dto.getUsername(),roles,exp));
//        map.put("user",user);
//        return map;
        return null;
    }


    @GetMapping("")
    @ApiOperation("查找所有用户")
    public List<User> findAll() throws ClassNotFoundException {
        return userService.findAll();
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "查找用户")
    public User findById(@PathVariable(value = "id") Long id) throws ClassNotFoundException {
        return userService.findById(id);
    }



}
