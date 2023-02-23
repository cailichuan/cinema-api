package com.cinema.main_api.aop;

import annotation.ErrorVoidApiParamter;
import com.cinema.main_api.util.RedisUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import type.RedisTable;

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
    @Pointcut("execution(* com.cinema.main_api.controller.*.*(..))")
    public void allApi(){}




    //带有@ErrorVoidApi注解的方法
    @Pointcut("@annotation(annotation.ErrorVoidApi)")
    public void errorVoidApiMethod(){}

    //===================after============================================

    /**
     * reids获取write的报错
     * 方法不报错时执行
     * @param joinPoint
     * @throws Throwable
     */
    @AfterReturning("allApi() && errorVoidApiMethod()")
    public void springCloudVoidError(JoinPoint joinPoint) throws Throwable {
        //获取方法
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();


        //获取参数
        Parameter[] parameters = method.getParameters();
        Object[] args = joinPoint.getArgs();

        //String uuid参数的位置
        int SUIndex = 0;
        //@ErrorVoidApiParamter注解的位置
        int EVAPIndex = 0;
        //是否有有@ErrorVoidApiParamter注解
        boolean hashErrorVoidApiParamter = false;



        for (int i = 0; i<parameters.length;i++){

            Parameter parameter = parameters[i];

            if (parameter.getName().equals("uuid")){
                SUIndex = i;
                break;
            }

            //如果参数上有@ErrorVoidApiParamter注解
            if (parameter.getAnnotation(ErrorVoidApiParamter.class)!=null){
                hashErrorVoidApiParamter= true;
                EVAPIndex = i;
                break;
            }

        }

        //如果参数上有@ErrorVoidApiParamter注解
        if (hashErrorVoidApiParamter){

            //获取uuid
            String typeName = parameters[EVAPIndex].getParameterizedType().getTypeName();
            Class pClass = Class.forName(typeName);
            Method getUuid = pClass.getMethod("getUuid");
            String uuid=(String)getUuid.invoke(args[EVAPIndex],null);

            //存在错误信息则抛出异常
            if (redisUtils.hExists(RedisTable.HASH.WRITE_CHARACTERISTIC_By_ERROR,uuid)){
                String msg = (String)redisUtils.hGet(RedisTable.HASH.WRITE_CHARACTERISTIC_By_ERROR, uuid);
                throw new Exception(msg);
            }

            return;
        }

        //如果参数上每有@ErrorVoidApiParamter注解
        //获取uuid
        String uuid  = (String)args[SUIndex];

        //如果存在异常信息则抛出异常
        if (redisUtils.hExists(RedisTable.HASH.WRITE_CHARACTERISTIC_By_ERROR,uuid)){
            String msg = (String) redisUtils.hGet(RedisTable.HASH.WRITE_CHARACTERISTIC_By_ERROR, uuid);

            throw new Exception(msg);
        }

    }
}
