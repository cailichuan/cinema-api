package com.cinema.write_api.controller;

import annotation.*;
import com.cinema.write_api.service.WriteUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import model.dto.RegisterDto;
import model.entity.User;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import util.RDTCUitl;

import javax.annotation.Resource;

@RestController
@Api(tags = "用户接口")
@RequestMapping("/writeapi/user")
public class WriteUserController {

    @Resource
    private WriteUserService writeUserService;


    @PostMapping("/register")
    @ApiOperation(value = "用户注册")
    @PostApiIdempotence
    public User save( @PostApiIP @RequestParam("registerDtoJson") String registerDtoJson, @RequestPart("Hs")MultipartFile HS)  throws Exception {


        RegisterDto registerDto = new RDTCUitl<RegisterDto>().getData(registerDtoJson, RDTCUitl.ClassName.REGISTERDTO);


        if (HS.isEmpty()){
            throw new Exception("图片为空");
        }


            return writeUserService.save(registerDto,HS);





    }


}
