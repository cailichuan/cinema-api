package com.cinema.main_api.handler;

import annotation.DisableBaseResponse;

import lombok.extern.slf4j.Slf4j;
import model.support.ResponseResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.nio.file.AccessDeniedException;

/**
 * 捕获controller异常
 * conrtroller抛出异常执行下边的函数
 * 返回Respone写入ApiResult
 */

@Slf4j
@ResponseBody
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    @DisableBaseResponse
    public Object handleException(Exception e){
        if (e.getClass().equals(AccessDeniedException.class)) {
            return new ResponseResult<>(403,"你没有访问权限");
        }

        log.error(e.getMessage());


        return new ResponseResult<>(400,e.getMessage());
    }


}
