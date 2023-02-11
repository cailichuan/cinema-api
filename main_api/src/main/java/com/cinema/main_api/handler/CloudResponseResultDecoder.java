package com.cinema.main_api.handler;


import cn.hutool.core.io.IoUtil;
import cn.hutool.core.util.CharsetUtil;
import cn.hutool.json.JSONUtil;



import feign.FeignException;
import feign.Response;
import feign.codec.DecodeException;
import feign.codec.Decoder;


import model.support.ResponseResult;

import org.springframework.cloud.openfeign.support.SpringDecoder;

import java.io.IOException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;

/**
 * 微服务自定义fegin的decoder
 * fegin的解析器
 * fegin远程调用返回数据前会调用这个接口
 */
public class CloudResponseResultDecoder implements Decoder {
    private final SpringDecoder decoder;

    public CloudResponseResultDecoder(SpringDecoder decoder){
        this.decoder=decoder;
    }

    @Override
    public Object decode(Response response, Type type) throws IOException, FeignException {


        //获取fegin请求的方法
        Method feginMethod = response.request().requestTemplate().methodMetadata().method();

        //获取fegin请求的方法的返回值类型
        Class<?> feginMethodReturnType = feginMethod.getReturnType();

        //如果fegin请求的方法返回值不是ResponseResult对象
        if (feginMethodReturnType!= ResponseResult.class){
            String json = IoUtil.read(response.body().asReader(CharsetUtil.CHARSET_UTF_8));

            //返回契合fegin请求的方法的返回值类型
            return JSONUtil.parse(json).getByPath("data",feginMethodReturnType);
        }

        return this.decoder.decode(response,type);
    }
}
