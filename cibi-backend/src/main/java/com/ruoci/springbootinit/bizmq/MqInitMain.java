package com.ruoci.springbootinit.bizmq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.ruoci.springbootinit.constant.CommonConstant;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @Author: ruoci
 **/
@Slf4j
public class MqInitMain {


    public static void main(String[] args) {

        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("localhost");
        try {
            Connection connection = connectionFactory.newConnection();
            Channel channel = connection.createChannel();
//            声明交换机
            channel.exchangeDeclare(CommonConstant.BI_EXCHANGE_NAME, "direct");
//            声明队列.
            channel.queueDeclare(CommonConstant.BI_QUEUE_NAME, true, false, false, null);
            channel.queueBind(CommonConstant.BI_QUEUE_NAME, CommonConstant.BI_EXCHANGE_NAME, CommonConstant.BI_ROUTING_KEY);
        } catch (IOException | TimeoutException e) {
            log.error("建立连接失败!!");
            throw new RuntimeException(e);
        }


    }


}
