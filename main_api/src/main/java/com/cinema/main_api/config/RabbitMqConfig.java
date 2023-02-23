package com.cinema.main_api.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class RabbitMqConfig {

    //订单交换机名称
    public static final String ORDER_EXCHANGE = "O";

    //超时订单交换机名称
    public static final String TTL_ODER_EXCHANGE = "TTL_O";

    //超时订单路由
    public static final String ODER_ROUTE_KEY = "RO";

    //延迟订单队列路由
    public static final String TTL_ROUTE_KEY = "RTO";

    //订单队列名称
    public static final String QUEUE_ORDER = "QO";

    //延迟订单队列名称
    public static final String QUEUE_TTL_ODER = "QTO";


    //声明交换机***********************************************
    @Bean("o_Exchange")
    public DirectExchange o_Exchange(){
        return new DirectExchange(ORDER_EXCHANGE);
    }

    @Bean("ttl_O_Exchange")
    public DirectExchange ttl_O_Exchange(){
        return new DirectExchange(TTL_ODER_EXCHANGE);
    }


    //声明队列*************************************************
    @Bean("queueO")
    public Queue queueO(){
        Map<String,Object> arguments = new HashMap<>(3);

        //设置死信交换机
        arguments.put("x-dead-letter-exchange",TTL_ODER_EXCHANGE);
        //设置死信路由
        arguments.put("x-dead-letter-routing-key",TTL_ROUTE_KEY);

        //设置过期时间
        arguments.put("x-message-ttl",40000);

        //设置最大长度
        arguments.put("x-max-length",6);

        return QueueBuilder.durable(QUEUE_ORDER).withArguments(arguments).build();

     }

     @Bean("queueTO")
    public Queue queueTO(){

        return QueueBuilder.durable(QUEUE_TTL_ODER).build();
     }


     //绑定***************************************************************
    @Bean
    public Binding queueOBingdingO(@Qualifier("queueO")Queue queueO,
                                   @Qualifier("o_Exchange") DirectExchange oExchange){

        return BindingBuilder.bind(queueO).to(oExchange).with(ODER_ROUTE_KEY);
    }

    @Bean
    public Binding queueTOBingdingTO(@Qualifier("queueTO")Queue queueTO,
                                     @Qualifier("ttl_O_Exchange")DirectExchange ttlOExchange){
        return BindingBuilder.bind(queueTO).to(ttlOExchange).with(TTL_ROUTE_KEY);
    }
}
