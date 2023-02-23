package com.cinema.main_api.config;

import com.cinema.main_api.handler.CloudResponseResultDecoder;
import com.cinema.main_api.handler.CloudResponseResultErrorDecoder;
import feign.codec.Decoder;
import feign.codec.ErrorDecoder;
import feign.optionals.OptionalDecoder;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.openfeign.support.ResponseEntityDecoder;
import org.springframework.cloud.openfeign.support.SpringDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * fegin的配置类
 */
@Configuration
public class CloudFeignConfig {

    /**
     * 注册自定义的decoder解析器
     * @param messageConverters
     * @return
     */
    @Bean
    public Decoder decoder(ObjectProvider<HttpMessageConverters> messageConverters){
        return new OptionalDecoder((new ResponseEntityDecoder(new CloudResponseResultDecoder(new SpringDecoder(messageConverters)))));
    }

//    @Bean
//    public ErrorDecoder errorDecoder(){
//        return new CloudResponseResultErrorDecoder();
//    }

}
