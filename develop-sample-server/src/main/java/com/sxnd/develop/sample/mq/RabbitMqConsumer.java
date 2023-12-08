package com.sxnd.develop.sample.mq;

import lombok.extern.slf4j.Slf4j;
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
public class RabbitMqConsumer {


//    @RabbitHandler
//    @RabbitListener(queues = "position_dispatch_controller_queue",ackMode = "MANUAL" )
//    public void receive(Message message, Channel channel) throws IOException {
//        Long deliveryTag = null;
//        String str = null;
//        try {
//            deliveryTag = message.getMessageProperties().getDeliveryTag();
//             str = new String(message.getBody());
//            // 日誌刷屏先注釋掉
//            log.info("收到来自队列positionQueue的消息tag：{}，内容：{}：",deliveryTag, JSONUtil.toJsonStr(str));
//        }catch (Exception e){
//            log.error("消费position_dispatch_controller_queue异常,消息内容：{}",JSONUtil.toJsonStr(str),e);
//        }finally {
//            channel.basicAck(deliveryTag,false);
//        }
//
//
//
//    }










}
