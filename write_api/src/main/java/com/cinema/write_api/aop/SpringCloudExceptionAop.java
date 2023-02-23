package com.cinema.write_api.aop;

import annotation.ErrorVoidApiParamter;

import cn.hutool.json.JSONUtil;
import com.cinema.write_api.util.RedisUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;

import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import type.RedisTable;
import util.RDTCUitl;

import javax.annotation.Resource;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;


@Slf4j
@Component
@Aspect
public class SpringCloudExceptionAop {

    @Resource
    private RedisUtils redisUtils;

    //===============================切点================
    //所有controller所有的方法
    @Pointcut("execution(* com.cinema.write_api.controller.*.*(..))")
    public void allApi(){}




    //带有@ErrorVoidApi注解的方法
    @Pointcut("@annotation(annotation.ErrorVoidApi)")
    public void errorVoidApiMethod(){}

    //=========================after=============================

    /**
     * 报错则将错误信息写入redis
     * 报错后执行
     * @param joinPoint
     */
    @AfterThrowing(value = "allApi()  && errorVoidApiMethod()",throwing = "ex")
    public void springCloudVoidError(JoinPoint joinPoint,Throwable ex){

        log.info("报错，错误信息写入缓存");
        //获取方法
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();

        //获取参数
        Parameter[] parameters = method.getParameters();
        Object[] args = joinPoint.getArgs();

        //@ErrorVoidApiParamter注解的位置
        int EVAPIndex = 0;
        //是否存在@ErrorVoidApiParamter注解注解
        boolean hashErrorVoidApiParamter=false;

        //String uuid的参数的位置
        int SUIndex = 0;

        for (int i = 0;i<parameters.length;i++){

            Parameter parameter = parameters[i];

            //存在String uuid参数
            if (parameter.getName().equals("uuid")) {
                SUIndex = i;
                break;
            }

            //存在@ErrorVoidApiParamter注解
            if (parameter.getAnnotation(ErrorVoidApiParamter.class)!=null) {
                EVAPIndex = i;
                hashErrorVoidApiParamter = true;
                break;
            }
        }



        //存在存在@ErrorVoidApiParamter注解
        if (hashErrorVoidApiParamter){

            //获取uuid
            String json = RDTCUitl.getJson(String.valueOf(args[EVAPIndex]));

            String uuid = JSONUtil.parse(json).getByPath("uuid", String.class);

            redisUtils.hPut(RedisTable.HASH.WRITE_CHARACTERISTIC_By_ERROR,uuid,String.valueOf(ex));

            return;
        }

        //不存在@ErrorVoidApiParamter注解
        //获取uuid
        String uuid = (String) args[SUIndex];
        redisUtils.hPut(RedisTable.HASH.WRITE_CHARACTERISTIC_By_ERROR,uuid,String.valueOf(ex));


    }
}
