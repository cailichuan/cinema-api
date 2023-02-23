package com.cinema.read_api;

import com.cinema.read_api.mapper.ReadActivityMapper;
import model.entity.Activity;
import org.junit.jupiter.api.Test;
import org.springframework.boot.system.ApplicationHome;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.ResourceUtils;
import util.PathUtil;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

@SpringBootTest
class ReadApiApplicationTests {
    @Resource
    ReadActivityMapper readActivityMapper;

    @Test
    void contextLoads() throws FileNotFoundException {
        System.out.println(new PathUtil().getCommonsImgPath());


    }

}
