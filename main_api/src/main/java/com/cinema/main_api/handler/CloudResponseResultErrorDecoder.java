package com.cinema.main_api.handler;

import feign.Response;
import feign.codec.ErrorDecoder;

public class CloudResponseResultErrorDecoder implements ErrorDecoder {

    @Override
    public Exception decode(String s, Response response) {


        System.out.println("状态码=" + response.status());
        return new Exception();
    }
}
