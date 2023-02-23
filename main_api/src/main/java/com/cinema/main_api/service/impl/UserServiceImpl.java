package com.cinema.main_api.service.impl;


import api.read.ReadApi;
import api.write.WriteApi;
import com.cinema.main_api.service.UserService;
import com.cinema.main_api.util.RedisUtils;
import lombok.extern.slf4j.Slf4j;
import mapper.UserMapper;
import model.dto.LoginDto;
import model.dto.RegisterDto;
import model.entity.User;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import type.RedisTable;
import util.RDTCUitl;


import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;


@Slf4j
@Service
public class UserServiceImpl implements UserService {


    @Resource
    private ReadApi readApi;

    @Resource
    private WriteApi writeApi;

    @Resource
    private RedisUtils redisUtils;

    @Resource
    private BCryptPasswordEncoder bCryptPasswordEncoder;



    @Override
    public User login(LoginDto dto) throws Exception {

        User user = readApi.findUserByUserName(dto.getUsername());
        System.out.println("密码" + user.getPassword());
        if (user==null){
            throw new Exception("用户名或者密码错误，user=null");
        }



        if (!bCryptPasswordEncoder.matches(dto.getPassword(),user.getPassword())){

            throw new Exception("用户名或者密码错误,验证失败");
        }

        return user;
    }

    @Override
    public List<User> findAll() throws ClassNotFoundException {

        if (redisUtils.hSize(RedisTable.HASH.USER)==0) {
            log.info("查询数据库");
            List<User> users = readApi.findAllUser();

            //写入缓存
            for (User user : users) {
                redisUtils.hPut(RedisTable.HASH.USER,String.valueOf(user.getId()),String.valueOf(user));
            }

            return users;
        }

        log.info("查询缓存");
        List<User> users = new ArrayList<>();
        List<Object> objects = redisUtils.hValues(RedisTable.HASH.USER);

        for (Object object : objects) {

            User user = new RDTCUitl<User>().getData(object,RDTCUitl.ClassName.USER);
            users.add(user);
        }
        return users;
    }

    @Override
    public User findById(Long id) throws ClassNotFoundException {

        if (!redisUtils.hExists(RedisTable.HASH.USER,String.valueOf(id))) {

            log.info("查询数据库");
            User user = readApi.findUserById(id);

            if (user!=null){
                redisUtils.hPut(RedisTable.HASH.USER,String.valueOf(id),String.valueOf(user));
            }

            return user;
        }

        log.info("查询缓存");
        Object UO = redisUtils.hGet(RedisTable.HASH.USER, String.valueOf(id));
        User user = new RDTCUitl<User>().getData(UO, RDTCUitl.ClassName.USER);

        return user;
    }

    @Override
    public User update(User user,String uuid) {
        return null;
    }

    @Override
    public User save(RegisterDto registerDto, MultipartFile Hs, String uuid) throws Exception {


        //密码加密
        String encodePassword = bCryptPasswordEncoder.encode(registerDto.getPassword());

        registerDto.setPassword(encodePassword);

        String registerDtoJson = String.valueOf(registerDto);

        return  writeApi.saveUser(registerDtoJson,Hs);
    }

    @Override
    public User findByUsername(String username) {
        return null;
    }

    @Override
    public void deleteById(Long id,String uuid) {

    }
}
