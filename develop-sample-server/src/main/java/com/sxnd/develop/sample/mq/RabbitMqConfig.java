package com.sxnd.develop.sample.mq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * @author GW00305020
 * @ClassName RabbitMqCinfig
 * @description: TODO
 * @date 2023年08月04日
 * @version: 1.0
 */
@Configuration
@Slf4j
public class RabbitMqConfig {
//    /**
//     *  调度任务状态的队列
//     * @return  任务状态的队列-IOT订阅
//     */
//    @Bean("dispatchTaskStatusQueueToDevice")
//    public Queue DispatchTaskStatusQueueToDevice() {
//        return new Queue("dispatchTaskStatusQueue.ToDevice");
//    }
//
//    /**
//     *  调度任务下发队列
//     * @return 路权订阅
//     */
//    @Bean("dispatchTaskContentQueueToRouteRight")
//    public Queue dispatchTaskContentQueueToRouteRight() {
//        return new Queue("dispatchTaskContentQueue.ToRouteRight");
//    }
//
//    /**
//     * 调度任务下发队列
//     * @return IOT订阅
//     */
//    @Bean("dispatchTaskContentQueueToDevice")
//    public Queue DispatchTaskContentQueueToDevice() {
//        return new Queue("dispatchTaskContentQueue.ToDevice");
//    }
//
//    /**
//     * 调度任务状态主题交换机
//     * @return
//     */
//    @Bean("dispatchTaskStatusTopic")
//    public TopicExchange dispatchTaskStatusTopicExchange() {
//        return new TopicExchange("dispatchTaskStatus.Topic");
//    }
//
//    /**
//     * 调度任务下发主题交换机
//     * @return
//     */
//    @Bean("dispatchTaskContentTopic")
//    public TopicExchange taskContentTopicExchange() {
//        return new TopicExchange("dispatchTaskContent.Topic");
//    }
//
//    /**
//     * 调度任务状态队列与主题交换机的绑定
//     * @param topic
//     * @param dispatchTaskStatusQueueToDevice
//     * @return
//     */
//    @Bean
//    public Binding bindingDispatchTaskStatusQueueToDevice(@Qualifier("dispatchTaskStatusTopic") TopicExchange topic, @Qualifier("dispatchTaskStatusQueueToDevice") Queue dispatchTaskStatusQueueToDevice) {
//        return BindingBuilder.bind(dispatchTaskStatusQueueToDevice)
//                .to(topic)
//                .with("dispatch.taskStatus");
//    }
//
//
//    /**
//     * 调度任务下发队列与主题交换机的绑定
//     * @param topic
//     * @param dispatchTaskContentQueueToDevice
//     * @return
//     */
//    @Bean
//    public Binding bindingTopicToDeviceTaskContentQueue(@Qualifier("dispatchTaskContentTopic") TopicExchange topic, @Qualifier("dispatchTaskContentQueueToDevice") Queue dispatchTaskContentQueueToDevice) {
//        return BindingBuilder.bind(dispatchTaskContentQueueToDevice)
//                .to(topic)
//                .with("dispatch.taskContent");
//    }
//
//    @Bean
//    public Binding  bindingTopicToRouteRightTaskContentQueue(@Qualifier("dispatchTaskContentTopic") TopicExchange topic, @Qualifier("dispatchTaskContentQueueToRouteRight") Queue dispatchTaskContentQueueToRouteRight) {
//        return BindingBuilder.bind(dispatchTaskContentQueueToRouteRight)
//                .to(topic)
//                .with("dispatch.taskContent.routeRight");
//    }

    @Bean
    public RabbitTemplate createRabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate();
        rabbitTemplate.setConnectionFactory(connectionFactory);
        rabbitTemplate.setMandatory(true);
        rabbitTemplate.setConfirmCallback((correlationData, ack, cause) -> {
            if(ack){
                log.info("消息发送交换机成功：correlationData：{},ack：{},cause：{}", correlationData, ack, cause);
            }else{
                log.info("消息发送交换机失败：correlationData：{},ack：{},cause：{}", correlationData, ack, cause);
            }
        });
        rabbitTemplate.setReturnsCallback(new RabbitTemplate.ReturnsCallback() {
            @Override
            public void returnedMessage(ReturnedMessage returnedMessage) {
                log.info(returnedMessage.getMessage().toString());
                log.info("消息信息：" +  new String(returnedMessage.getMessage().getBody()));
                log.info("交换机：" + returnedMessage.getExchange());
            }
        });
        return rabbitTemplate;
    }

}
