package com.cinema.main_api.consumer;


//import api.write.WriteApi;
import api.write.WriteApi;
import com.cinema.main_api.config.RabbitMqConfig;
import com.cinema.main_api.service.UuidService;
import com.cinema.main_api.util.GetSnowId;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import model.entity.OrderException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import type.SnowIdType;
import util.DataTimeUtil;


import javax.annotation.Resource;

/**
 * 订单消费者
 * 
 */
@Slf4j
@Component
public class oderConsumer {
    
    @Resource
    private WriteApi writeApi;

    @Resource
    private GetSnowId getSnowId;

    @Resource
    private UuidService uuidService;

    /**
     *
     * @param message
     * @param channel
     */
    @RabbitListener(queues = RabbitMqConfig.QUEUE_ORDER)
    public void order(Message message, Channel channel) throws Exception {
        String oidStr = new String(message.getBody());


        Long oid = Long.valueOf(oidStr);




        try {
            writeApi.payOrderById(oid);

            //代码执行完成，手动返回ack,把消息从队列中移除
            //flase单条处理
            channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
        } catch (Exception e) {

            //异常处理
            getSnowId.setSonwIdType(SnowIdType.ORDEREXCEPTION);
            long id = getSnowId.nextId();

            OrderException orderException = new OrderException();
            orderException.setId(id);
            orderException.setStatus(false);
            orderException.setOid(oid);
            orderException.setReason(e.getMessage());
            orderException.setCreateAt(DataTimeUtil.getNowDateTimeString());

            String uuid = uuidService.getUUid();


            writeApi.saveOrderException(orderException,uuid);

            //代码执行完成，手动返回ack,把消息从队列中移除
            //flase单条处理
            channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
        }



    }

    /**
     * 超时订单处理的队列
     * @param message
     * @param channel
     */
    @RabbitListener(queues = RabbitMqConfig.QUEUE_TTL_ODER)
    public void ttlOrder(Message message,Channel channel){


    ;


    }
}
