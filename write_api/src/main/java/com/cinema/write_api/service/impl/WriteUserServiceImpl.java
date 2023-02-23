package com.cinema.write_api.service.impl;

import com.cinema.write_api.mapper.WriteUserMapper;
import com.cinema.write_api.service.WriteUploadService;
import com.cinema.write_api.service.WriteUserService;
import com.cinema.write_api.util.GetSnowId;
import com.cinema.write_api.util.RedisUtils;
import lombok.extern.slf4j.Slf4j;
import model.dto.RegisterDto;
import model.dto.UploadDto;
import model.entity.Upload;
import model.entity.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import type.RedisTable;
import type.SnowIdType;
import util.DataTimeUtil;
import util.PathUtil;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
public class WriteUserServiceImpl implements WriteUserService {

    @Resource
    private WriteUserMapper writeUserMapper;



    @Resource
    private WriteUploadService writeUploadService;

    @Resource
    private GetSnowId snowId;

    @Resource
    private RedisUtils redisUtils;



    @Override
    public User update(User user) {
        writeUserMapper.update(user);


        log.info("写入缓存");
        redisUtils.hPut(RedisTable.HASH.USER,String.valueOf(user.getId()),String.valueOf(user));

        return user;
    }





    @Transactional
    @Override
    public User save(RegisterDto registerDto,MultipartFile HS){



        Integer count = writeUserMapper.countByUserName(registerDto.getUsername());



        try{
            if (count != 0 && count!=null){

                throw  new Exception("用户名已注册");
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }



        try {
            //把图片数据存入数据库
            long id = snowId.setSonwIdType(SnowIdType.UPLOAD).nextId();
            Upload upload = new Upload(id, "HS", PathUtil.getSuffix(HS.getOriginalFilename()));
            writeUploadService.sava(upload);



            //封装user
            String now = DataTimeUtil.getNowDateTimeString();
            long userId = snowId.setSonwIdType(SnowIdType.USER).nextId();
            User user = new User();
            user.setId(userId);
            user.setUpid(upload.getId());
            user.setEmail(registerDto.getEmail());
            user.setBirthday(registerDto.getBirthday());
            user.setNickname(registerDto.getNickname());
            user.setInfo(user.getInfo());
            user.setCreateAt(now);
            user.setUpdateAt(now);
            user.setUsername(registerDto.getUsername());
            user.setPassword(registerDto.getPassword());
            user.setGender(registerDto.getGender());
            //存入user
            writeUserMapper.insert(user);

            log.info("写入缓存");
            redisUtils.hPut(RedisTable.HASH.USER,String.valueOf(userId),String.valueOf(user));

            //把图片文件存入服务器
            UploadDto uploadDto = new UploadDto(upload.getId(), upload.getPath(), upload.getSuffix(), HS);
            writeUploadService.savaUploadInFile(uploadDto);
            return user;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("头像存储失败");
        }

    }

    @Override
    public void deleteById(Long id) {

        Map<String,Long> map = new HashMap<>();
        map.put("id",id);

        writeUserMapper.deleteByMap(map);

        log.info("删除缓存");
        redisUtils.hDelete(RedisTable.HASH.USER,String.valueOf(id));
    }
}
