package com.itcode.task;

import org.camunda.bpm.client.spring.annotation.ExternalTaskSubscription;
import org.camunda.bpm.client.task.ExternalTaskHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Configuration
public class MessageEventService {

    @ExternalTaskSubscription(
            topicName = "wechatPay",
            processDefinitionKey = "process_01_message_event",
            includeExtensionProperties = true,
            lockDuration = 50000 //每个topic锁定的时间.
    )
    @Bean
    public ExternalTaskHandler wechatPayHandler() {

        return (externalTask, externalTaskService) -> {
            System.out.println("开始微信支付");
            externalTaskService.complete(externalTask);
        };
    }

    @ExternalTaskSubscription(
            topicName = "alipay",
            processDefinitionKey = "process_01_message_event",
            includeExtensionProperties = true,
            lockDuration = 50000 //每个topic锁定的时间.
    )
    @Bean
    public ExternalTaskHandler alipayHandler() {

        return (externalTask, externalTaskService) -> {
            System.out.println("开始ali支付");
            externalTaskService.complete(externalTask);
        };
    }

    @ExternalTaskSubscription(
            topicName = "successPay",
            processDefinitionKey = "process_01_message_event",
            includeExtensionProperties = true,
            lockDuration = 50000 //每个topic锁定的时间.
    )
    @Bean
    public ExternalTaskHandler successPayHandler() {

        return (externalTask, externalTaskService) -> {
            System.out.println("支付成功");
            externalTaskService.complete(externalTask);
        };
    }
}
