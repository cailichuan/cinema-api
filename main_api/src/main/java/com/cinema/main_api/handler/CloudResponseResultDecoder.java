package com.cinema.main_api.handler;


import annotation.FeginDecoderBaseResponse;
import cn.hutool.core.io.IoUtil;
import cn.hutool.core.util.CharsetUtil;
import cn.hutool.json.JSONUtil;



import feign.FeignException;
import feign.Response;
import feign.codec.DecodeException;
import feign.codec.Decoder;


import lombok.SneakyThrows;
import model.entity.Activity;
import model.support.ResponseResult;

import org.springframework.cloud.openfeign.support.SpringDecoder;
import util.PatternUtil;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 微服务自定义fegin的decoder
 * fegin的解析器
 * fegin远程调用返回数据前会调用这个接口
 */
public class CloudResponseResultDecoder implements Decoder {
    private final SpringDecoder decoder;

    public CloudResponseResultDecoder(SpringDecoder decoder) {
        this.decoder = decoder;
    }

    @SneakyThrows
    @Override
    public Object decode(Response response, Type type) throws IOException, FeignException {


        //获取fegin请求的方法
        Method feginMethod = response.request().requestTemplate().methodMetadata().method();


        //获取fegin请求的方法的返回值类型
        Class<?> feginMethodReturnType = feginMethod.getReturnType();


        //获取fegin请求方法的注解
        Annotation annotation = feginMethod.getAnnotation(FeginDecoderBaseResponse.class);

        //如果fegin请求的方法返回值不是ResponseResult对象且没有FeginDecoderBaseResponse注解
        if (feginMethodReturnType != ResponseResult.class && annotation == null) {



            //获取状态码
            String json = IoUtil.read(response.body().asReader(CharsetUtil.CHARSET_UTF_8));
            String codeStr = String.valueOf(JSONUtil.parse(json).getByPath("code", String.class));
            Integer code = Integer.valueOf(codeStr);


            //如果状态码>400,则抛出异常
            if (code>=400){

                System.out.println(json);
                String msg =String.valueOf(JSONUtil.parse(json).getByPath("msg", String.class)) ;
                throw new DecodeException(code,msg,response.request());

            }


            //返回契合fegin请求的方法的返回值类型
            Object data = JSONUtil.parse(json).getByPath("data", feginMethodReturnType);

            //如果类型为list还要加强处理
            if (feginMethodReturnType == List.class) {

                try {

                    //获取list的元素的class
                    String listElementTypeStr = PatternUtil.getListElementType(type.getTypeName());
                    Class listElementClass = Class.forName(listElementTypeStr);

                    //加强序列化


                    //如果基本元素类型为Integer
                    if (listElementTypeStr.equals("java.lang.Integer")) {
                        List<Integer> stringNum = PatternUtil.getStringNum(String.valueOf(data));
                        data = stringNum;
                    } else {

                        List<Object> newList = new ArrayList<>();
                        for (Object element : (List) data) {

                            Object o = JSONUtil.parse(element).toBean(listElementClass);
                            newList.add(o);
                        }

                        data = newList;


                    }

                } catch (ClassNotFoundException e) {

                    throw new RuntimeException(e);
                }


            }


            //如果类型为map类型则还要加强处理
            if (feginMethodReturnType == Map.class) {


                try {

                    Map<String, String> mapKeyAndValueType = PatternUtil.getMapKeyAndValueType(type.getTypeName());

                    //获取key和value类型的class
                    String keyType = mapKeyAndValueType.get("key");
                    String valueType = mapKeyAndValueType.get("value");
                    Class key = Class.forName(keyType);
                    Class value = Class.forName(valueType);

                    Map<Object,Object> newMap = new HashMap<>();

                    Map dataMap = (Map) data;

                    dataMap.forEach((k,v) ->{


                        Object ko = JSONUtil.parse(k).toBean(key);
                        Object vo = JSONUtil.parse(v).toBean(value);

                        newMap.put(ko,vo);
                    });

                    data = dataMap;


                } catch (Exception e) {
                    e.printStackTrace();
                    throw new RuntimeException(e);
                }

            }


            //返回处理后的数据
            return data;

        }


            return this.decoder.decode(response, type);
    }
}
