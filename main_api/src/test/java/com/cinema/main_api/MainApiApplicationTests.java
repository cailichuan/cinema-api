package com.cinema.main_api;

import cn.hutool.core.lang.UUID;
import cn.hutool.json.JSON;
import cn.hutool.json.JSONUtil;
import com.cinema.main_api.util.RedisUtils;
import model.entity.Activity;
import model.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@SpringBootTest
class MainApiApplicationTests {

    @Resource
    private RedisUtils redisUtils;

    @Test
    void contextLoads() {
        int i=1,s=0;
        while (i<3){
            i++;
            s+=i;
        }
        System.out.println(s);
    }

    @Test
    public void redisTest(){
        //{id=3343, content='heih1', number=553, startTime='2023-03-03 21:02:55', endTime='2023-03-03 21:02:56', createAt='2023-03-03 21:02:57'}

        String o="{id=3343, content='heih1', number=553, startTime='2023-03-03 21:02:55', endTime='2023-03-03 21:02:56', createAt='2023-03-03 21:02:57'}";
        String s = "{id:3343, content:'heih1', number:553, startTime:'2023-03-03 21:02:55', endTime:'2023-03-03 21:02:56', createAt:'2023-03-03 21:02:57'}";
        Activity activity = JSONUtil.toBean(s, Activity.class);
        System.out.println(activity);

        System.out.println("+++++++++++++++++++++");


        JSON parse = JSONUtil.parse(o);


        System.out.println(parse);


    }

    @Test
    public void sss(){

        String s= "{id=3343, content='heih1', number=553, startTime='2023-03-03 21:02:55', endTime='2023-03-03 21:02:56', createAt='2023-03-03 21:02:57'}";

        String pattern="^\\{(\\s?\\S+=\\S+,?)+}$";

        String p="^\\{(\\s?\\S+=\\S+,?)*\\}$";
        Pattern compile = Pattern.compile(pattern);
        Matcher matcher = compile.matcher(s);
        if (matcher.find()){
            System.out.println("有");
            String group = matcher.group(1);
            System.out.println(group);
            int end = matcher.end();
            System.out.println(end);
        }

    }

    @Test
    public void uid(){

        System.out.println(UUID.randomUUID());
    }

}
