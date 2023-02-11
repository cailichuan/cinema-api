package com.cinema.read_api;

import com.cinema.read_api.mapper.ReadActivityMapper;
import model.entity.Activity;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
class ReadApiApplicationTests {
    @Resource
    ReadActivityMapper readActivityMapper;

    @Test
    void contextLoads() {
        List<Activity> activities = readActivityMapper.selectList();
        for (Activity activity : activities) {
            System.out.println(activity);
        }

    }

}
