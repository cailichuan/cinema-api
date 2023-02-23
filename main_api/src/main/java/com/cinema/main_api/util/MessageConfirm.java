package com.cinema.main_api.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.ReturnedMessage;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;


import javax.annotation.PostConstruct;
import javax.annotation.Resource;


/**
 * mq的应答回调类
 */
@Slf4j
@Component
public class MessageConfirm implements RabbitTemplate.ConfirmCallback , RabbitTemplate.ReturnsCallback {


    @Resource
    private RabbitTemplate rabbitTemplate;

    /**
     * 工程运行的时候执行该方法
     */
    @PostConstruct
    private void  init(){
        rabbitTemplate.setConfirmCallback(this);
    }


    /**
     *
     * 数据提交到交换机的回调方法，不管提交成不成功都会调用此方法
     *
     * @param correlationData 发送时携带的消息
     * @param ack 如果为true,表现交换机接收到消息
     * @param message 异常消息
     */
    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String message) {


        if (ack){
            log.info("交换机接收到消息");
        }

        else {
            log.info("交换机接收消失失败，原因:{}",message);
        }

    }


    /**
     *当routingkey不存在的时候，会触发该方法
     * @param returnedMessage
     */
    @Override
    public void returnedMessage(ReturnedMessage returnedMessage) {

        String message = new String(returnedMessage.getMessage().getBody());
        String replyText = returnedMessage.getReplyText();
        log.info("交换机推送消息到队列失败，消息是:{},失败原因:{}",message,replyText);
    }
}
