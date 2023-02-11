package com.cinema.write_api.handler;

import annotation.DisableBaseResponse;
import model.support.ResponseResult;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * 统一拦截read-api的controller中所有方法的返回值
 * 封装后返回ResponseResult<T>
 */
@ControllerAdvice(basePackages = "com.cinema.write_api")
public class WriteGlobalResponseHandler implements ResponseBodyAdvice<Object> {
    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        //如果方法上带有DisableBaseResponse注解， 不处理返回false
        return returnType.hasMethodAnnotation(DisableBaseResponse.class);
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        if (body==null) {
            return new ResponseResult<>();
        }

        return new ResponseResult<>(body);
    }
}
