package com.cinema.write_api.aop;

import annotation.ApiIdempotence;
import annotation.ErrorVoidApiParamter;
import annotation.PostApiIP;
import cn.hutool.json.JSON;
import cn.hutool.json.JSONUtil;
import com.cinema.write_api.util.RedisUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;
import type.RedisTable;
import util.RDTCUitl;

import javax.annotation.Resource;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.lang.reflect.Type;

/**
 * 接口幂等性处理的aop
 * 如果接口要受理必须添加@ApiIdempotence
 */

@Slf4j
@Component
@Aspect
public class ApiIdempotenceAop {

    @Resource
    private RedisUtils redisUtils;

    //============================切点=======================

    //所有controller所有的方法
    @Pointcut("execution(* com.cinema.write_api.controller.*.*(..))")
    public void allApi(){}


    //带有@ApiIdempotence注解的方法
    @Pointcut("@annotation(annotation.ApiIdempotence)")
    public void apiIdempotenceMethod(){}

    //带有@PostApiIdempotence注解的方法
    @Pointcut("@annotation(annotation.PostApiIdempotence)")
    public void postApiIdempotenceMethod(){}


    //带有@ErrorVoidApi注解的方法
    @Pointcut("@annotation(annotation.ErrorVoidApi)")
    public void errorVoidApiMethod(){}




    //===========================坏绕通知=======================

    /**
     * 非post方法的接口幂等性处理
     * @param pjp
     * @return
     * @throws Throwable
     */
    @Around("allApi() && apiIdempotenceMethod()")
    public Object noPostApiIdempotence(ProceedingJoinPoint pjp) throws Throwable {

        //方法执行前===============================


        MethodSignature signature = (MethodSignature) pjp.getSignature();

        //获取方法
        Method method = signature.getMethod();

        //获取方法上的注解
        ApiIdempotence annotation = AnnotationUtils.findAnnotation(method, ApiIdempotence.class);

        //uuid
        String uuid="";



        //方法参数
        Parameter[] parameters = method.getParameters();
        Object[] args = pjp.getArgs();

        for (int i=0;i<parameters.length;i++){

            if (parameters[i].getName().equals("uuid")) {

                Object arg = args[i];

                uuid = String.valueOf(arg);

                break;
            }
        }

        //检测接口幂等性
        log.info("检测接口幂等性");
        if (!redisUtils.hExists(RedisTable.HASH.WRITE_CHARACTERISTIC,uuid))
            throw new Exception("请不要重复操作");



        //执行方法======================================================

        Object proceed = pjp.proceed();


        //执行后=======================================================

        log.info("删除标识");
        redisUtils.hDelete(RedisTable.HASH.WRITE_CHARACTERISTIC,uuid);


        return proceed;
    }



    //=================================around=======================================
    @Around("allApi() && postApiIdempotenceMethod()")
    public Object postApiIdempotence(ProceedingJoinPoint pjp) throws Throwable{

        //方法执行前
        MethodSignature signature = (MethodSignature) pjp.getSignature();
        Method method = signature.getMethod();
        Parameter[] parameters = method.getParameters();

        //传入的参数
        Object[] args = pjp.getArgs();

        //uuid
        String uuid = "";
        boolean hasUuid = false;

        int index = 0;

        for (Parameter parameter : parameters) {

            System.out.println(args[index]);
            //如果参数上有@PostApiIP注解就获取该参数的uuid
            PostApiIP annotation = parameter.getAnnotation(PostApiIP.class);
            if (annotation!=null) {


                //去json取uuid
                String json = RDTCUitl.getJson(String.valueOf(args[index]));
                uuid = JSONUtil.parse(json).getByPath("uuid", String.class);

                hasUuid = true;
                break;


            }

            index++;
        }

        //村在uuid则进行幂等性处理
        if (hasUuid){
            log.info("检查接口幂等性");
            if(!redisUtils.hExists(RedisTable.HASH.WRITE_CHARACTERISTIC,uuid))
                throw new Exception("请勿重复操作.");
        }

        Object proceed = pjp.proceed();



        //方法执行后
        log.info("删除标识");
        redisUtils.hDelete(RedisTable.HASH.WRITE_CHARACTERISTIC,uuid);
        return proceed;
    }



}
