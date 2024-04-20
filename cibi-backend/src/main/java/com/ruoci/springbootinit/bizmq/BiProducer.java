package com.ruoci.springbootinit.bizmq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

import static com.ruoci.springbootinit.constant.CommonConstant.*;

/**
 * @Author: ruoci
 **/
@Component
@Slf4j
public class BiProducer {

    @Resource
    private RabbitTemplate rabbitTemplate;

    public void sendMessage(String message){
        log.info("发送消息：{}", message);
        rabbitTemplate.convertAndSend(BI_EXCHANGE_NAME, BI_ROUTING_KEY, message);
    }


}
