package com.ifyou.skypivot.sample.mq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author GW00305020
 * @ClassName RabbitMqConsumer
 * @description: TODO
 * @date 2023年08月03日
 * @version: 1.0
 */
@Component
@Slf4j
public class RabbitMqProducer {

    @Autowired
    private RabbitTemplate template;

//    @Resource(name = "dispatchTaskStatusTopic")
//    private TopicExchange dispatchTaskStatusTopic;
//
//    public void sendDisPatchTaskStatus(UUID deviceId,Message message){
//        message.getMessageProperties().setHeader("deviceId",deviceId);
//        template.convertAndSend(dispatchTaskStatusTopic.getName(), "dispatch.taskStatus",message);
//        log.info("向车辆：{}同步任务状态成功",deviceId);
//    }
//
//    public void sendDispatchTaskToDevice(UUID deviceId,Message message){
//        message.getMessageProperties().setHeader("deviceId",deviceId);
//        template.convertAndSend(dispatchTaskContentTopic.getName(), "dispatch.taskContent",message);
//        log.info("向车辆：{}下发调度任务成功",deviceId);
//
//    }
//    public void sendDispatchTaskToRouteRight(UUID deviceId,Message message){
//        template.convertAndSend(dispatchTaskContentTopic.getName(), "dispatch.taskContent.routeRight",message);
//        log.info("向路权发送车辆：{}下发调度任务成功",deviceId);
//
//    }



}
